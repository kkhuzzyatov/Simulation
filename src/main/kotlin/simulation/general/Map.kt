package simulation.general

class Map(
    private val cells: MutableMap<Position, Entity> = mutableMapOf(),
) {

    fun get(coordinate: Position): Entity? =
        cells[coordinate]

    fun isEmpty(coordinate: Position): Boolean =
        !cells.containsKey(coordinate)

    fun remove(coordinate: Position) {
        cells.remove(coordinate)
    }

    fun put(coordinate: Position, entity: Entity) {
        cells[coordinate] = entity
    }

    fun move(from: Position, to: Position) {
        val entity = cells[from] ?: return
        cells.remove(from)
        cells[to] = entity
    }
}