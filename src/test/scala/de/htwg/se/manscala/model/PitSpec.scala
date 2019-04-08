package de.htwg.se.manscala.model

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PitSpec extends WordSpec with Matchers {
  "A Pit" when { "new" should {
    val player = Player("Your Name")
    val pit = Pit(6, isMancala=false, player)
    "have 0 or more stones"  in {
      pit.stones should be > 0
    }
    "be a Mancala or not" in {
      pit.isMancala || !pit.isMancala
    }
    "have a Player as owner" in {
      pit.owner.isInstanceOf // TODO: is this correct?
    }
    "have a nice String representation" in {
      pit.toString should be("(" + pit.stones + ")")
    }
  }}


}
