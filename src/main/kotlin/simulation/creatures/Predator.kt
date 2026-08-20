package simulation.creatures

import simulation.engine.PathFinder
import simulation.general.Map
import simulation.general.Position
import simulation.interfaces.Attackable

class Predator(
    position: Position,
    speed: Int,
    hp: Int,
    val attack: Int,
    hunger: Int,
    satiety: Double,
) : Creature(position, speed, hp, hunger, satiety),
    Attackable {
    override fun attack(target: Creature) {
        TODO("Not yet implemented")
    }

    override fun move(map: Map) {
        val nearestHerbivore =
            PathFinder.findNearestHerbivore(position, map)
                ?: return

        moveTowards(nearestHerbivore, map)
    }
}
