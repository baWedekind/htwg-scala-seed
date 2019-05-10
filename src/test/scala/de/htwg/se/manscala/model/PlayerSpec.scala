package de.htwg.se.manscala.model

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PlayerSpec extends WordSpec with Matchers {
  "A Player" when { "new" should {
    val name = "Your Name"
    val player = Player(name)
    "have a name"  in {
      player.name should be(name)
    }
    "have a nice String representation" in {
      player.toString should be(name)
    }
  }}


}
