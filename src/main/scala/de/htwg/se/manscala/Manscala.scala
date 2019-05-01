package de.htwg.se.manscala

import java.util.Scanner

import de.htwg.se.manscala.model.{Board, Pit, Player}
import de.htwg.se.manscala.view.Tui

import scala.io.StdIn.readLine

object Manscala {
  val p1 = Player("A")
  val p2 = Player("B")
  val pitTypeDefaultP1 = Pit(Pit.PIT_SIZE, isMancala = false, p1)
  val pitTypeDefaultP2 = Pit(Pit.PIT_SIZE, isMancala = false, p2)
  val pits = List(pitTypeDefaultP1.copy(), pitTypeDefaultP1.copy(),
    pitTypeDefaultP1.copy(), pitTypeDefaultP1.copy(),
    pitTypeDefaultP1.copy(), pitTypeDefaultP1.copy(),
    Pit(0, isMancala = true, p1),
    pitTypeDefaultP2.copy(), pitTypeDefaultP2.copy(),
    pitTypeDefaultP2.copy(), pitTypeDefaultP2.copy(),
    pitTypeDefaultP2.copy(), pitTypeDefaultP2.copy(),
    Pit(0, isMancala = true, p2))
  val board = Board(List(p1, p2), pits)
  val tui = new Tui

  def main(args: Array[String]): Unit = {
    val pitSize = Pit.PIT_SIZE

    var input: String = ""

    do {
      println("Board : " + board.toString)
      input = readLine()
      tui.processInputLine(input, board)
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
