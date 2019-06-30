package de.htwg.se.manscala.model.boardComponent.boardReverseImpl

import de.htwg.se.manscala.model.boardComponent.BoardInterface
import de.htwg.se.manscala.model.pitComponent.pitNormalImpl.NormalPit
import de.htwg.se.manscala.model.pitComponent.Pit
import de.htwg.se.manscala.model.pitComponent.pitMancalaImpl.MancalaPit
import de.htwg.se.manscala.model.playerComponent.Player

/**
  * Board is a companion object for case class Board @see {Board}
  *
  * @member sideLength an Int with how many Pits each player owns.
  */
object Board {
  val SIDE_LENGTH: Int = 7
  private val default_p1 = Player("A", 0)
  private val default_p2 = Player("B", 1)
  val DEFAULT_PITS = List(
    Pit.apply(isMancala = false, default_p1), Pit.apply(isMancala = false, default_p1),
    Pit.apply(isMancala = false, default_p1), Pit.apply(isMancala = false, default_p1),
    Pit.apply(isMancala = false, default_p1), Pit.apply(isMancala = false, default_p1),
    Pit.apply(isMancala = true, default_p1),
    Pit.apply(isMancala = false, default_p2), Pit.apply(isMancala = false, default_p2),
    Pit.apply(isMancala = false, default_p2), Pit.apply(isMancala = false, default_p2),
    Pit.apply(isMancala = false, default_p2), Pit.apply(isMancala = false, default_p2),
    Pit.apply(isMancala = true, default_p2))
}

/**
  * Board should have a list of players and a list of pits.
  * This version of Board expects a side length of six pits and a Mancala per player
  * The default value copies the DEFAULT_PITS prototype, the case class also providing a scala version of a Builder
  * pattern
  */
case class Board(players: List[Player],
                 pits: List[Pit] = Board.DEFAULT_PITS.map(x => Pit.apply(x.isInstanceOf[MancalaPit], x.owner))) extends BoardInterface {
  override val numPlayers: Int = players.size
  if (numPlayers % 2 != 0) {
    throw new IllegalArgumentException("Number of Players must be even. Given: " + numPlayers)
  }

  /**
    * default constructor
    * @return a default Board with players A, B and 7 pits with Pit.PIT_SIZE stones per non mancala pit
    */
  def this() = this(List(Board.default_p1, Board.default_p2))

  /**
    * moves pebbles according to player's choice of pit.
    * @param chosenPit, an int representing the current player's choice.
    * @return (Boolean, Int) true if player switch should happen, the amount of stones in chosenPit.
    */
  override def move(chosenPit: Int): (Boolean, Int) = {
    // i / 7 = player: whole div [0,6] = 0, [7,13]=1 etc
    var stones = 0
    stones = pits(chosenPit).asInstanceOf[NormalPit].emptyPit()
    for(j <- 1 to stones) {
      // (chosenPit +j) / pits.size should be 0
      pits((chosenPit + j) % pits.size).incr()
    }
    ((chosenPit + stones % pits.size) / Board.SIDE_LENGTH !=
      chosenPit / Board.SIDE_LENGTH, stones)
  }

  /**
    * Undoes a move based on what the chosenPit was, and how many stones were returned from it.
    * Only call from UndoManager, or other situations where you know this is legal
    * @param chosenPit, an Int representing the Player's choice
    * @param stones, an Int for the amount of stones to replace
    */
  override def reverseMove(chosenPit: Int, stones: Int):Unit = {
    for(j <- stones to 1 by -1) {
      // (chosenPit +j) / pits.size should be 0
      println("Decrementing [" + (chosenPit + j) % pits.size + "] with j = " + j )
      pits((chosenPit + j) % pits.size).decr()
    }
    // Replace the stones in chosenPit
    pits(chosenPit).stones = stones
  }

  override def toString:String = {
    val buildString = new StringBuilder("{")
    for (pit <- pits) {
      buildString.append(pit.toString)
    }
    buildString.append("}").toString()
  }
}
