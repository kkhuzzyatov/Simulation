package simulation.landscape

import simulation.general.Entity
import simulation.general.Position

class Rock(
    position: Position,
    var value: Int = 0,
) : Entity(position)
