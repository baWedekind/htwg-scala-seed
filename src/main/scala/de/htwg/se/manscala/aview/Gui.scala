package de.htwg.se.manscala.aview

import java.awt.{Color, Font}

import de.htwg.se.manscala.controller.Controller

import scala.language.postfixOps
import scala.swing._
import scala.swing.event._
import de.htwg.se.manscala.controller._
import de.htwg.se.manscala.model.boardComponent.boardReverseImpl.Board
import de.htwg.se.manscala.util.Observer

class Gui(controller: Controller) extends Frame with Observer {

  controller.add(this)

  val height = 800
  val width = 1400
  val row: Int = controller.board.numPlayers
  val col: Int = Board.SIDE_LENGTH
  var str = "It is " + controller.getCurrentPlayer() + "'s turn"

  var guiPits = Array.ofDim[GuiPit](row, col)

  title = "Mancala"
  preferredSize = new Dimension(width, height)

  setButtons()

  val playerFeedback = new TextField("Start of Game", 20)

  val textPanel: TextField = new TextField() {
    editable = false
    background = getPlayerColor(controller.currPlayer)
    text = str
  }

  contents = new BorderPanel {
    add(textPanel, BorderPanel.Position.North)
    add(gridPanel, BorderPanel.Position.Center)
    add(playerFeedback, BorderPanel.Position.South)
  }
  buttonActionListener()

  menuBar = new MenuBar {
    contents += new Menu("File") {
      mnemonic = Key.F
      contents += new MenuItem(Action("New Game") {
        reset
      })


      contents += new MenuItem(Action("Save Game") {
        save()
      })
      contents += new MenuItem(Action("Load Game") {
        load()
      })
      contents += new MenuItem(Action("Rules") {
        help()
      })
      contents += new MenuItem(Action("Quit") {
        exit()
      })
    }

      contents += new MenuItem(Action("Undo") {

        controller.undoManager.undoStep()
      })
      contents += new MenuItem(Action("Redo") {

        controller.undoManager.redoStep()
      })

    contents += new Menu("Options") {
      mnemonic = Key.O
//      contents += new MenuItem(Action("Start with 4 stones") {
//        controller.updateStones(4)
//        reset
//        redraw()
//      })
      contents += new MenuItem(Action("Start with 6 stones") {
        reset
        redraw()
      })
    }
  }
  visible = true

  def save(): Unit = {
    Dialog.showMessage(contents.head, "Save Game coming soon!")
  }

  def load(): Unit = {
    Dialog.showMessage(contents.head, "Load Game coming soon!")
  }

  def gridPanel: GridPanel = new GridPanel(row, col) {
    preferredSize = new Dimension(600, 600)
    for (x <- 0 until row)  {
      for (y <- 0 until col) {
        contents += guiPits(x)(y)
//        contents += myguiPits(x * Board.SIDE_LENGTH + y)
//        0 * 7  + 0 = 0; 0 * 7 + 1 = 1; 0 * 7 + 6 = 6
//        1 * 7 + 0 = 7; 1 * 7 + 1 = 8; 1 * 7 + 6 = 13

      }
    }

  }

  def setButtons(): Unit = {
    for (x <- 0 until row) {
      for (y <- 0 until col) {
        guiPits(x)(y) = new GuiPit(new Point(x, y))
        guiPits(x)(y).background = getPlayerColor(controller.board.numPlayers - x - 1)
        guiPits(x)(y).text = controller.board.pits(positionToArray(x, y)).toString()
        guiPits(x)(y).font = new Font("Arial", 0, 50)
      }
    }
  }

  private def getPlayerColor(x: Int): Color = {
    controller.board.players(x).name(0) match {
      case 'A' | 'a' => Color.decode("#dab420")
      case 'B' | 'b' => Color.decode("#b0e2ff")
      case 'C' | 'c' => Color.decode("#ff403a")
      case 'D' | 'd' => Color.decode("#af5166")
      case 'E' | 'e' => Color.decode("#470359")
      case 'F' | 'f' => Color.decode("#7fff00")
      case 'G' | 'g' => Color.decode("#00ff00")
      case 'H' | 'h' => Color.decode("#ff0067")
      case 'I' | 'i' => Color.decode("#00ffff")
      case 'J' | 'j' => Color.decode("#672edf")
      case 'K' | 'k' => Color.decode("#ff0000")
      case 'L' | 'l' => Color.decode("#ec8484")
      case 'M' | 'm' => Color.decode("#002444")
      case 'N' | 'n' => Color.decode("#a2cbc3")
      case 'O' | 'o' => Color.decode("#a59971")
      case 'P' | 'p' => Color.decode("#ee9c00")
      case 'Q' | 'q' => Color.decode("#420dab")
      case 'R' | 'r' => Color.decode("#000000")
      case 'S' | 's' => Color.decode("#e0115f")
      case 'T' | 't' => Color.decode("#cc2023")
      case 'U' | 'u' => Color.decode("#f7ed5f")
      case 'V' | 'v' => Color.decode("#6365ff")
      case 'W' | 'w' => Color.decode("#202d00")
      case 'X' | 'x' => Color.decode("#37f376")
      case 'Y' | 'y' => Color.decode("#8d729d")
      case 'Z' | 'z' => Color.decode("#8e4e4c")
      case _ => Color.magenta
    }
  }

