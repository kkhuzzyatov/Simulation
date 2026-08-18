package simulation.creatures

import simulation.general.Entity
import simulation.general.Map
import simulation.interfaces.Movable

abstract class Creature(
    val speed: Double,
    var hp: Int,
    var hunger: Int,
    var satiety: Double
) : Entity(), Movable {
    override fun move(map: Map) {
        TODO("Not yet implemented")
    }
}