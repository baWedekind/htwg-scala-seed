package de.htwg.se.manscala.model.pitComponent

import com.google.inject.Provides
import com.google.inject.name.Named
import de.htwg.se.manscala.model.pitComponent.pitMancalaImpl.MancalaPit
import de.htwg.se.manscala.model.pitComponent.pitNormalImpl.NormalPit
import de.htwg.se.manscala.model.playerComponent.PlayerInterface

trait Pit {

  def copy(): Pit

  val owner: PlayerInterface
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
  @Provides
  def apply(@Named("isMancala") isMancala: Boolean, @Named("player") pl: PlayerInterface): Pit = if (isMancala) {
    new MancalaPit(pl)
  } else {
    new NormalPit(pl)
  }

  val PIT_SIZE: Int = 6
}
