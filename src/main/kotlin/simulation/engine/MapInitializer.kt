package simulation.engine

import simulation.config.SimulationConfig
import simulation.creatures.Herbivore
import simulation.creatures.Predator
import simulation.food.Grass
import simulation.food.Tree
import simulation.general.Map
import simulation.general.Position
import simulation.landscape.Rock
import kotlin.random.Random

object MapInitializer {

    fun init(): Map {
        val map = Map()

        generateRocks(map)
        generateGrass(map)
        generateTrees(map)
        generateHerbivores(map)
        generatePredators(map)

        return map
    }


    private fun generateRocks(map: Map) {
        repeat(SimulationConfig.START_ROCKS) {
            val position = randomFreePosition(map)
            map.put(position, Rock())
        }
    }


    private fun generateGrass(map: Map) {
        repeat(SimulationConfig.START_GRASS) {
            val position = randomFreePosition(map)
            map.put(position, Grass())
        }
    }


    private fun generateTrees(map: Map) {
        repeat(SimulationConfig.START_TREES) {
            val position = randomFreePosition(map)
            map.put(position, Tree())
        }
    }


    private fun generateHerbivores(map: Map) {
        repeat(SimulationConfig.START_HERBIVORES) {
            val position = randomFreePosition(map)

            map.put(
                position,
                Herbivore(
                    speed = 1.0,
                    hp = 100,
                    hunger = 140,
                    satiety = 84.0
                )
            )
        }
    }


    private fun generatePredators(map: Map) {
        repeat(SimulationConfig.START_PREDATORS) {
            val position = randomFreePosition(map)

            map.put(
                position,
                Predator(
                    speed = 1.0,
                    hp = 150,
                    attack = 30,
                    hunger = 300,
                    satiety = 180.0
                )
            )
        }
    }

    private fun randomFreePosition(map: Map): Position {

        while (true) {

            val position = Position(
                x = Random.nextInt(0, SimulationConfig.WIDTH).toDouble(),
                y = Random.nextInt(0, SimulationConfig.HEIGHT).toDouble()
            )

            if (map.isEmpty(position)) {
                return position
            }
        }
    }
}