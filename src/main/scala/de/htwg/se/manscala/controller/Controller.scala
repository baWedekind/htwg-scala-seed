package de.htwg.se.manscala.controller

import de.htwg.se.manscala.model.{Board, Pit, Player}
import de.htwg.se.manscala.util.Observable

class Controller(var board: Board, var currPlayer: Player) extends Observable {
  def createDefaultBoard(size: Int):Unit = {
    board = new Board()
    notifyObservers()
  }

  def boardToString: String = Board.toString

  def move(chosenPit: Int): Boolean = {
    if (!checkMove(chosenPit)) {
      false
    } else {
      val success: Boolean = this.board.move(chosenPit)
      notifyObservers()
      success
    }
  }

  def checkMove(chosenPit: Int): Boolean = {
    if (chosenPit / Board.SIDE_LENGTH != currPlayer.id) {
      false
    } else {
      true
    }

  }
}