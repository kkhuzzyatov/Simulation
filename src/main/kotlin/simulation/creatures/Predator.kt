package simulation.creatures

import simulation.general.Coordinates
import simulation.interfaces.Attackable

class Predator(
    speed: Int,
    hp: Int,
    val attack : Int,
    hunger: Int,
    satiety: Double,
    position: Coordinates
) : Creature(speed, hp, hunger, satiety, position), Attackable {
    override fun attack(target: Creature) {
        TODO("Not yet implemented")
    }
}