package de.htwg.se.manscala.model.fileIoComponent

import de.htwg.se.manscala.model.boardComponent.BoardInterface
import de.htwg.se.manscala.util.UndoManager

trait FileIOInterface {
  def load(): BoardInterface
  def save(undoManager: UndoManager): Unit
}
