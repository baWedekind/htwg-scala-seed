import de.htwg.se.manscala.model.{Board, Player}

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

p1.toString()

val board = Board()