package simulation.food

import simulation.creatures.Creature
import simulation.general.Position

class Tree(
    position: Position,
    var value: Int = 2,
) : HerbivoreFood(position) {
    override fun eat(target: Creature) {
        TODO("Not yet implemented")
    }
}
