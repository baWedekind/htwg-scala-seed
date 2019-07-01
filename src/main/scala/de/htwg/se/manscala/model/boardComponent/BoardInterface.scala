package de.htwg.se.manscala.model.boardComponent

import de.htwg.se.manscala.model.boardComponent.boardReverseImpl.Board
import de.htwg.se.manscala.model.pitComponent.Pit
import de.htwg.se.manscala.model.playerComponent.PlayerInterface
import de.htwg.se.manscala.model.playerComponent.playerImpl.Player

trait BoardInterface {
  /**
    * numPlayers is a configuration constant for the amount of players we expect, usually 2, always even.
    */
  val numPlayers: Int
  /**
    * A list of Players to be initialised with size numPlayers
    */
  val players: List[PlayerInterface]
  /**
    * A list of Pits.
    */
  val pits: List[Pit]
  /**
    * a getter for numPlayers
    * @return this.numPlayers
    */
  def getNumPlayers(): Int
  /**
    * A getter for the Player List
    * @return this.players
    */
  def getPlayers(): List[PlayerInterface]
  /**
    * A getter for the Pit List
    * @return this.pits
    */
  def getPits(): List[Pit]

/**
    * moves pebbles according to player's choice of pit.
    * @param chosenPit, an int representing the current player's choice.
    * @return (Boolean, Int) true if player switch should happen, the amount of stones in chosenPit.
    */
  def move(chosenPit: Int): (Boolean, Int)
/**
    * Undoes a move based on what the chosenPit was, and how many stones were returned from it.
    * Only call from UndoManager, or other situations where you know this is legal
    * @param chosenPit, an Int representing the Player's choice
    * @param stones, an Int for the amount of stones to replace
    */
  def reverseMove(chosenPit: Int, stones: Int):Unit
}

object BoardInterface {
  val SIDE_LENGTH = 7
  /**
    * Apply method for factory style action.
    */
  def apply(players: List[PlayerInterface], pits: List[Pit], typeString: String): BoardInterface = typeString match {
    case "new" => Board(players, pits)
    case "default" => new Board()
  }
}
