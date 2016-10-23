package it.com.pasculli.rps

import scala.util.Random

/**
 * Represent the game
 */
abstract class Game {

  protected def computeResult(player1Move: Move, player2Move: Move): Result = {
    if (player1Move defeat player2Move) PlayerOneWins
    else if (player2Move defeat player1Move) PlayerTwoWins
    else Tie
  }
  def play: (Move, Move, Result)
}

/**
 * Represent Player VS Computer Game
 */
case class PlayerVsComputerGame(playerMove: Move, ai: ComputerAI) extends Game {

  override def play: (Move, Move, Result) = {
    val computerMove = ai.playMove
    (playerMove, computerMove, computeResult(playerMove, computerMove))
  }
}

/**
 * Represent Computer VS Computer Game
 */
case class ComputerVsComputerGame(aiPlayerOne: ComputerAI, aiPlayerTwo: ComputerAI) extends Game {

  override def play: (Move, Move, Result) = {
    val player1Move = aiPlayerOne.playMove
    val player2Move = aiPlayerTwo.playMove
    (player1Move, player2Move, computeResult(player1Move, player2Move))
  }
}

abstract class ComputerAI {
  def playMove: Move
}

case class RandomComputerAI() extends ComputerAI {
  override def playMove: Move = {
    Random.shuffle(Moves.all).head
  }
}

sealed trait Result

case object PlayerOneWins extends Result

case object PlayerTwoWins extends Result

case object Tie extends Result
