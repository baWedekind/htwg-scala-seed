package de.htwg.se.manscala.aview

import de.htwg.se.manscala.controller.{Command, Controller, MoveCommand}
import de.htwg.se.manscala.model.Board

class Tui {

  def processInputLine(input: String, controller: Controller) {
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
        if (!controller.executeCommand(Commalomadommadomm)) {
          println("please choose a valid pit")
        }
      }
      case _ => println("please choose a valid pit")
    }
  }
}
