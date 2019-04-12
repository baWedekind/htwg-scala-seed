package de.htwg.se.manscala.model

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import scala.collection.mutable.ListBuffer

@RunWith(classOf[JUnitRunner])
class BoardSpec extends WordSpec with Matchers {

  "A Board" when { "new" should {
    val p1 = Player("Test one")
    val p2 = Player("Test two")
    val pitTypeDefaultP1 = Pit(Pit.PIT_SIZE, isMancala = false, p1)
    val pitTypeDefaultP2 = Pit(Pit.PIT_SIZE, isMancala = false, p2)
    val pits = List(pitTypeDefaultP1.copy(), pitTypeDefaultP1.copy(),
      pitTypeDefaultP1.copy(), pitTypeDefaultP1.copy(),
      pitTypeDefaultP1.copy(), pitTypeDefaultP1.copy(),
      Pit(0, isMancala = true, p1),
      pitTypeDefaultP2.copy(), pitTypeDefaultP2.copy(),
      pitTypeDefaultP2.copy(), pitTypeDefaultP2.copy(),
      pitTypeDefaultP2.copy(), pitTypeDefaultP2.copy(),
      Pit(0, isMancala = true, p2))
    val board = Board(List(p1, p2), pits)
    "have two players in a list" in {
      board.players shouldBe a[List[Player]]
      board.players.size shouldBe 2
    }
    "have Board.SIDE_LENGTH * board.players.size pits a list" in {
      board.pits shouldBe a[List[Pit]]
      board.pits.size shouldBe Board.SIDE_LENGTH * board.players.size
    }
    "or " when { "new with uneven Players" should {
      val p3 = Player("Test three")
      "Throw an IllegalArgumentException" in {
        an[IllegalArgumentException] should be thrownBy Board(List(p1, p2, p3), pits)
      }

    }}
  }}
  "A Board" when { "applying a move" should {
    val p1 = Player("Test one")
    val p2 = Player("Test two")
    val pitTypeDefaultP1 = Pit(Pit.PIT_SIZE, isMancala = false, p1)
    val pitTypeDefaultP2 = Pit(Pit.PIT_SIZE, isMancala = false, p2)
    val pits = List(pitTypeDefaultP1.copy(), pitTypeDefaultP1.copy(),
      pitTypeDefaultP1.copy(), pitTypeDefaultP1.copy(),
      pitTypeDefaultP1.copy(), pitTypeDefaultP1.copy(),
      pitTypeDefaultP1.copy(), Pit(0, isMancala = true, p1),
      pitTypeDefaultP2.copy(), pitTypeDefaultP2.copy(),
      pitTypeDefaultP2.copy(), pitTypeDefaultP2.copy(),
      pitTypeDefaultP2.copy(), pitTypeDefaultP2.copy(),
      pitTypeDefaultP2.copy(), Pit(0, isMancala = true, p2))
    val board = Board(List(p1, p2), pits)
    val chosenPit = 12
    val numStones = board.pits(chosenPit).stones
    var prevStonesList = new ListBuffer[Int]()
    for (j <- 1 to numStones + 1) {
      prevStonesList += board.pits((chosenPit + j) % pits.size).stones
    }
    val success = board.move(chosenPit)
    "complete the move successfully" in {
      success
    }
    "have incremented the same amount of Pits as Stones returned" in {
      for (j <- 1 to numStones + 1) {
        board.pits((chosenPit + j) % pits.size).stones shouldBe prevStonesList(j - 1) + 1
      }
    }
    "or refuse the move unsuccessfully" when {
      val mancalaPit = 7

      "the chosen Pit is a Mancala" in {
        !board.move(mancalaPit)
      }
      val farPit = Board.SIDE_LENGTH * board.players.size + 5
      "the chosen Pit is out of bounds" in {
        !board.move(farPit)
      }
    }
  }}
}
