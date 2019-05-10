package de.htwg.se.manscala.model

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
        an[IllegalArgumentException] should be thrownBy Board(List(board.players(0), board.players(1), p3), Board.DEFAULT_PITS)
      }

    }}
  }}
  "A Board" when { "applying a move" should {
    val p1 = Player("Test one", 0)
    val p2 = Player("Test two", 1)
    val pits = List(new Pit(p1), new Pit(p1),
      new Pit(p1), new Pit(p1),
      new Pit(p1), new Pit(p1),
      new Pit(p1), Pit(0, isMancala = true, p1),
      new Pit(p2), new Pit(p2),
      new Pit(p2), new Pit(p2),
      new Pit(p2), new Pit(p2),
      new Pit(p2), Pit(0, isMancala = true, p2))
    val board = Board(List(p1, p2), pits)
    val chosenPit = 12
    val numStones = board.pits(chosenPit).stones
    var prevStonesList = new ListBuffer[Int]()
    for (j <- 1 to numStones) {
      prevStonesList += board.pits((chosenPit + j) % pits.size).stones
    }
    val success = board.move(chosenPit)
    "complete the move successfully" in {
      success
    }
    "have incremented the same amount of Pits as Stones returned" in {
      for (j <- 1 to numStones) {
        board.pits((chosenPit + j) % pits.size).stones shouldBe prevStonesList(j - 1) + 1
      }
    }
  }}
}
