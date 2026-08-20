package simulation.engine

import simulation.creatures.Creature
import simulation.general.Map

object SimulationEngine {
    fun nextTurn(map: Map) {
        moveCreatures(map)
        generateFood()
        updateHunger()
        reproduce()
    }

    private fun moveCreatures(map: Map) {
        map
            .getAllEntities()
            .filterIsInstance<Creature>()
            .forEach { creature ->
                creature.move(map)
            }
    }

    private fun generateFood() {
    }

    private fun updateHunger() {
    }

    private fun reproduce() {
    }
}
