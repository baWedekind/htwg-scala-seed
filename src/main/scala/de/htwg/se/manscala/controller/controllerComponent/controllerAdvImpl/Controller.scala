package de.htwg.se.manscala.controller.controllerComponent.controllerAdvImpl

import de.htwg.se.manscala.controller.controllerComponent.{Command, ControllerInterface}
import de.htwg.se.manscala.model.boardComponent.boardReverseImpl.Board
import de.htwg.se.manscala.model.pitComponent.pitMancalaImpl.MancalaPit
import de.htwg.se.manscala.model.playerComponent.Player
import de.htwg.se.manscala.util.{Observable, UndoManager}

class Controller(val undoManager: UndoManager, var board: Board, var currPlayer: Int) extends Observable with ControllerInterface {
  private var notifier = ""

  override def getNotifier():String = this.notifier

  override def switchPlayer(): Unit = {
    if (currPlayer == board.numPlayers - 1) {
      currPlayer = 0
    } else {
      currPlayer += 1
    }
  }

  override def getCurrentPlayer(): Player = {
    this.board.players.filter(p => p.id == currPlayer).head
  }

  override def createDefaultBoard():Unit = {
    board = new Board()
    notifyObservers()
  }

  override def boardToString: String = board.toString

  override def executeCommand(command: Command): Boolean = {
    val success = undoManager.doStep(command)
    if (!success) {
      this.notifier = "Please choose a valid Pit"
    } else {
      this.notifier = ""
    }
    notifyObservers()
    success
  }

  // I could use a Strategy pattern to make an "AI" choose moves
  override def move(chosenPit: Int): Boolean = {
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

  override def checkMove(chosenPit: Int): Boolean = {
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