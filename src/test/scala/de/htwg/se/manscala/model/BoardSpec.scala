package de.htwg.se.manscala.model

import de.htwg.se.manscala.model.boardComponent.boardReverseImpl.Board
import de.htwg.se.manscala.model.playerComponent.Player
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import scala.collection.mutable.ListBuffer

@RunWith(classOf[JUnitRunner])
class BoardSpec extends WordSpec with Matchers {

  "A Board" when { "new" should {
    val board = new Board()
    "have two players in a list" in {
      board.players shouldBe a[List[_]]
      board.players.size shouldBe 2
    }
    "have Board.SIDE_LENGTH * board.players.size pits in a list" in {
      board.pits shouldBe a[List[_]]
      board.pits.size shouldBe Board.SIDE_LENGTH * board.players.size
    }
    "or " when { "new with uneven Players" should {
      val p3 = Player("Test three", 2)
      "Throw an IllegalArgumentException" in {
        an[IllegalArgumentException] should be thrownBy Board(List(board.players.head, board.players(1), p3), Board.DEFAULT_PITS)
      }

    }}
  }}
  "A Board" when { "applying a move" should {
    val board = new Board()
    val chosenPit = 12
    val numStones = board.pits(chosenPit).stones
    var prevStonesList = new ListBuffer[Int]()
    for (j <- 1 to numStones) {
      prevStonesList += board.pits((chosenPit + j) % board.pits.size).stones
    }
    "complete the move successfully" in {
      val (success, _) = board.move(chosenPit)
      success
    }
    "have incremented the same amount of Pits as Stones returned" in {
      board.move(chosenPit)
      for (j <- 1 to numStones) {
        // ! Error, errant move happening during iteration !
        board.pits((chosenPit + j) % board.pits.size).stones shouldBe prevStonesList(j - 1) + 1
      }
    }
    "and" when { "reversing a move " should {
      "have decremented the right pits" in {//TODO: figure out how to test this
        val (_, stones) = board.move(chosenPit)
        board.reverseMove(chosenPit, stones)
        for(j <- stones % board.pits.size to 1 by -1) {
          board.pits((chosenPit + j) % board.pits.size).stones shouldBe prevStonesList(j - 1)
        }
      }
    }}
  }}
}
