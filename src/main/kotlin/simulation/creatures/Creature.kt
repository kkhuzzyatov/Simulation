package simulation.creatures

import simulation.general.Entity

abstract class Creature(
    val speed: Int,
    var hp: Int
) : Entity()