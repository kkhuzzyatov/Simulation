package simulation.creatures

import simulation.interfaces.Attackable
import simulation.interfaces.Movable

class Predator(
    speed: Int,
    hp: Int,
    val attack : Int
) : Creature(speed, hp), Movable, Attackable {
    override fun attack(target: Creature) {
        TODO("Not yet implemented")
    }

    override fun makeMove() {
        TODO("Not yet implemented")
    }
}