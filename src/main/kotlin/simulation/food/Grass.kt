package simulation.food

import simulation.config.SimulationConfig
import simulation.creatures.Creature
import simulation.general.Position

class Grass(
    position: Position,
) : HerbivoreFood(position) {
    override fun getValue(): Int = SimulationConfig.GRASS_VALUE
}
