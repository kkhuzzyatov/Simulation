package simulation.general

import simulation.creatures.Herbivore
import simulation.creatures.Predator
import simulation.food.HerbivoreFood

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

    fun move(
        entity: Entity,
        to: Position,
    ): Boolean {
        val target = cells[to]

        if (target != null && !canReplace(entity, target)) {
            return false
        }

        cells.remove(entity.position)

        // remove eaten entity
        cells.remove(to)

        entity.position = to

        cells[to] = entity

        return true
    }

    private fun canReplace(
        mover: Entity,
        target: Entity,
    ): Boolean =
        when {
            mover is Predator && target is Herbivore -> true
            mover is Herbivore && target is HerbivoreFood -> true
            else -> false
        }

    fun getAllEntities(): Collection<Entity> = cells.values
}
