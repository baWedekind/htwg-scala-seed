package de.htwg.se.manscala

import java.util.Scanner

import de.htwg.se.mancala.model.Player

import scala.collection.mutable.MutableList

object Manscala {
  private val INPUT = new Scanner(System.in)
  def main(args: Array[String]): Unit = {
    val pitSize = 6
    println("Player 1, What is your name?")
    val nameP1 = INPUT.next()
    val p1 = Player(nameP1, collection.mutable.MutableList(pitSize, pitSize, pitSize, pitSize, pitSize, 0))
    println("Hello, " + p1.name)

    println("Player 2, What is your name?")
    val nameP2 = INPUT.next()
    val p2 = Player(nameP2, collection.mutable.MutableList(pitSize, pitSize, pitSize, pitSize, pitSize, 0))
    println("Hello, " + p2.name)

  }
}
