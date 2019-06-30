package de.htwg.se.manscala.controller.controllerComponent.controllerAdvImpl

import de.htwg.se.manscala.controller.controllerComponent.Command
import de.htwg.se.manscala.model.boardComponent.boardReverseImpl.Board
import de.htwg.se.manscala.model.playerComponent.Player

class MoveCommand(chosenPit: Int, var board: Board, var savedStones: Int, player: Player, controller: Controller) extends Command {
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
    this.controller.currPlayer = player.id
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
