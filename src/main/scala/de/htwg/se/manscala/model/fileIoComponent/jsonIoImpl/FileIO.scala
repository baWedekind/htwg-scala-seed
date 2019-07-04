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
    board = injector.instance[BoardInterface]

    board
  }

  override def save(undoManager: UndoManager): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("savegame.json"))
    undoTilNone(undoManager)
    pw.write(Json.prettyPrint(undoToJson(undoManager)))
    pw.close
  }


  def undoToJson(undo: UndoManager):JsObject = {
    Json.obj(
//      "redoStack" -> Json.arr(undo.redoStack),
    )
  }

  def undoTilNone(undo: UndoManager): Unit = {
    while (undo.undoStack.head != None) {
      undo.undoStep()
    }
  }
}