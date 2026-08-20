package simulation.renderer

import simulation.config.ImageConfig
import simulation.config.SimulationConfig
import simulation.creatures.Creature
import simulation.creatures.Herbivore
import simulation.creatures.Predator
import simulation.food.Grass
import simulation.food.Tree
import simulation.general.Map
import simulation.general.Position
import simulation.landscape.Rock
import java.awt.Color
import java.awt.Font
import java.awt.Graphics
import javax.swing.JPanel

class SimulationPanel(
    private val map: Map,
    private val animationManager: AnimationManager,
) : JPanel() {
    companion object {
        const val CELL_SIZE = 30
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)

        g.font =
            Font(
                "Segoe UI Emoji",
                Font.PLAIN,
                22,
            )

        drawStaticEntities(g)

        drawAnimatedEntities(g)
    }

    private fun drawStaticEntities(g: Graphics) {
        for (y in 0 until SimulationConfig.HEIGHT) {
            for (x in 0 until SimulationConfig.WIDTH) {
                val entity =
                    map.get(Position(x, y))

                if (entity != null &&
                    animationManager
                        .getSprites()
                        .any { it.entity == entity }
                ) {
                    continue
                }

                g.drawString(
                    getIcon(entity),
                    x * CELL_SIZE + 5,
                    y * CELL_SIZE + 23,
                )

                if (entity is Creature) {
                    drawHpBar(
                        g,
                        x,
                        y,
                        entity,
                    )

                    drawSatietyBar(
                        g,
                        x,
                        y,
                        entity,
                    )
                }
            }
        }
    }

    private fun drawAnimatedEntities(g: Graphics) {
        animationManager
            .getSprites()
            .forEach {
                g.drawString(
                    getIcon(it.entity),
                    it.drawX(CELL_SIZE) + 5,
                    it.drawY(CELL_SIZE) + 23,
                )
            }
    }

    private fun drawHpBar(
        g: Graphics,
        x: Int,
        y: Int,
        creature: Creature,
    ) {
        val oldColor = g.color

        val barX = x * CELL_SIZE + 5
        val barY = y * CELL_SIZE + 2
        val barWidth = CELL_SIZE - 10
        val barHeight = 3

        val hpPercent = creature.getHpPercent()
        val hpWidth = (barWidth * hpPercent).toInt()

        // White background
        g.color = Color.WHITE
        g.fillRect(
            barX,
            barY,
            barWidth,
            barHeight,
        )

        // HP
        g.color = Color.GREEN
        g.fillRect(
            barX,
            barY,
            hpWidth,
            barHeight,
        )

        // Black frame
        g.color = Color.BLACK
        g.drawRect(
            barX,
            barY,
            barWidth,
            barHeight,
        )

        g.color = oldColor
    }

    private fun drawSatietyBar(
        g: Graphics,
        x: Int,
        y: Int,
        creature: Creature,
    ) {
        val oldColor = g.color

        val barX = x * CELL_SIZE + 5
        val barY = y * CELL_SIZE + 6
        val barWidth = CELL_SIZE - 10
        val barHeight = 3

        val satietyPercent = creature.getSatietyPercent()
        val satietyWidth = (barWidth * satietyPercent).toInt()

        // White background
        g.color = Color.WHITE
        g.fillRect(
            barX,
            barY,
            barWidth,
            barHeight,
        )

        // Satiety
        g.color = Color.BLUE
        g.fillRect(
            barX,
            barY,
            satietyWidth,
            barHeight,
        )

        // Black frame
        g.color = Color.BLACK
        g.drawRect(
            barX,
            barY,
            barWidth,
            barHeight,
        )

        g.color = oldColor
    }

    private fun getIcon(entity: Any?): String =
        when (entity) {
            is Herbivore -> ImageConfig.HERBIVORE
            is Predator -> ImageConfig.PREDATOR
            is Grass -> ImageConfig.GRASS
            is Tree -> ImageConfig.TREE
            is Rock -> ImageConfig.ROCK
            else -> " "
        }
}
