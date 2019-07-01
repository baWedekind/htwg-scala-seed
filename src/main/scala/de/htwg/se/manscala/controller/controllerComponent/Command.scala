package de.htwg.se.manscala.controller.controllerComponent

import de.htwg.se.manscala.controller.controllerComponent.controllerAdvImpl.MoveCommand
import de.htwg.se.manscala.model.boardComponent.BoardInterface
import de.htwg.se.manscala.model.playerComponent.PlayerInterface

trait Command {
  def doStep(): Boolean
  def undoStep(): Unit
  def redoStep(): Unit
}

object Command {
  def apply(chosenPit: Int, board: BoardInterface, savedStones: Int, player: PlayerInterface,
  controller: ControllerInterface, commandType: String): Command = commandType match {
    case "move" => new MoveCommand(chosenPit, board, savedStones, player, controller)
  }
}