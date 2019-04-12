package de.htwg.se.manscala.model

import de.htwg.se.manscala.model.Player

/**
  * Board is a companion object for case class Board @see {Board}
  * @member sideLength an Int with how many Pits each player owns.
  */
object Board {
  val SIDE_LENGTH: Int = 7
}

/**
  * Board should have a list of players and a list of pits
  * Use the above in BoardSpec later
  */
case class Board(players: List[Player], pits: List[Pit]) {
  val numPlayers: Int = players.size
  if (numPlayers % 2 != 0) {
    throw new IllegalArgumentException("Number of Players must be even. Given: " + numPlayers)
  }
  /**
    * moves pebbles according to player's choice of pit, after legality is checked.
    * @param chosenPit, an int representing the current player's choice.
    * @return true on successful completion, false if the move is illegal.
    */
  def move(chosenPit: Int): Boolean = {
    // i / 7 = player: whole div [0,6] = 7, [7,13]=1 etc
    val playerChosen = chosenPit / Board.SIDE_LENGTH
    var stones = 0
    // was chosen pit in range and not a mancala (playerpit)
    // TODO: player may only chose own pits!
    if (chosenPit >= 0 && chosenPit < numPlayers * Board.SIDE_LENGTH &&
      !pits(chosenPit).isMancala) {
      stones = pits(chosenPit).emptyPit()
    } else {
      // illegal number chosen
      false
    }
    //    var j: Int = i
    // Idea: make Board have a List of Pits which have an owner Player...
    // Remember that players is a List of Players
    for(j <- 1 to stones + 1) {
      // (chosenPit +j) / pits.size should be 0
      pits((chosenPit + j) % pits.size).incr()
    }
    true
  }
}
