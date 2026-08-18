package simulation.food

import simulation.creatures.Creature
import simulation.general.Entity
import simulation.interfaces.Eatable

class Grass(var value :Int = 1) : Entity(), Eatable {
    override fun eat(target: Creature) {
        TODO("Not yet implemented")
    }
}