package de.htwg.se.manscala.model.playerComponent.playerImpl

import de.htwg.se.manscala.model.playerComponent.PlayerInterface

case class Player(name: String, id: Int) extends PlayerInterface {
  override def toString:String = name

  override def getName(): String = name

  override def getId(): Int = id
}
