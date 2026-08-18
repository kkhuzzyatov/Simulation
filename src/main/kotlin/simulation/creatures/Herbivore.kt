package simulation.creatures

import simulation.general.Coordinates

class Herbivore(
    speed: Int,
    hp: Int,
    hunger: Int,
    satiety: Double,
    position: Coordinates
) : Creature(speed, hp, hunger, satiety, position)