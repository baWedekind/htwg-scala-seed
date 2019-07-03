package de.htwg.se.manscala

import com.google.inject.{AbstractModule, Guice, Provides}
import com.google.inject.name.Names
import net.codingwell.scalaguice.ScalaModule
import de.htwg.se.manscala.controller.controllerComponent._
import de.htwg.se.manscala.model.boardComponent.BoardInterface
import de.htwg.se.manscala.model.boardComponent.boardReverseImpl
import de.htwg.se.manscala.model.pitComponent.{Pit, pitMancalaImpl, pitNormalImpl}
import de.htwg.se.manscala.model.playerComponent.{PlayerInterface, playerImpl}
import de.htwg.se.manscala.util.UndoManager

class ManscalaModule extends AbstractModule with ScalaModule {
  override def configure(): Unit = {
    bind[BoardInterface].to[boardReverseImpl.Board]
    bind[PlayerInterface].annotatedWithName("default_player1").toInstance(boardReverseImpl.Board.default_p1)
    bind[PlayerInterface].annotatedWithName("default_player2").toInstance(boardReverseImpl.Board.default_p2)
//    bind[ControllerInterface].to[controllerAdvImpl.Controller]
//    bind[BoardInterface].annotatedWithName("default").toInstance(new boardReverseImpl.Board())
//    bind[Pit].annotatedWithName("mancala").to[pitMancalaImpl.MancalaPit]
//    bind[Pit].annotatedWithName("normal").to[pitNormalImpl.NormalPit]
//    bind[Command].annotatedWithName("move").toInstance(new controllerAdvImpl.MoveCommand())
  }

  @Provides
  def provideControllerParams(): ControllerInterface = {
    val undoManager: UndoManager = new UndoManager
    var board: BoardInterface = Guice.createInjector(this).getInstance(classOf[BoardInterface])
    var currPlayer: Int = 0
    new controllerAdvImpl.Controller(undoManager, board, currPlayer)
  }

}


@Provides
object PlayerIDProvider {
  private var nextID = 0

  def get: Int = {
    nextID += 1; nextID
  }
}