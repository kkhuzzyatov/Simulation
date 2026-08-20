package simulation

import simulation.engine.MapInitializer
import simulation.general.Map
import simulation.renderer.SwingRenderer

fun main() {
    val map: Map = MapInitializer.init()

    SwingRenderer.render(map)
}
