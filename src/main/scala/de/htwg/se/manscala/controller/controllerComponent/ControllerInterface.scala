package de.htwg.se.manscala.controller.controllerComponent

import de.htwg.se.manscala.model.playerComponent.Player

trait ControllerInterface {

  def getNotifier(): String

  def switchPlayer(): Unit

  def getCurrentPlayer(): Player

  def createDefaultBoard(): Unit

  def boardToString: String

  def executeCommand(command: Command): Boolean

  // I could use a Strategy pattern to make an "AI" choose moves
  def move(chosenPit: Int): Boolean

  def checkMove(chosenPit: Int): Boolean
}
