package simulation.renderer

import simulation.general.Entity
import simulation.general.Position

class Sprite(
    val entity: Entity,
    val icon: String,
    private val from: Position,
    private val to: Position,
) {
    var progress = 0.0

    fun update(step: Double) {
        progress = (progress + step).coerceAtMost(1.0)
    }

    fun isFinished(): Boolean = progress >= 1.0

    fun drawX(cellSize: Int): Int {
        val x =
            from.x + (to.x - from.x) * progress

        return (x * cellSize).toInt()
    }

    fun drawY(cellSize: Int): Int {
        val y =
            from.y + (to.y - from.y) * progress

        return (y * cellSize).toInt()
    }
}
