package simulation.renderer

import simulation.config.ImageConfig
import simulation.config.SimulationConfig
import simulation.creatures.Herbivore
import simulation.creatures.Predator
import simulation.engine.SimulationEngine
import simulation.food.Grass
import simulation.food.Tree
import simulation.general.Map
import simulation.general.Position
import simulation.landscape.Rock
import java.awt.BorderLayout
import java.awt.Font
import java.awt.Graphics
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel

object SwingRenderer {
    private const val CELL_SIZE = 30

    private var turn = 0

    private lateinit var fieldPanel: JPanel
    private lateinit var turnLabel: JLabel

    fun render(map: Map) {
        val frame = JFrame("Simulation")

        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE

        frame.setSize(
            SimulationConfig.WIDTH * CELL_SIZE + 20,
            SimulationConfig.HEIGHT * CELL_SIZE + 100,
        )

        turnLabel =
            JLabel(
                "Turn: $turn",
                JLabel.CENTER,
            ).apply {
                font = Font("Arial", Font.BOLD, 18)
            }

        fieldPanel =
            object : JPanel() {
                override fun paintComponent(g: Graphics) {
                    super.paintComponent(g)

                    g.font = Font("Segoe UI Emoji", Font.PLAIN, 22)

                    for (y in 0 until SimulationConfig.HEIGHT) {
                        for (x in 0 until SimulationConfig.WIDTH) {
                            val entity = map.get(Position(x, y))

                            val icon =
                                when (entity) {
                                    is Herbivore -> ImageConfig.HERBIVORE
                                    is Predator -> ImageConfig.PREDATOR
                                    is Grass -> ImageConfig.GRASS
                                    is Tree -> ImageConfig.TREE
                                    is Rock -> ImageConfig.ROCK
                                    else -> " "
                                }

                            g.drawString(
                                icon,
                                x * CELL_SIZE + 5,
                                y * CELL_SIZE + 23,
                            )
                        }
                    }
                }
            }

        val nextTurnButton =
            JButton("Next Turn").apply {
                font = Font("Arial", Font.BOLD, 14)

                addActionListener {
                    SimulationEngine.nextTurn(map)

                    turn++
                    turnLabel.text = "Turn: $turn"

                    fieldPanel.repaint()
                }
            }

        frame.layout = BorderLayout()

        frame.add(
            turnLabel,
            BorderLayout.NORTH,
        )

        frame.add(
            fieldPanel,
            BorderLayout.CENTER,
        )

        frame.add(
            nextTurnButton,
            BorderLayout.SOUTH,
        )

        frame.isVisible = true
    }
}
