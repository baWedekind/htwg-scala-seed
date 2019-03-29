package de.htwg.se.manscala

import de.htwg.se.manscala.model.Player

object Mancala {
  def main(args: Array[String]): Unit = {
    val student = Player("Your Name")
    println("Hello, " + student.name)
  }
}
