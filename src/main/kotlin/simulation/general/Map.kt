package simulation.general

import simulation.creatures.Creature
import simulation.creatures.Herbivore
import simulation.creatures.Predator
import simulation.food.HerbivoreFood
import simulation.interfaces.Eatable

class Map(
    private val cells: MutableMap<Position, Entity> = mutableMapOf(),
) {
    fun get(coordinate: Position): Entity? = cells[coordinate]

    fun isEmpty(coordinate: Position): Boolean = !cells.containsKey(coordinate)

    fun put(
        coordinate: Position,
        entity: Entity,
    ) {
        cells[coordinate] = entity
    }

    fun remove(coordinate: Position): Entity? = cells.remove(coordinate)

    fun move(
        creature: Creature,
        to: Position,
    ): Boolean {
        val target = cells[to]

        if (target != null && !canReplace(creature, target)) {
            return false
        }

        if (target is Eatable && canReplace(creature, target)) {
            creature.satiety += target.getValue()
        }

        cells.remove(creature.position)

        // remove eaten entity
        cells.remove(to)

        creature.position = to

        cells[to] = creature

        return true
    }

    private fun canReplace(
        mover: Entity,
        target: Entity,
    ): Boolean =
        when (mover) {
            is Herbivore if target is HerbivoreFood -> true
            else -> false
        }

    fun getAllEntities(): Collection<Entity> = cells.values
}
