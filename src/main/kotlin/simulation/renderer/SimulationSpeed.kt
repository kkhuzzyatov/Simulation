package simulation.renderer

enum class SimulationSpeed(
    val delay: Int,
    val animationStep: Double,
    val label: String,
) {
    X05(2000, 0.025, "x0.5"),
    X1(1000, 0.05, "x1"),
    X2(500, 0.1, "x2"),
    X3(333, 0.15, "x3"),
    X5(200, 0.1, "x5"),
    X10(100, 0.05, "x10"),
}
