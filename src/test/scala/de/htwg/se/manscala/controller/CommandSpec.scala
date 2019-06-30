package de.htwg.se.manscala.controller

import de.htwg.se.manscala.controller.controllerComponent.Command
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

class incrCommand extends Command {
  var state:Int =0
  override def doStep(): Boolean = {
    state+=1
    true
  }

  override def undoStep(): Unit = state-=1

  override def redoStep(): Unit = state+=1
}

@RunWith(classOf[JUnitRunner])
class CommandSpec extends WordSpec with Matchers {
  "A Command" should {
    "have a do step" in {
      val command = new incrCommand
      command.state shouldBe 0
      command.doStep()
      command.state shouldBe 1
      command.doStep()
      command.state shouldBe 2
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
