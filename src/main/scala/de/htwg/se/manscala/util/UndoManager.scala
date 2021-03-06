package de.htwg.se.manscala.util

import de.htwg.se.manscala.controller.controllerComponent.Command

class UndoManager {
  var undoStack: List[Option[Command]] = List(None)
  var redoStack: List[Option[Command]] = List(None)

  def doStep(command: Command): Boolean = {
    undoStack.head match {
      case Some(_) => undoStack = Some(command) :: undoStack
      case None => undoStack = List(Some(command))
    }
    command.doStep()
  }

  // Test structure with simple Command
  def undoStep(): Unit = {
    undoStack.head match {
      case None =>
      case Some(command) => {
        command.undoStep()
        val head = undoStack.head
        undoStack = undoStack.tail match {
          case Nil => List(None)
          case _ => undoStack.tail
        }
        redoStack = head :: redoStack
      }
    }
  }

  def redoStep(): Unit = {
    redoStack.head match {
      case None =>
      case Some(command) => {
        command.redoStep()
        val head = redoStack.head
        redoStack = redoStack.tail
        undoStack = head :: undoStack
      }
    }
  }
}
