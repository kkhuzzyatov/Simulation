package simulation.interfaces

import simulation.creatures.Creature

interface Eatable {
    fun eat(target: Creature)
}