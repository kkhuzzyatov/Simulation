package simulation.food

import simulation.creatures.Creature
import simulation.general.Entity
import simulation.interfaces.Eatable

class Tree(var value :Int = 2) : Entity(), Eatable {
    override fun eat(target: Creature) {
        TODO("Not yet implemented")
    }
}