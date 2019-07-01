package de.htwg.se.manscala.controller.controllerComponent

import de.htwg.se.manscala.model.boardComponent.BoardInterface
import de.htwg.se.manscala.model.playerComponent.PlayerInterface
import de.htwg.se.manscala.model.playerComponent.playerImpl.Player
import de.htwg.se.manscala.util.{Observable, UndoManager}

trait ControllerInterface extends Observable {
  val undoManager: UndoManager
  var board: BoardInterface
  var currPlayer: Int

  /**
    * a setter for the current Player (Int)
    */
  def setCurrentPlayer(id: Int): Unit

  def getNotifier(): String

  def switchPlayer(): Unit

  /**
    * a Getter for the curretn Player, gets the actual player, not an Int.
    * @return the actual PlayerInterface instance
    */
  def getCurrentPlayer(): PlayerInterface

  def createDefaultBoard(): Unit

  def boardToString: String

  def executeCommand(command: Command): Boolean

  // I could use a Strategy pattern to make an "AI" choose moves
  def move(chosenPit: Int): Boolean

  def checkMove(chosenPit: Int): Boolean
}
