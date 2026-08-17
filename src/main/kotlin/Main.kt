fun main() {
    val map = Map()
    val i = 0
    println("Current move: $i")
    for (x in 0..10) {
        for (y in 0..10) {
            if (map.map.containsKey(Coordinates(x, y))) {
                val creature = map.map[Coordinates(x, y)]
                when (creature) {
                    is Herbivore -> print("H")
                    is Predator -> print("R")
                }
            } else {
                print("O")
            }
        }
        println()
    }
}