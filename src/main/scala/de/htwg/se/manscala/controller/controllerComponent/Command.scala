package de.htwg.se.manscala.controller.controllerComponent

trait Command {
  def doStep(): Boolean
  def undoStep(): Unit
  def redoStep(): Unit
}
