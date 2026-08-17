package simulation.food

import simulation.general.Entity

class Tree(var value :Int = 2) : Entity(), Eatable {
    override fun getValue(): Int {
        return value
    }
}