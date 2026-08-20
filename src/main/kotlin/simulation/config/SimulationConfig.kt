package simulation.config

object SimulationConfig {
    // Map size
    const val WIDTH = 25
    const val HEIGHT = 25

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

    // Satiety setting
    const val SATIETY_LOSS_MULTIPLIER = 0.05

    // Hungry limits settings
    const val STARVING_SATIETY_PERCENT = 40
    const val CRITICAL_SATIETY_PERCENT = 15

    // Hungry damage settings
    const val STARVATION_DAMAGE_MULTIPLIER = 0.05
    const val CRITICAL_STARVATION_DAMAGE_MULTIPLIER = 0.15

    // Recovery settings
    const val RECOVERY_SATIETY_PERCENT = 80
    const val HP_RECOVERY_MULTIPLIER = 0.05

    // Herbivore settings
    const val HERBIVORE_SPEED = 2
    const val HERBIVORE_MAX_HP = 100
    const val HERBIVORE_HUNGER = 140

    // Predator settings
    const val PREDATOR_SPEED = 3
    const val PREDATOR_MAX_HP = 150
    const val PREDATOR_DAMAGE = 30
    const val PREDATOR_HUNGER = 300

    // Food per turn generation settings
    const val MIN_GRASS_PER_TURN = 3
    const val MAX_GRASS_PER_TURN = 8

    const val MIN_TREES_PER_TURN = 0
    const val MAX_TREES_PER_TURN = 2

    // Reproduction settings
    const val REPRODUCTION_SATIETY_PERCENT = 75
    const val REPRODUCTION_SATIETY_TRANSFER_PERCENT = 50

    // Mutation settings
    const val HP_MUTATION = 10
    const val DAMAGE_MUTATION = 5
}
