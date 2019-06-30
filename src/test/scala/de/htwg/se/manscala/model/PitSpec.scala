package de.htwg.se.manscala.model

import de.htwg.se.manscala.model.pitComponent.Pit
import de.htwg.se.manscala.model.pitComponent.pitNormalImpl.NormalPit
import de.htwg.se.manscala.model.playerComponent.Player
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PitSpec extends WordSpec with Matchers {
  "A Pit" when { "new" should {
    val player = Player("Your Name", 0)
    val pit = Pit.apply(isMancala=false, player)
    "have 6 stones"  in {
      pit.stones shouldBe 6
    }
    "be a NormalPit" in {
      pit.isInstanceOf[NormalPit]
    }
    "have a Player as owner" in {
      pit.owner shouldBe a[Player]
    }
    "with name = Your Name" in {
      pit.owner.name should be("Your Name")
    }
    "and id = 0" in {
      pit.owner.id shouldBe 0
    }
    "have a nice String representation" in {
      pit.toString should be("(" + pit.stones + ")")
    }
//    "and" when { "cloned" should {
//      val clonedPit = pit.clone()
//      "return an exact copy of itself" in {
//        clonedPit. should be pit
//      }
//    }}
  }}
  "A Pit" when { "incremented" should {
    val player = Player("Test Player", 0)
    val pit = Pit.apply(isMancala=true, player)
    pit.incr()
    "have exactly one more stone than before" in {
      pit.stones shouldBe 1
    }
  }}
  "A Pit" when { "emptied" should {
    val player = Player("Test Player", 0)
    val pit = Pit.apply(isMancala = false, player)
    "return the amount of stones it had" in {
      pit.asInstanceOf[NormalPit].emptyPit() shouldBe Pit.PIT_SIZE
    }
    "and be empty" in {
      pit.stones shouldBe 0
    }
  }}


}
