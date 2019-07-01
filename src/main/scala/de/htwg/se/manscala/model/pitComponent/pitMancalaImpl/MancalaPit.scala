package de.htwg.se.manscala.model.pitComponent.pitMancalaImpl

import de.htwg.se.manscala.model.pitComponent.Pit
import de.htwg.se.manscala.model.playerComponent.PlayerInterface

case class MancalaPit(var stones: Int = Pit.PIT_SIZE, owner: PlayerInterface) extends Pit {
  def this(pl: PlayerInterface) = this(0, pl)

  override def toString:String = "([" + this.stones + "])"

  override def copy(): MancalaPit = MancalaPit(this.stones, this.owner)
}
