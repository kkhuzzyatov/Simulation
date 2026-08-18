package simulation.food

import simulation.general.Entity
import simulation.interfaces.Eatable

class Grass(var value :Int = 1) : Entity(), Eatable {
    override fun getValue(): Int {
        return value
    }
}