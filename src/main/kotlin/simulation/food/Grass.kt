package simulation.food

import simulation.creatures.Creature
import simulation.general.Position

class Grass(
    position: Position,
    var value: Int = 1,
) : HerbivoreFood(position) {
    override fun eat(target: Creature) {
        TODO("Not yet implemented")
    }
}
