package de.htwg.se.manscala.model.boardComponent

trait BoardInterface {
  /**
    * numPlayers is a configuration constant for the amount of players we expect, usually 2, always even.
    */
  val numPlayers: Int
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
  def reverseMove(chosenPit: Int, stones: Int):Unit}
