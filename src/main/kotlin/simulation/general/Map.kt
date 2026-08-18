package simulation.general

class Map (
    private val map: MutableMap<Coordinates, Entity> = mutableMapOf(),
) {
    fun get(coordinate: Coordinates): Entity? {
        return map[coordinate]
    }

    fun clear(coordinate: Coordinates) {
        map.remove(coordinate)
    }

    fun update(coordinate: Coordinates, entity: Entity) {
        map[coordinate] = entity
    }
}