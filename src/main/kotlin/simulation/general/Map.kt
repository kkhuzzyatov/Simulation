package simulation.general

import simulation.creatures.Creature

class Map (
    val map: MutableMap<Coordinates, Creature> = mutableMapOf(),
)