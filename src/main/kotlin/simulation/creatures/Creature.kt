package simulation.creatures

import simulation.general.Entity
import simulation.general.Map
import simulation.general.Position
import simulation.interfaces.Movable

abstract class Creature(
    position: Position,
    val speed: Int,
    var hp: Int,
    var hunger: Int,
    var satiety: Double,
) : Entity(position),
    Movable {
    protected fun moveTowards(
        target: Position,
        map: Map,
    ): Boolean {
        repeat(speed) {
            if (position == target) {
                return true
            }

            val dx = target.x - position.x
            val dy = target.y - position.y

            val possibleMoves = mutableListOf<Position>()

            // Prefer horizontal movement
            if (dx != 0) {
                possibleMoves.add(
                    Position(
                        position.x + dx.coerceIn(-1, 1),
                        position.y,
                    ),
                )
            }

            // Try vertical movement
            if (dy != 0) {
                possibleMoves.add(
                    Position(
                        position.x,
                        position.y + dy.coerceIn(-1, 1),
                    ),
                )
            }

            // If both directions are blocked, stop
            val moved =
                possibleMoves.any { next ->
                    map.move(this, next)
                }

            if (!moved) {
                return false
            }
        }

        return position == target
    }
}
