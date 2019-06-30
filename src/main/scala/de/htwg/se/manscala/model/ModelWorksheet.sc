import de.htwg.se.manscala.model.Pit.PIT_SIZE
import de.htwg.se.manscala.model.Pit
import de.htwg.se.manscala.model.boardComponent.boardReverseImpl.Board

case class Cell(x:Int, y:Int)

val cell1 = Cell(4,5)
cell1.x
cell1.y

case class Field(cells: Array[Cell])

val field1 = Field(Array.ofDim[Cell](1))
field1.cells(0)=cell1
field1.cells(0).x
field1.cells(0).y

val p1 = Player("Alice")
val p2 = Player("Bob")
val pitSize = PIT_SIZE
val pitTypeDefaultP1 = Pit(pitSize, isMancala = false, p1)
val pitTypeDefaultP2 = Pit(pitSize, isMancala = false, p2)
val pits = List(pitTypeDefaultP1.copy(), pitTypeDefaultP1.copy(),
  pitTypeDefaultP1.copy(), pitTypeDefaultP1.copy(),
  pitTypeDefaultP1.copy(), pitTypeDefaultP1.copy(),
  pitTypeDefaultP1.copy(), Pit(0, isMancala = true, p1),
  pitTypeDefaultP2.copy(), pitTypeDefaultP2.copy(),
  pitTypeDefaultP2.copy(), pitTypeDefaultP2.copy(),
  pitTypeDefaultP2.copy(), pitTypeDefaultP2.copy(),
  pitTypeDefaultP2.copy(), Pit(0, isMancala = true, p2))

for (i <- 0 to Board.SIDE_LENGTH * 2) {

}

p1.toString()

val board = Board(List(p1, p2), pits)