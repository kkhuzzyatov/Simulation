package simulation.creatures

import simulation.config.SimulationConfig
import simulation.engine.PathFinder
import simulation.general.Map
import simulation.general.Position
import simulation.interfaces.Attackable

class Predator(
    position: Position,
    speed: Int,
    maxHp: Int,
    hunger: Int,
    val attack: Int,
) : Creature(position, speed, maxHp, hunger),
    Attackable {
    override fun attack(target: Creature) {
        target.currentHp -= attack
    }

    override fun move(map: Map) {
        val herbivore =
            PathFinder.findNearestHerbivore(position, map)
                ?: return

        val distance =
            kotlin.math.abs(position.x - herbivore.position.x) +
                kotlin.math.abs(position.y - herbivore.position.y)

        if (distance == 1) {
            attack(herbivore as Herbivore)

            if (herbivore.currentHp <= 0) {
                eatKilledHerbivore(herbivore, map)
            }

            return
        }

        moveToFreeNearTarget(herbivore.position, map)
    }

    private fun moveToFreeNearTarget(
        target: Position,
        map: Map,
    ) {
        val possiblePositions =
            listOf(
                Position(target.x + 1, target.y),
                Position(target.x - 1, target.y),
                Position(target.x, target.y + 1),
                Position(target.x, target.y - 1),
            )

        possiblePositions
            .shuffled()
            .firstOrNull { map.isEmpty(it) }
            ?.let {
                map.move(this, it)
            }
    }

    private fun eatKilledHerbivore(
        herbivore: Herbivore,
        map: Map,
    ) {
        satiety += SimulationConfig.HERBIVORE_VALUE

        map.remove(herbivore.position)

        map.move(
            this,
            herbivore.position,
        )
    }
}
