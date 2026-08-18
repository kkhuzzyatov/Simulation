package simulation.creatures

import simulation.general.Coordinates
import simulation.general.Entity
import simulation.interfaces.Movable

abstract class Creature(
    val speed: Int,
    var hp: Int,
    var hunger: Int,
    var satiety: Double,
    position: Coordinates
) : Entity(position), Movable {
    override fun makeMove() {
        TODO("Not yet implemented")
    }
}