  def buttonActionListener(): Unit = {
    for {
      x <- 0 until row
      y <- 0 until col
    } guiPits(x)(y).reactions += {
      case b: ButtonClicked => {
        val chosenPit = positionToArray(x, y)
        val comma = new MoveCommand(chosenPit, controller.board, 0,
          controller.getCurrentPlayer(), controller)
        controller.executeCommand(comma)
      }
    }
  }

  def redraw(): Unit = {
    textPanel.background = getPlayerColor(controller.currPlayer)
    str = "It is " + controller.getCurrentPlayer() + "'s turn"
    textPanel.text_=(str)

    for {
      x <- 0 until row
      y <- 0 until col
    } {
      guiPits(x)(y).text = controller.board.pits(positionToArray(x, y)).toString()
    }

    repaint
  }

  def exit(): Unit = {
    val dia = Dialog.showConfirmation(contents.head, "Are you sure you want to quit?", "Quit", optionType = Dialog.Options.YesNo)
    if (dia == Dialog.Result.Yes) {
      val conf = Dialog.showConfirmation(contents.head, "Are you REALLY sure you wish to Exit?", "Quit", optionType = Dialog.Options.YesNo)
      if (conf == Dialog.Result.Yes) {
        sys.exit(0)
      }
    }
  }

  def reset(): Unit = {
    val dia = Dialog.showConfirmation(contents.head, "Are you sure you wish to restart?", "New Game", optionType = Dialog.Options.YesNo)
    if (dia == Dialog.Result.Yes) {
//      controller.reset()
      Dialog.showMessage(contents.head, "New Game coming soon!")
    }
  }

  override def update(): Unit = {
    playerFeedback.text = controller.getNotifier()
    redraw()
  }

  def help(): Unit = {
    val rules = "Explained for 2 players.\n\n" +
      "6 (or 4) stones get put into 12 out of 14 pits\n\n" +
      "The player with the most stones in his Mancala wins.\n\n" +
      "On your turn, pick one of your pits. All stones are removed and one is dropped into each consecutive\n\n" +
      "neighbouring pit in a counterclockwise direction. The opponent's Mancala is skipped.\n\n" +
      "If the last stone falls in your Mancala, take an extra turn.\n\n" +
      "If the last stone falls into a" + /*"n empty" + */ "pit on your side" + /*" empty it and the opposing pit "+
      " and put the stones into your Mancala, and" +*/ " take an extra turn\n\n" +
      "The game ends when a Player has no stones left except in his Mancala.\n\n"
    Dialog.showConfirmation(contents.head, rules, "Rules", optionType = Dialog.Options.Default)
  }

  /**
    * Converts an index in an List of size SIDE_LENGTH to a position in a GUI Point matrix to reflect a Mancala Board.
    * This only works for 2 players, A more complicated GUI is required for four or more players
    * @param i the index in the Array
    * @return a Tuple with x y position
    */
  private def arrayToPosition(i: Int): (Int, Int) = {
    if (i < Board.SIDE_LENGTH) {
      (1, i)
    } else {
      // first row, collumn [7,13] -> [6,0]; 7 - (1+0*2) = 6; 10 -(1+2*3) = 3;13-(1+2*6) = 0;
      ( 0, i - (1 + 2 * (i - Board.SIDE_LENGTH)) )
    }
  }

  /**
    * Converts a position in a GUI Point matrix to an index to reflect the model layer (for 2 players).
    * @param x the x position
    * @param y the y position
    * @return the index in the matrix
    */
  private def positionToArray(x: Int, y: Int): Int = {
    if (x == 0) {
      y + ( 1 + 2 * (Board.SIDE_LENGTH - y - 1))
    } else {
      y
    }
  }
}