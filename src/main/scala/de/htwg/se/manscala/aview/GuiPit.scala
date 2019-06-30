package de.htwg.se.manscala.aview

import scala.swing.{Button, Point}

class GuiPit(point: Point) extends Button {

  def getPoint(): Point = {
    point
  }
}