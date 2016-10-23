package it.com.pasculli.rps

import scala.io.StdIn
import scala.util.Try

case class ConsoleUI() {

  /**
   * Start the game
   */
  def start {

    println("Welcome to the marvelous Rock - Paper - Scissor game!")
    var endGame = false
    while (!endGame) {
      val gameModeMsg =
        "Choose your game (1,2,3):\n" +
          "1- Player vs Computer\n" +
          "2- Computer vs Computer\n" +
          "3- Exit"

      println(gameModeMsg)
      val userInput = getInput()

      userInput match {
        case Some(choice) if (choice == 1) => playPlayerVsComputerGame()
        case Some(choice) if (choice == 2) => playComputerVsComputerGame()
        case Some(choice) if (choice == 3) => {
          println("Goodbye! ;)")
          endGame = true
        }
        case _ => println("Please choose a valid value\n")
      }
    }
  }

  /**
   * Manage the match between the user and the computer
   */
  private def playPlayerVsComputerGame() {

    var endMatch = false

    while (!endMatch) {

      var moves = scala.collection.mutable.Map[Int, Move]()
      println("Chose your move")
      //print the selection of the moves
      Moves.all.zipWithIndex.foreach {
        case (e, i) =>
          println((i + 1) + " - " + e)
          moves += ((i + 1) -> e)
      }
      val userInput = getInput()

      userInput match {
        case Some(choice) if (moves.get(choice).isDefined) => {
          val gameResult = PlayerVsComputerGame(moves.get(choice).get, RandomComputerAI()).play
          println("You played: " + gameResult._1)
          println("Computer played: " + gameResult._2)
          gameResult._3 match {
            case PlayerOneWins => println("YOU WIN!!!\n")
            case PlayerTwoWins => println("You lose.\n")
            case Tie           => println("It's a tied game!\n")
          }
          endMatch = true
        }
        case _ => println("Please choose a valid value")
      }

    }
  }

  /**
   * Manage the match between 2 computers
   */
  private def playComputerVsComputerGame() {
    val gameResult = ComputerVsComputerGame(RandomComputerAI(), RandomComputerAI()).play
    println("Computer 1 played: " + gameResult._1)
    println("Computer 2 played: " + gameResult._2)
    gameResult._3 match {
      case PlayerOneWins => println("Computer 1 wins!\n")
      case PlayerTwoWins => println("Computer 2 wins!\n")
      case Tie           => println("It's a tied game!\n")
    }
  }

  /**
   * Get the keyboard input from the user
   */
  private def getInput(): Option[Int] = {
    for {
      input <- Option(StdIn.readLine())
      option <- Try(input.toInt).toOption
    } yield option
  }
}