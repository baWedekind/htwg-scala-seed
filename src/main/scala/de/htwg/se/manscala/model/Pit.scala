package de.htwg.se.manscala.model

import de.htwg.se.manscala.model.Player

object Pit {
  val PIT_SIZE: Int = 6
}

case class Pit(var stones: Int, isMancala: Boolean, owner: Player) {
  def incr(): Unit = {
    this.stones += 1
  }

  def emptyPit(): Int = {
    val ret = stones
    stones = 0
    ret
  }

  override def toString:String = "(" + this.stones + ")"
}
