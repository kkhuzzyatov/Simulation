package simulation.food

import simulation.config.SimulationConfig
import simulation.creatures.Creature
import simulation.general.Position

class Tree(
    position: Position,
) : HerbivoreFood(position) {
    override fun getValue(): Int = SimulationConfig.TREE_VALUE
}
