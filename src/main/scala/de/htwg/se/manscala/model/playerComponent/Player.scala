package de.htwg.se.manscala.model.playerComponent

case class Player(name: String, id: Int) {
  override def toString:String = name
}
