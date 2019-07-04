package de.htwg.se.manscala.aview

import de.htwg.se.manscala.controller.controllerComponent.{Command, ControllerInterface}
import de.htwg.se.manscala.util.Observer

class Tui(controller: ControllerInterface) extends Observer {

  controller.add(this)

  def processInputLine(input: String) {
    val int = """(\d+)""".r
    input match {
      case "q" => ()
      case "undo"=> controller.undoManager.undoStep()
      case "redo" => controller.undoManager.redoStep()
      case int(x) => {
        val Commalomadommadomm: Command = Command.apply(x.toInt, controller.board, 0,
          controller.getCurrentPlayer(), controller, "move")
        controller.executeCommand(Commalomadommadomm)
      }
      case _ => println("please choose a valid pit")
    }
  }

  override def update(): Unit = {
    println(controller.getNotifier() + " " + controller.board)
  }
}
