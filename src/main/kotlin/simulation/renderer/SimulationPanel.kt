package simulation.renderer

import simulation.config.ImageConfig
import simulation.config.SimulationConfig
import simulation.creatures.Herbivore
import simulation.creatures.Predator
import simulation.food.Grass
import simulation.food.Tree
import simulation.general.Map
import simulation.general.Position
import simulation.landscape.Rock
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
