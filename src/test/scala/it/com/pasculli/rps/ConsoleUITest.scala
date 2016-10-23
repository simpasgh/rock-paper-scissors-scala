package test.scala.rps

import java.io.ByteArrayOutputStream
import java.io.StringReader

import scala.Console.withIn
import scala.Console.withOut

import org.scalamock.scalatest.proxy.MockFactory
import org.scalatest.FunSpec
import org.scalatest.Matchers

import it.com.pasculli.rps.ConsoleUI

/**
 * Test for Console
 */
class ConsoleUITest extends FunSpec with Matchers with MockFactory {

  val consoleUI = ConsoleUI()

  describe("Console UI")({
    it("should allow the player to chose a Player vs Computer game, to play a move printing the result")({
      val outputStream = new ByteArrayOutputStream()

      withOut(outputStream) {
        withIn(new StringReader("1\n2\n3\n")) {
          /*
           * 1: Player vs computer game
           * 2: Paper
           * 3: Exit
           */
          consoleUI.start
        }
      }
      //game start and mode selection
      checkGameModeSelectionMessage(outputStream)
      //play player vs computer game and ask for a move
      checkMoveSelectionMessage(outputStream)
      //display the played move and the result
      checkCorrectMoveSelection(outputStream)
      //exit the game
      checkGoodbyeMessage(outputStream)
    })
    it("should allow the player to look at a Computer vs Computer game and should print the result")({
      val consoleUI = ConsoleUI()

      val outputStream = new ByteArrayOutputStream()
      withOut(outputStream) {
        withIn(new StringReader("2\n3\n")) {
          /*
           * 2: Computer vs Computer game
           * 3: Exit
           */
          consoleUI.start
        }
      }

      //game start and mode selection
      checkGameModeSelectionMessage(outputStream)
      //play computer vs computer
      checkComputerGameMessage(outputStream)
      //exit the game
      checkGoodbyeMessage(outputStream)
    })

    it("should allow the player to play each time a different game") {
      val consoleUI = ConsoleUI()

      val outputStream = new ByteArrayOutputStream()
      withOut(outputStream) {
        withIn(new StringReader("2\n1\n2\n3\n")) {
          /*
           * 2: Computer vs Computer game
           * 1: Player vs computer game
           * 2: Paper
           * 3: Exit
           */
          consoleUI.start
        }
      }

      //game start and mode selection
      checkGameModeSelectionMessage(outputStream)
      //play computer vs computer
      checkComputerGameMessage(outputStream)
      //after the game ask for choose the new game
      checkGameModeSelectionMessage(outputStream)
      //play player vs computer game and ask for a move
      checkMoveSelectionMessage(outputStream)
      //display the played move and the result
      checkCorrectMoveSelection(outputStream)
      //exit the game
      checkGoodbyeMessage(outputStream)
    }
  })

  /**
   * Check if the end game message is correctly displayed
   */
  private def checkGoodbyeMessage(outputStream: java.io.ByteArrayOutputStream) = {
    outputStream.toString should include(
      "Goodbye!")
  }

  /**
   * Check if the messages for the computer vs computer game are correctly displayed
   */
  private def checkComputerGameMessage(outputStream: java.io.ByteArrayOutputStream) = {
    outputStream.toString should include(
      "Computer 1 played:")
    outputStream.toString should include(
      "Computer 2 played:")
  }

  /**
   * Check if the correct move was selected
   */
  private def checkCorrectMoveSelection(outputStream: java.io.ByteArrayOutputStream) = { //check correct selection
    outputStream.toString should include(
      "You played: Paper\n")
  }

  /**
   * Check if the correct message is printed for move selection
   */
  private def checkMoveSelectionMessage(outputStream: java.io.ByteArrayOutputStream) = {
    outputStream.toString should include(
      "Chose your move\n" +
        "1 - Rock\n" +
        "2 - Paper\n" +
        "3 - Scissors")
  }

  /**
   * Check if the correct message is printed for game selection
   */
  private def checkGameModeSelectionMessage(outputStream: java.io.ByteArrayOutputStream) = {
    outputStream.toString should include(
      "Choose your game (1,2,3):\n" +
        "1- Player vs Computer\n" +
        "2- Computer vs Computer\n" +
        "3- Exit")
  }
}
