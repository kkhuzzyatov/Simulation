package simulation.food

import simulation.general.Entity
import simulation.interfaces.Eatable

abstract class HerbivoreFood(
    position: simulation.general.Position,
) : Entity(position),
    Eatable
