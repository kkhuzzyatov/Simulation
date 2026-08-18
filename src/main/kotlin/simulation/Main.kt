package simulation

import simulation.creatures.Herbivore
import simulation.creatures.Predator
import simulation.food.Grass
import simulation.food.Tree
import simulation.general.Coordinates
import simulation.general.Map
import simulation.landscape.Rock
import simulation.config.Signs

fun main() {
    val map = Map()
    val i = 0
    println("Current move: $i")
    for (x in 0..10) {
        for (y in 0..10) {
            when (map.get(Coordinates(x, y))) {
                is Herbivore -> print(Signs.HERBIVORE)
                is Predator -> print(Signs.PREDATOR)
                is Grass -> print(Signs.GRASS)
                is Tree -> print(Signs.TREE)
                is Rock -> print(Signs.ROCK)
                else -> print(" ")
            }
        }
        println()
    }
}