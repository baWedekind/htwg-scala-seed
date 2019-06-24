package de.htwg.se.manscala

import de.htwg.se.manscala.model.{Board, Pit, Player}
import de.htwg.se.manscala.controller.{Controller, UndoManager}
import de.htwg.se.manscala.aview.Tui

import scala.io.StdIn.readLine

object Manscala {
  var board: Board = new Board()
  val tui = new Tui
  val controller = new Controller(new UndoManager, board, board.players.head.id)

  def main(args: Array[String]): Unit = {
    val pitSize = Pit.PIT_SIZE

    var input: String = ""

    do {
      println("Board : " + controller.board.toString)
      input = readLine()
      tui.processInputLine(input, controller)
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
