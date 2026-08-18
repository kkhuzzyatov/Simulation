package simulation.general

class Map(
    private val cells: MutableMap<Coordinates, Entity> = mutableMapOf(),
) {

    fun get(coordinate: Coordinates): Entity? =
        cells[coordinate]

    fun isEmpty(coordinate: Coordinates): Boolean =
        !cells.containsKey(coordinate)

    fun remove(coordinate: Coordinates) {
        cells.remove(coordinate)
    }

    fun put(coordinate: Coordinates, entity: Entity) {
        cells[coordinate] = entity
    }

    fun move(from: Coordinates, to: Coordinates) {
        val entity = cells[from] ?: return
        cells.remove(from)
        cells[to] = entity
    }
}