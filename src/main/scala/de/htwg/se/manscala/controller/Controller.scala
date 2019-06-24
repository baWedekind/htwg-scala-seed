package de.htwg.se.manscala.controller

import de.htwg.se.manscala.model.{Board, MancalaPit, Pit, Player}
import de.htwg.se.manscala.util.Observable

class Controller(val undoManager: UndoManager, var board: Board, var currPlayer: Int) extends Observable {
  def switchPlayer(): Unit = {
    if (currPlayer == board.numPlayers - 1) {
      currPlayer = 0
    } else {
      currPlayer += 1
    }
  }

  def getCurrentPlayer(): Player = {
    this.board.players.filter(p => p.id == currPlayer).head
  }

  def createDefaultBoard():Unit = {
    board = new Board()
    notifyObservers()
  }

  def boardToString: String = board.toString

  def executeCommand(command: Command): Boolean = {
    undoManager.doStep(command)
  }

  // I could use a Strategy pattern to make an "AI" choose moves
  def move(chosenPit: Int): Boolean = {
    if (!checkMove(chosenPit)) {
      false
    } else {
      val (switch, _) = this.board.move(chosenPit)
      if (switch) {
        this.switchPlayer()
      }
      notifyObservers()
      true
    }
  }

  //TODO: Implement a Command pattern with and undo and redo stack
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