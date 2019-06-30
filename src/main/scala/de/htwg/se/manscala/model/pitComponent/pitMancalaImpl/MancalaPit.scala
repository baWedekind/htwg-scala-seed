package de.htwg.se.manscala.model.pitComponent.pitMancalaImpl

import de.htwg.se.manscala.model.pitComponent.Pit
import de.htwg.se.manscala.model.playerComponent.Player

case class MancalaPit(var stones: Int = Pit.PIT_SIZE, owner: Player) extends Pit {
  def this(pl: Player) = this(0, pl)

  override def toString:String = "([" + this.stones + "])"

  override def copy(): MancalaPit = MancalaPit(this.stones, this.owner)
}
