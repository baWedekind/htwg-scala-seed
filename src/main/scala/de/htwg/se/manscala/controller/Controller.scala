package de.htwg.se.manscala.controller

import de.htwg.se.manscala.model.{Board, MancalaPit, Pit, Player}
import de.htwg.se.manscala.util.Observable

class Controller(var board: Board, var currPlayer: Int) extends Observable {
  private def switchPlayer(): Unit = {
    if (currPlayer == board.numPlayers - 1) {
      currPlayer = 0
    } else {
      currPlayer += 1
    }
  }

  def createDefaultBoard():Unit = {
    board = new Board()
    notifyObservers()
  }

  def boardToString: String = board.toString

  def move(chosenPit: Int): Boolean = {
    if (!checkMove(chosenPit)) {
      false
    } else {
      val switch: Boolean = this.board.move(chosenPit)
      if (switch) {
        this.switchPlayer()
      }
      notifyObservers()
      true
    }
  }

  def checkMove(chosenPit: Int): Boolean = {
    if (chosenPit / Board.SIDE_LENGTH != currPlayer) {
      false
    } else if (!(chosenPit >= 0 && chosenPit < board.numPlayers * Board.SIDE_LENGTH) ||
      board.pits(chosenPit).isInstanceOf[MancalaPit]) {
      false
    } else {
      true
    }
  }
}