package simulation.engine

import simulation.config.SimulationConfig
import simulation.creatures.Creature
import simulation.creatures.Herbivore
import simulation.creatures.Predator
import simulation.food.Grass
import simulation.food.Tree
import simulation.general.Map
import simulation.general.Position
import java.util.Random
import kotlin.math.roundToInt

object SimulationEngine {
    fun nextTurn(map: Map) {
        moveCreatures(map)
        updateSatiety(map)
        updateHpBasedOnSatiety(map)
        generateFood(map)
        reproduce(map)
    }

    private fun moveCreatures(map: Map) {
        map
            .getAllEntities()
            .filterIsInstance<Creature>()
            .forEach { creature ->
                creature.move(map)
            }
    }

    private fun updateSatiety(map: Map) {
        map
            .getAllEntities()
            .filterIsInstance<Creature>()
            .forEach { creature ->

                val loss =
                    (creature.hunger * SimulationConfig.SATIETY_LOSS_MULTIPLIER)
                        .roundToInt()

                creature.satiety -= loss

                if (creature.satiety < 0) {
                    creature.satiety = 0
                }
            }
    }

    private fun updateHpBasedOnSatiety(map: Map) {
        map
            .getAllEntities()
            .filterIsInstance<Creature>()
            .forEach { creature ->

                val satietyPercent = creature.getSatietyPercent() * 100

                when {
                    satietyPercent < SimulationConfig.CRITICAL_SATIETY_PERCENT -> {
                        val damage =
                            (
                                creature.hunger *
                                    SimulationConfig.CRITICAL_STARVATION_DAMAGE_MULTIPLIER
                            ).roundToInt()

                        creature.currentHp -= damage
                    }

                    satietyPercent < SimulationConfig.STARVING_SATIETY_PERCENT -> {
                        val damage =
                            (
                                creature.hunger *
                                    SimulationConfig.STARVATION_DAMAGE_MULTIPLIER
                            ).roundToInt()

                        creature.currentHp -= damage
                    }

                    satietyPercent >= SimulationConfig.RECOVERY_SATIETY_PERCENT -> {
                        val recovery =
                            (creature.maxHp * SimulationConfig.HP_RECOVERY_MULTIPLIER)
                                .roundToInt()

                        creature.currentHp =
                            (creature.currentHp + recovery)
                                .coerceAtMost(creature.maxHp)
                    }
                }

                if (creature.currentHp <= 0) {
                    map.remove(creature.position)
                }
            }
    }

    private fun generateFood(map: Map) {
        val random = Random()

        repeat(
            random.nextInt(
                SimulationConfig.MIN_GRASS_PER_TURN,
                SimulationConfig.MAX_GRASS_PER_TURN + 1,
            ),
        ) {
            val position = randomFreePosition(map, random)
            map.put(
                position,
                Grass(position),
            )
        }

        repeat(
            random.nextInt(
                SimulationConfig.MIN_TREES_PER_TURN,
                SimulationConfig.MAX_TREES_PER_TURN + 1,
            ),
        ) {
            val position = randomFreePosition(map, random)
            map.put(
                position,
                Tree(position),
            )
        }
    }

    private fun randomFreePosition(
        map: Map,
        random: Random,
    ): Position {
        while (true) {
            val position =
                Position(
                    random.nextInt(0, SimulationConfig.WIDTH),
                    random.nextInt(0, SimulationConfig.HEIGHT),
                )

            if (map.isEmpty(position)) {
                return position
            }
        }
    }

    private fun reproduce(map: Map) {
        val random = Random()

        map
            .getAllEntities()
            .filterIsInstance<Creature>()
            .toList()
            .forEach { parent ->

                if (parent.getSatietyPercent() * 100 <
                    SimulationConfig.REPRODUCTION_SATIETY_PERCENT
                ) {
                    return@forEach
                }

                val childPosition =
                    getRandomFreeNeighbor(parent.position, map)
                        ?: return@forEach

                val childSatiety =
                    parent.satiety *
                        SimulationConfig.REPRODUCTION_SATIETY_TRANSFER_PERCENT / 100

                parent.satiety -= childSatiety

                val child =
                    when (parent) {
                        is Herbivore -> {
                            Herbivore(
                                position = childPosition,
                                speed =
                                    mutate(
                                        parent.speed,
                                        random,
                                    ),
                                maxHp =
                                    mutateHp(
                                        parent.maxHp,
                                        random,
                                    ),
                                hunger = parent.hunger,
                            )
                        }

                        is Predator -> {
                            Predator(
                                position = childPosition,
                                speed =
                                    mutate(
                                        parent.speed,
                                        random,
                                    ),
                                maxHp =
                                    mutateHp(
                                        parent.maxHp,
                                        random,
                                    ),
                                hunger = parent.hunger,
                                attack =
                                    mutateDamage(
                                        parent.attack,
                                        random,
                                    ),
                            )
                        }

                        else -> {
                            return@forEach
                        }
                    }

                map.put(
                    childPosition,
                    child,
                )
            }
    }

    private fun getRandomFreeNeighbor(
        position: Position,
        map: Map,
    ): Position? {
        val neighbors =
            listOf(
                Position(position.x + 1, position.y),
                Position(position.x - 1, position.y),
                Position(position.x, position.y + 1),
                Position(position.x, position.y - 1),
            ).filter {
                it.x in 0 until SimulationConfig.WIDTH &&
                    it.y in 0 until SimulationConfig.HEIGHT &&
                    map.isEmpty(it)
            }

        return neighbors.randomOrNull()
    }

    private fun mutate(
        value: Int,
        random: Random,
    ): Int =
        (value + random.nextInt(-1, 2))
            .coerceAtLeast(1)

    private fun mutateHp(
        value: Int,
        random: Random,
    ): Int =
        (
            value +
                random.nextInt(
                    -SimulationConfig.HP_MUTATION,
                    SimulationConfig.HP_MUTATION + 1,
                )
        ).coerceAtLeast(1)

    private fun mutateDamage(
        value: Int,
        random: Random,
    ): Int =
        (
            value +
                random.nextInt(
                    -SimulationConfig.DAMAGE_MUTATION,
                    SimulationConfig.DAMAGE_MUTATION + 1,
                )
        ).coerceAtLeast(1)
}
