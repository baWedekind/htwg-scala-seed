package de.htwg.se.manscala.model.pitComponent.pitNormalImpl

import de.htwg.se.manscala.model.pitComponent.Pit
import de.htwg.se.manscala.model.playerComponent.PlayerInterface

case class NormalPit(var stones: Int = Pit.PIT_SIZE, owner: PlayerInterface) extends Pit {
  def this(pl: PlayerInterface) = this(Pit.PIT_SIZE, pl)

  def emptyPit(): Int = {
    val ret = this.stones
    this.stones = 0
    ret
  }

  override def toString:String = "(" + this.stones + ")"

  override def copy(): NormalPit = NormalPit(this.stones, this.owner)
}
