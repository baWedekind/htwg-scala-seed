package de.htwg.se.manscala.aview

import de.htwg.se.manscala.controller.Controller

class Tui {

  def processInputLine(input: String, controller: Controller) {
    val int = """(\d+)""".r
    input match {
      case "q" => ()
//      case "n"=> new Grid(9)
//      case "r" => new GridCreator(9).createRandom(16)
//      case "s" =>
//        val (success, solvedGrid) = new Solver(board).solve;
//        if (success) println("Puzzle solved")else println("This puzzle could not be solved!")
//        solvedGrid
      case int(x) => {
          if (!controller.move(x.toString.toInt)) {
            println("please choose a valid pit")
          }
      }
      case _ => println("please choose a valid pit")
    }
  }
}
