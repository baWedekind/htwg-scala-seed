package de.htwg.se.manscala.controller

trait Command {
  def doStep(): Boolean
  def undoStep(): Unit
  def redoStep(): Unit
}
