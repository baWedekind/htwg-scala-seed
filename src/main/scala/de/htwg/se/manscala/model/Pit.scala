package de.htwg.se.manscala.model

import de.htwg.se.manscala.model.Player

trait Pit {

  def copy(): Pit

  val owner: Player
  var stones: Int

  def incr(): Unit = {
    this.stones += 1
  }

  def decr(): Unit = {
    this.stones -= 1
  }
}


object Pit {
  /**
    * Apply method for factory style action.
    */
  def apply(isMancala: Boolean, pl: Player): Pit = if (isMancala) {
    new MancalaPit(pl)
  } else {
    new NormalPit(pl)
  }

  val PIT_SIZE: Int = 6
}

case class NormalPit(var stones: Int = Pit.PIT_SIZE, owner: Player) extends Pit {
  def this(pl: Player) = this(Pit.PIT_SIZE, pl)

  def emptyPit(): Int = {
    val ret = this.stones
    this.stones = 0
    ret
  }

  override def toString:String = "(" + this.stones + ")"

  override def copy(): NormalPit = NormalPit(this.stones, this.owner)
}

case class MancalaPit(var stones: Int = Pit.PIT_SIZE, owner: Player) extends Pit {
  def this(pl: Player) = this(0, pl)

  override def toString:String = "([" + this.stones + "])"

  override def copy(): MancalaPit = MancalaPit(this.stones, this.owner)
}