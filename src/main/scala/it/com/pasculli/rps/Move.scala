package it.com.pasculli.rps

/**
 * Represent a move in the game
 */
sealed abstract class Move {
  val defeatedMoves: Seq[Move]
  def defeat(other: Move): Boolean = { defeatedMoves.contains(other) }
}

case object Rock extends Move { val defeatedMoves = Seq[Move](Scissors) }
case object Paper extends Move { val defeatedMoves = Seq[Move](Rock) }
case object Scissors extends Move { val defeatedMoves = Seq[Move](Paper) }

case object Moves {
  val all: List[Move] = List(Rock, Paper, Scissors)
}