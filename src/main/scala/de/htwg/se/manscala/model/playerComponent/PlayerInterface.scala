package de.htwg.se.manscala.model.playerComponent

import de.htwg.se.manscala.model.playerComponent.playerImpl.Player

trait PlayerInterface {
  val name: String
  val id: Int

  def getName(): String
  def getId(): Int
}

object PlayerInterface {
  /**
    * Apply method for factory style action.
    */
  def apply(name: String, id: Int): PlayerInterface = if (true) {
    Player(name, id)
  } else {
    Player(name, id)
  }
}