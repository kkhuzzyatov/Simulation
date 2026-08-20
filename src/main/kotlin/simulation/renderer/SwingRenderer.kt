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

    private var simulationTimer: Timer? = null
    val herbivoreLabel =
        JLabel("Herbivores: 0")
    val predatorLabel =
        JLabel("Predators: 0")

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

        val runButton =
            JButton("Run")

        val speedButton =
            JButton(
                SimulationController.speed.label,
            )

        val animationTimer =
            Timer(16) {
                animationManager.update(
                    SimulationController.speed.animationStep,
                )

                panel.repaint()
            }

        animationTimer.start()

        runButton.addActionListener {
            SimulationController.running =
                !SimulationController.running

            if (SimulationController.running) {
                runButton.text = "Pause"

                startSimulationTimer(
                    map,
                    animationManager,
                    panel,
                    turnLabel,
                )
            } else {
                runButton.text = "Run"

                simulationTimer?.stop()
            }
        }

        speedButton.addActionListener {
            SimulationController.speed =
                when (SimulationController.speed) {
                    SimulationSpeed.X05 -> {
                        SimulationSpeed.X1
                    }

                    SimulationSpeed.X1 -> {
                        SimulationSpeed.X2
                    }

                    SimulationSpeed.X2 -> {
                        SimulationSpeed.X3
                    }

                    SimulationSpeed.X3 -> {
                        SimulationSpeed.X5
                    }

                    SimulationSpeed.X5 -> {
                        SimulationSpeed.X10
                    }

                    SimulationSpeed.X10 -> {
                        SimulationSpeed.X05
                    }
                }

            speedButton.text =
                SimulationController.speed.label

            if (SimulationController.running) {
                startSimulationTimer(
                    map,
                    animationManager,
                    panel,
                    turnLabel,
                )
            }
        }

        val top =
            JPanel(BorderLayout())

        val stats =
            JPanel()

        stats.add(turnLabel)
        stats.add(herbivoreLabel)
        stats.add(predatorLabel)

        top.add(
            stats,
            BorderLayout.NORTH,
        )

        val controls =
            JPanel()

        controls.add(runButton)

        controls.add(speedButton)

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
            controls,
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

    private fun updateCreatureCounters(
        map: Map,
        herbivoreLabel: JLabel,
        predatorLabel: JLabel,
    ) {
        val herbivores =
            map
                .getAllEntities()
                .count { it is simulation.creatures.Herbivore }

        val predators =
            map
                .getAllEntities()
                .count { it is simulation.creatures.Predator }

        herbivoreLabel.text =
            "Herbivores: $herbivores"

        predatorLabel.text =
            "Predators: $predators"
    }

    private fun startSimulationTimer(
        map: Map,
        animationManager: AnimationManager,
        panel: SimulationPanel,
        turnLabel: JLabel,
    ) {
        simulationTimer?.stop()

        simulationTimer =
            Timer(
                SimulationController.speed.delay,
            ) {
                if (!animationManager.isRunning()) {
                    animationManager.captureBeforeTurn(map)

                    SimulationEngine.nextTurn(map)

                    animationManager.createAnimations(map)

                    turn++

                    turnLabel.text =
                        "Turn: $turn"
                }

                updateCreatureCounters(
                    map,
                    herbivoreLabel,
                    predatorLabel,
                )

                panel.repaint()
            }

        simulationTimer?.start()
    }
}
