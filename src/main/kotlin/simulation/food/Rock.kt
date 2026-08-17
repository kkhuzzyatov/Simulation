package simulation.food

import simulation.general.Entity

class Rock(var value :Int = 0) : Entity(), Eatable {
    override fun getValue(): Int {
        return value
    }
}