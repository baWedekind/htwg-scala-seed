package de.htwg.se.manscala.controller

import de.htwg.se.manscala.model
import de.htwg.se.manscala.model.boardComponent.boardReverseImpl.Board
import de.htwg.se.manscala.model.pitComponent.Pit
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MoveCommandSpec extends WordSpec with Matchers {
  "A Command" should {
    "have a do step" in {
      val controller = new Controller(new UndoManager, new Board(), 0)
      val command = new MoveCommand(2, controller.board, 0,
        controller.getCurrentPlayer(), controller)
      command.doStep()
      command.board shouldBe controller.board
      command.savedStones shouldBe Pit.PIT_SIZE
      command.doStep()
      command.board shouldBe controller.board
    }
    "have an undo step" in {
      val command = new incrCommand
      command.state shouldBe 0
      command.doStep()
      command.state shouldBe 1
      command.undoStep()
      command.state shouldBe 0
    }
    "have a redo step" in {
      val command = new incrCommand
      command.state shouldBe 0
      command.doStep()
      command.state shouldBe 1
      command.undoStep()
      command.state shouldBe 0
      command.redoStep()
      command.state shouldBe 1
    }
  }
}
