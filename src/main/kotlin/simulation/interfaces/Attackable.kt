package simulation.interfaces

import simulation.creatures.Creature

interface Attackable {
    fun attack(target: Creature)
}
