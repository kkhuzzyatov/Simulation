package simulation.renderer

import simulation.config.SimulationConfig
import simulation.engine.SimulationEngine
import simulation.general.Map
import java.awt.BorderLayout
import java.awt.Font
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.Timer

object SwingRenderer {
    private var turn = 0

    fun render(map: Map) {
        val frame =
            JFrame("Simulation")

        val animationManager =
            AnimationManager()

        val panel =
            SimulationPanel(
                map,
                animationManager,
            )

        val turnLabel =
            JLabel(
                "Turn: 0",
                JLabel.CENTER,
            ).apply {
                font =
                    Font(
                        "Arial",
                        Font.BOLD,
                        18,
                    )
            }

        val button =
            JButton("Next Turn")

        lateinit var timer: Timer

        timer =
            Timer(16) {
                animationManager.update()
                panel.repaint()

                if (!animationManager.isRunning()) {
                    timer.stop()
                }
            }

        button.addActionListener {
            animationManager.captureBeforeTurn(map)

            SimulationEngine.nextTurn(map)

            animationManager.createAnimations(map)

            turn++

            turnLabel.text =
                "Turn: $turn"

            timer.start()
        }

        val top =
            JPanel(BorderLayout())

        top.add(
            turnLabel,
            BorderLayout.NORTH,
        )

        frame.layout =
            BorderLayout()

        frame.add(
            top,
            BorderLayout.NORTH,
        )

        frame.add(
            panel,
            BorderLayout.CENTER,
        )

        frame.add(
            button,
            BorderLayout.SOUTH,
        )

        frame.setSize(
            SimulationConfig.WIDTH *
                SimulationPanel.CELL_SIZE + 20,
            SimulationConfig.HEIGHT *
                SimulationPanel.CELL_SIZE + 100,
        )

        frame.defaultCloseOperation =
            JFrame.EXIT_ON_CLOSE

        frame.isVisible = true
    }
}
