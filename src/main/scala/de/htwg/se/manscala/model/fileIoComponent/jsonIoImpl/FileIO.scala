package de.htwg.se.manscala.model.fileIoComponent.jsonIoImpl

  import com.google.inject.Guice
  import com.google.inject.name.Names
  import net.codingwell.scalaguice.InjectorExtensions._
  import de.htwg.se.manscala.ManscalaModule
  import de.htwg.se.manscala.model.fileIoComponent.FileIOInterface
  import de.htwg.se.manscala.model.boardComponent.BoardInterface
  import de.htwg.se.manscala.util.UndoManager
  import play.api.libs.json._

  import scala.io.Source

class FileIO extends FileIOInterface {
  override def load: BoardInterface = {
    var board: BoardInterface = null
    val source: String = Source.fromFile("savegame.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    val size = (json \ "grid" \ "size").get.toString.toInt
    val injector = Guice.createInjector(new ManscalaModule)
    size match {
      case 1 => grid = injector.instance[GridInterface](Names.named("tiny"))
      case 4 => grid = injector.instance[GridInterface](Names.named("small"))
      case 9 => grid = injector.instance[GridInterface](Names.named("normal"))
      case _ =>
    }
    board = injector.instance[BoardInterface]
    for (index <- 0 until size * size) {
      val row = (json \\ "row")(index).as[Int]
      val col = (json \\ "col")(index).as[Int]
      val cell = (json \\ "cell")(index)
      val value = (cell \ "value").as[Int]
      grid = grid.set(row, col, value)
      val given = (cell \ "given").as[Boolean]
      val showCandidates = (cell \ "showCandidates").as[Boolean]
      if (given) grid = grid.setGiven(row, col, value)
      if (showCandidates) grid = grid.setShowCandidates(row, col)
    }
    grid
  }

  override def save(undoManager: UndoManager): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("savegame.json"))
    undoTilNone(undoManager)
    pw.write(Json.prettyPrint(undoToJson(undoManager)))
    pw.close
  }

  implicit val cellWrites = new Writes[CellInterface] {
    def writes(cell: CellInterface) = Json.obj(
      "value" -> cell.value,
      "given" -> cell.given,
      "showCandidates" -> cell.showCandidates
    )
  }

  def undoToJson(undo: UndoManager):JsObject = {
    Json.obj(
      "redoStack" -> Json.arr(undo.redoStack),
    )
  }

  def undoTilNone(undo: UndoManager): Unit = {
    while (undo.undoStack.head != None) {
      undo.undoStep()
    }
  }
}