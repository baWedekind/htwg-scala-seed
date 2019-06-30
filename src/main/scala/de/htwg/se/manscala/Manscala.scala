package de.htwg.se.manscala

import de.htwg.se.manscala.aview.{Gui, Tui}
import de.htwg.se.manscala.controller.controllerComponent.controllerAdvImpl.Controller
import de.htwg.se.manscala.model.boardComponent.boardReverseImpl.Board
import de.htwg.se.manscala.model.pitComponent.Pit
import de.htwg.se.manscala.util.UndoManager

import scala.io.StdIn.readLine

object Manscala {
  var board: Board = new Board()
  val controller = new Controller(new UndoManager, board, board.players.head.id)
  val tui = new Tui(controller)
  val gui = new Gui(controller)


  def main(args: Array[String]): Unit = {
    val pitSize = Pit.PIT_SIZE

    var input: String = ""

    do {
      input = readLine()
      tui.processInputLine(input)
    } while (input != "q")

//    println("Player 1, What is your name?")
//    val nameP1 = INPUT.next()
//    val p1 = Player(nameP1)
//    println("Hello, " + p1.name)
//
//    println("Player 2, What is your name?")
//    val nameP2 = INPUT.next()
//    val p2 = Player(nameP2)
//    println("Hello, " + p2.name)

  }
}
