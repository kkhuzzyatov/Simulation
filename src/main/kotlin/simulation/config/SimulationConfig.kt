package simulation.config

object SimulationConfig {
    // Map size
    const val WIDTH = 20
    const val HEIGHT = 20

    // Creature generation settings
    const val START_HERBIVORES = 40
    const val START_PREDATORS = 10

    // Food start generation settings
    const val START_GRASS = 30
    const val START_TREES = 8
    const val START_ROCKS = 4

    // Food value settings
    const val GRASS_VALUE = 8
    const val TREE_VALUE = 30
    const val HERBIVORE_VALUE = 60
    // Herbivore settings
    const val HERBIVORE_SPEED = 2
    const val HERBIVORE_MAX_HP = 100
    const val HERBIVORE_HUNGER = 140

    // Predator settings
    const val PREDATOR_SPEED = 3
    const val PREDATOR_MAX_HP = 150
    const val PREDATOR_DAMAGE = 30
    const val PREDATOR_HUNGER = 300
}