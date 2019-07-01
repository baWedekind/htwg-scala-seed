package de.htwg.se.manscala.controller

import de.htwg.se.manscala.controller.controllerComponent.controllerAdvImpl.Controller
import de.htwg.se.manscala.model.boardComponent.BoardInterface
import de.htwg.se.manscala.model.boardComponent.boardReverseImpl.Board
import de.htwg.se.manscala.util.UndoManager
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ControllerSpec extends WordSpec with Matchers {

  "A Controller"  when {
    "new" should {
      val board = new Board()
      val controller = new Controller(new UndoManager, board, board.players.head.id)
      "have a Board" in {
        controller.board shouldBe a[Board]
      }
      "and a current Player" in {
        controller.currPlayer shouldBe 0
      }
      controller.createDefaultBoard()
      "create a default Board" in {
        controller.board should be(new Board())
      }
      "return a nice String representation of Board" in {
        controller.boardToString should be(board.toString())
      }
      "check a move is legal and return true" in {
        controller.checkMove(3)
      }
      "or false" when {
        val mancalaPit = BoardInterface.SIDE_LENGTH
        "the chosen Pit is a Mancala" in {
          !controller.checkMove(mancalaPit)
        }
        val farPit = BoardInterface.SIDE_LENGTH * board.players.size + 5
        "the chosen Pit is out of bounds" in {
          !controller.checkMove(farPit)
        }
        val notMyPit = BoardInterface.SIDE_LENGTH + BoardInterface.SIDE_LENGTH / 2
        "the chosen Pit does not belong to the current Player" in {
          !controller.checkMove(notMyPit)
        }
      }
      "and " when {
        val oldPlayer = controller.currPlayer
        "performing a move should have switched the current Player" in {
          controller.move(3)
          val newPlayer = controller.currPlayer
          newPlayer should not be oldPlayer
        }
      "and" when {
        val switcher = new Controller(new UndoManager, board, board.numPlayers - 1)
        switcher.currPlayer = board.numPlayers - 1
        switcher.switchPlayer()
        "should loop around to the first player" in {
          switcher.currPlayer shouldBe 0
        }
      }
    }}
  }
}
