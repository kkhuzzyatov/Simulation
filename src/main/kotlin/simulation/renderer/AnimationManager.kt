package simulation.renderer

import simulation.config.ImageConfig
import simulation.creatures.Herbivore
import simulation.creatures.Predator
import simulation.food.Grass
import simulation.food.Tree
import simulation.general.Entity
import simulation.general.Map
import simulation.general.Position
import simulation.landscape.Rock

class AnimationManager {
    private val sprites = mutableListOf<Sprite>()

    private var previousPositions =
        mutableMapOf<Entity, Position>()

    fun captureBeforeTurn(map: Map) {
        previousPositions.clear()

        map
            .getAllEntities()
            .forEach {
                previousPositions[it] = it.position
            }
    }

    fun createAnimations(map: Map) {
        sprites.clear()

        map
            .getAllEntities()
            .forEach { entity ->

                val oldPosition =
                    previousPositions[entity]
                        ?: entity.position

                if (oldPosition != entity.position) {
                    sprites.add(
                        Sprite(
                            entity,
                            getIcon(entity),
                            oldPosition,
                            entity.position,
                        ),
                    )
                }
            }
    }

    fun update() {
        sprites.forEach {
            it.update(0.05)
        }

        sprites.removeIf {
            it.isFinished()
        }
    }

    fun getSprites(): List<Sprite> = sprites

    fun isRunning(): Boolean = sprites.isNotEmpty()

    private fun getIcon(entity: Entity): String =
        when (entity) {
            is Herbivore -> ImageConfig.HERBIVORE
            is Predator -> ImageConfig.PREDATOR
            is Grass -> ImageConfig.GRASS
            is Tree -> ImageConfig.TREE
            is Rock -> ImageConfig.ROCK
            else -> " "
        }
}
