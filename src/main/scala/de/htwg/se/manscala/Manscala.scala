package de.htwg.se.manscala

import java.util.Scanner

import de.htwg.se.manscala.model.{Pit, Player}

import scala.collection.mutable.MutableList

object Manscala {
  private val INPUT = new Scanner(System.in)
  def main(args: Array[String]): Unit = {
    val pitSize = Pit.PIT_SIZE
    println("Player 1, What is your name?")
    val nameP1 = INPUT.next()
    val p1 = Player(nameP1)
    println("Hello, " + p1.name)

    println("Player 2, What is your name?")
    val nameP2 = INPUT.next()
    val p2 = Player(nameP2)
    println("Hello, " + p2.name)

  }
}
