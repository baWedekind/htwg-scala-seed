package de.htwg.se.manscala.view

import de.htwg.se.manscala.model.{Board,Pit,Player}

class Tui {

  def processInputLine(input: String, board:Board) {
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
          board.move(x.toString.toInt)
      }
      case _ => println("please choose a valid pit")
    }
  }
}
