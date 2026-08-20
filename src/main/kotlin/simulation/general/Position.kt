package simulation.general

data class Position(
    val x: Int,
    val y: Int,
) {
    fun neighbours(): List<Position> =
        listOf(
            Position(x + 1, y),
            Position(x - 1, y),
            Position(x, y + 1),
            Position(x, y - 1),
        )
}
