package de.htwg.se.manscala.controller.controllerComponent.controllerAdvImpl

import de.htwg.se.manscala.controller.controllerComponent.{Command, ControllerInterface}
import de.htwg.se.manscala.model.boardComponent.BoardInterface
import de.htwg.se.manscala.model.boardComponent.boardReverseImpl.Board
import de.htwg.se.manscala.model.playerComponent.PlayerInterface
import de.htwg.se.manscala.model.playerComponent.playerImpl.Player

class MoveCommand(chosenPit: Int, var board: BoardInterface, var savedStones: Int, player: PlayerInterface,
                  controller: ControllerInterface) extends Command {
  override def doStep() : Boolean = {
    if (!controller.checkMove(chosenPit)) {
      false
    } else {

      val tuple = this.board.move(chosenPit)
      savedStones = tuple._2
      if (tuple._1) {
        controller.switchPlayer()
      }
      controller.notifyObservers()
      true
    }
  }

  override def undoStep() : Unit = {
    this.board.reverseMove(this.chosenPit, this.savedStones)
    this.controller.currPlayer = player.getId()
//    controller.board = savedBoard
    controller.notifyObservers()
  }

  override def redoStep() : Unit = {
    val (switch, _) = this.board.move(chosenPit)
    if (switch) {
      controller.switchPlayer()
    }
    controller.notifyObservers()
  }

}
