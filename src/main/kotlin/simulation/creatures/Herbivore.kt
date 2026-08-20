package simulation.creatures

import simulation.engine.PathFinder
import simulation.general.Map
import simulation.general.Position

class Herbivore(
    position: Position,
    speed: Int,
    hp: Int,
    hunger: Int,
    satiety: Double,
) : Creature(position, speed, hp, hunger, satiety) {
    override fun move(map: Map) {
        val nearestFood =
            PathFinder.findNearestFoodForHerbivore(position, map)
                ?: return

        moveTowards(nearestFood, map)
    }
}
