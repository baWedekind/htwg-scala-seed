package de.htwg.se.manscala.controller.controllerComponent.controllerAdvImpl

import com.google.inject.name.Names
import com.google.inject.{Guice, Inject, Injector}
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.manscala.ManscalaModule
import de.htwg.se.manscala.controller.controllerComponent.{Command, ControllerInterface}
import de.htwg.se.manscala.model.boardComponent.BoardInterface
import de.htwg.se.manscala.model.pitComponent.pitMancalaImpl.MancalaPit
import de.htwg.se.manscala.model.playerComponent.PlayerInterface
import de.htwg.se.manscala.util.{Observable, UndoManager}

class Controller @Inject() (val undoManager: UndoManager, var board: BoardInterface, var currPlayer: Int = 0) extends Observable with
  ControllerInterface {
  this.currPlayer = board.getPlayers().head.getId()
  private var notifier = ""
  val injector: Injector = Guice.createInjector(new ManscalaModule)

  override def getNotifier(): String = this.notifier

  override def switchPlayer(): Unit = {
    if (currPlayer == board.numPlayers - 1) {
      currPlayer = 0
    } else {
      currPlayer += 1
    }
  }

  override def getCurrentPlayer(): PlayerInterface = {
    this.board.getPlayers().filter(p => p.getId() == currPlayer).head
  }

  override def createDefaultBoard(): Unit = {
    // Create a default Board
//    board = BoardInterface.apply(Nil, Nil, "default")
    // with DI
    board = injector.instance[BoardInterface](Names.named("default"))
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
    if (chosenPit / BoardInterface.SIDE_LENGTH != currPlayer) {
      false
    } else if (!(chosenPit >= 0 && chosenPit < board.numPlayers * BoardInterface.SIDE_LENGTH) ||
      board.pits(chosenPit).isInstanceOf[MancalaPit]) {
      false
    } else {
      true
    }
  }

  /**
    * a setter for the current Player (Int)
    */
  override def setCurrentPlayer(id: Int): Unit = {
    this.currPlayer = id
  }
}