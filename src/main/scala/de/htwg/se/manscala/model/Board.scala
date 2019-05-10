package de.htwg.se.manscala.model

/**
  * Board is a companion object for case class Board @see {Board}
  * @member sideLength an Int with how many Pits each player owns.
  */
object Board {
  val SIDE_LENGTH: Int = 7
  private val default_p1 = Player("A", 0)
  private val default_p2 = Player("B", 1)
  val DEFAULT_PITS = List(new Pit(default_p1), new Pit(default_p1),
    new Pit(default_p1), new Pit(default_p1),
    new Pit(default_p1), new Pit(default_p1),
    Pit(0, isMancala = true, default_p1),
    new Pit(default_p2), new Pit(default_p2),
    new Pit(default_p2), new Pit(default_p2),
    new Pit(default_p2), new Pit(default_p2),
    Pit(0, isMancala = true, default_p2))
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
    * default constructor
    * @return a default Board with players A, B and 7 pits with Pit.PIT_SIZE stones per non mancala pit
    */
  def this()  = this(List(Board.default_p1, Board.default_p2), Board.DEFAULT_PITS)

  /**
    * moves pebbles according to player's choice of pit.
    * @param chosenPit, an int representing the current player's choice.
    * @return true if player switch should happen.
    */
  def move(chosenPit: Int): Boolean = {
    // i / 7 = player: whole div [0,6] = 0, [7,13]=1 etc
    var stones = 0
    stones = pits(chosenPit).emptyPit()
    for(j <- 1 to stones) {
      // (chosenPit +j) / pits.size should be 0
      pits((chosenPit + j) % pits.size).incr()
    }
    (chosenPit + stones % pits.size) / Board.SIDE_LENGTH !=
      chosenPit / Board.SIDE_LENGTH
  }

  override def toString:String = {
    val buildString = new StringBuilder("{")
    for (pit <- pits) {
      buildString.append(pit.toString)
      // TODO: Beautify Board output
    }
    buildString.append("}").toString()
  }
}
