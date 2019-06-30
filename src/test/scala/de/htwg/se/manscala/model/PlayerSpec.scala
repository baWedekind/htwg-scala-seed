package de.htwg.se.manscala.model

import de.htwg.se.manscala.model.playerComponent.Player
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PlayerSpec extends WordSpec with Matchers {
  "A Player" when { "new" should {
    val name = "Your Name"
    val player = Player(name, 0)
    "have a name"  in {
      player.name should be(name)
    }
    "and a (unique) id" in {
      player.id shouldBe 0
    }
    "have a nice String representation" in {
      player.toString should be(name)
    }
  }}


}
