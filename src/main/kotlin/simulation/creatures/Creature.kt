package simulation.creatures

import simulation.general.Entity
import simulation.general.Map
import simulation.general.Position
import simulation.interfaces.Movable
import kotlin.math.round
import kotlin.math.roundToInt

abstract class Creature(
    position: Position,
    val speed: Int,
    val maxHp: Int,
    val hunger: Int,
    var currentHp: Int = maxHp,
    var satiety: Int = (hunger * 0.6).roundToInt(),
) : Entity(position),
    Movable {
    fun getHpPercent(): Double =
        (currentHp.toDouble() / maxHp)
            .coerceIn(0.0, 1.0)

    fun getSatietyPercent(): Double =
        (satiety.toDouble() / hunger)
            .coerceIn(0.0, 1.0)
}
