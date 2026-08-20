package simulation.creatures

import simulation.engine.PathFinder
import simulation.general.Map
import simulation.general.Position
import simulation.interfaces.Eatable

class Herbivore(
    position: Position,
    speed: Int,
    hp: Int,
    hunger: Int,
    satiety: Double,
) : Creature(position, speed, hp, hunger, satiety) {
    Eatable {
    override fun move(map: Map) {
        val nearestFood =
            PathFinder.findNearestFoodForHerbivore(position, map)
                ?: return

        moveTowards(nearestFood, map)
    }

    override fun getValue(): Int {
        return SimulationConfig.HERBIVORE_VALUE
    }
}
