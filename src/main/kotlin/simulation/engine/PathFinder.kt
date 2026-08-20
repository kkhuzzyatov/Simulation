package simulation.engine

import simulation.config.SimulationConfig
import simulation.creatures.Herbivore
import simulation.food.Grass
import simulation.food.HerbivoreFood
import simulation.food.Tree
import simulation.general.Map
import simulation.general.Position

object PathFinder {
    fun findNearestFoodForHerbivore(
        start: Position,
        map: Map,
    ): Position? =
        findNearest(start, map) { entity ->
            entity is HerbivoreFood
        }

    fun findNearestHerbivore(
        position: Position,
        map: Map,
    ): Herbivore? =
        map
            .getAllEntities()
            .filterIsInstance<Herbivore>()
            .minByOrNull {
                kotlin.math.abs(it.position.x - position.x) +
                    kotlin.math.abs(it.position.y - position.y)
            }

    private fun findNearest(
        start: Position,
        map: Map,
        predicate: (Any?) -> Boolean,
    ): Position? {
        val queue = ArrayDeque<Position>()
        val visited = mutableSetOf<Position>()

        queue.addLast(start)
        visited.add(start)

        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()

            if (predicate(map.get(current))) {
                return current
            }

            for (next in current.neighbours()) {
                if (!isInsideMap(next)) continue
                if (!visited.add(next)) continue

                queue.addLast(next)
            }
        }

        return null
    }

    private fun isInsideMap(position: Position): Boolean =
        position.x in 0 until SimulationConfig.WIDTH &&
            position.y in 0 until SimulationConfig.HEIGHT
}
