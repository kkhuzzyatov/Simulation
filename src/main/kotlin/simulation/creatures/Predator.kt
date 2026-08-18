package simulation.creatures

import simulation.interfaces.Attackable

class Predator(
    speed: Double,
    hp: Int,
    val attack : Int,
    hunger: Int,
    satiety: Double
) : Creature(speed, hp, hunger, satiety), Attackable {
    override fun attack(target: Creature) {
        TODO("Not yet implemented")
    }
}