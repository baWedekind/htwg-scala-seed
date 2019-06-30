package de.htwg.se.manscala.aview

import de.htwg.se.manscala.controller.{Command, Controller, MoveCommand}
import de.htwg.se.manscala.model.boardComponent.boardReverseImpl.Board
import de.htwg.se.manscala.util.Observer

class Tui(controller: Controller) extends Observer {

  controller.add(this)

  def processInputLine(input: String) {
    val int = """(\d+)""".r
    input match {
      case "q" => ()
      case "undo"=> controller.undoManager.undoStep()
      case "redo" => controller.undoManager.redoStep()
//      case "s" =>
//        val (success, solvedGrid) = new Solver(board).solve;
//        if (success) println("Puzzle solved")else println("This puzzle could not be solved!")
//        solvedGrid
      case int(x) => {
        val Commalomadommadomm: Command = new MoveCommand(x.toInt, controller.board, 0,
          controller.getCurrentPlayer(), controller)
        controller.executeCommand(Commalomadommadomm)
      }
      case _ => println("please choose a valid pit")
    }
  }

  override def update(): Unit = {
    println(controller.getNotifier() + " " + controller.board)
  }
}
