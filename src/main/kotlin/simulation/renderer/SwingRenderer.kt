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

    private lateinit var turnLabel: JLabel
    private lateinit var creatureLabel: JLabel
    private lateinit var fieldPanel: JPanel

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

        creatureLabel =
            JLabel(
                "",
                JLabel.CENTER,
            ).apply {
                font = Font("Arial", Font.PLAIN, 16)
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
        updateCreatureCounter(map)

        val nextTurnButton =
            JButton("Next Turn").apply {
                font = Font("Arial", Font.BOLD, 14)

                addActionListener {
                    SimulationEngine.nextTurn(map)

                    turn++
                    turnLabel.text = "Turn: $turn"

                    updateCreatureCounter(map)
                    fieldPanel.repaint()
                }
            }

        frame.layout = BorderLayout()

        val topPanel = JPanel(BorderLayout())

        topPanel.add(
            turnLabel,
            BorderLayout.NORTH,
        )

        topPanel.add(
            creatureLabel,
            BorderLayout.SOUTH,
        )

        frame.layout = BorderLayout()

        frame.add(
            topPanel,
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

    private fun updateCreatureCounter(map: Map) {
        var herbivores = 0
        var predators = 0

        for (y in 0 until SimulationConfig.HEIGHT) {
            for (x in 0 until SimulationConfig.WIDTH) {
                when (map.get(Position(x, y))) {
                    is Herbivore -> herbivores++
                    is Predator -> predators++
                }
            }
        }

        creatureLabel.text =
            "Herbivores: $herbivores    Predators: $predators"
    }
}
