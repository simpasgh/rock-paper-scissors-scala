package it.com.pasculli.rps

import org.scalamock.scalatest.MockFactory
import org.scalatest.FunSpec
import org.scalatest.Matchers

/**
 * Tests for Game
 */
class GameTest extends FunSpec with Matchers with MockFactory {

  describe("A game") {
    describe("Computer VS Computer game") {
      it("return a coherent state of the Computer VS Computer game with the played moves") {
        ComputerVsComputerGame(getAIMock(Rock), getAIMock(Rock)).play shouldBe (Rock, Rock, Tie)
        ComputerVsComputerGame(getAIMock(Rock), getAIMock(Scissors)).play shouldBe (Rock, Scissors, PlayerOneWins)
        ComputerVsComputerGame(getAIMock(Rock), getAIMock(Paper)).play shouldBe (Rock, Paper, PlayerTwoWins)
        ComputerVsComputerGame(getAIMock(Paper), getAIMock(Rock)).play shouldBe (Paper, Rock, PlayerOneWins)
        ComputerVsComputerGame(getAIMock(Paper), getAIMock(Scissors)).play shouldBe (Paper, Scissors, PlayerTwoWins)
        ComputerVsComputerGame(getAIMock(Paper), getAIMock(Paper)).play shouldBe (Paper, Paper, Tie)
        ComputerVsComputerGame(getAIMock(Scissors), getAIMock(Rock)).play shouldBe (Scissors, Rock, PlayerTwoWins)
        ComputerVsComputerGame(getAIMock(Scissors), getAIMock(Scissors)).play shouldBe (Scissors, Scissors, Tie)
        ComputerVsComputerGame(getAIMock(Scissors), getAIMock(Paper)).play shouldBe (Scissors, Paper, PlayerOneWins)

      }
    }
    describe("Player VS Computer game") {
      it("return a coherent state of the game Player Vs Computer with the played moves") {
        PlayerVsComputerGame(Rock, getAIMock(Rock)).play shouldBe (Rock, Rock, Tie)
        PlayerVsComputerGame(Rock, getAIMock(Scissors)).play shouldBe (Rock, Scissors, PlayerOneWins)
        PlayerVsComputerGame(Rock, getAIMock(Paper)).play shouldBe (Rock, Paper, PlayerTwoWins)
        PlayerVsComputerGame(Paper, getAIMock(Rock)).play shouldBe (Paper, Rock, PlayerOneWins)
        PlayerVsComputerGame(Paper, getAIMock(Scissors)).play shouldBe (Paper, Scissors, PlayerTwoWins)
        PlayerVsComputerGame(Paper, getAIMock(Paper)).play shouldBe (Paper, Paper, Tie)
        PlayerVsComputerGame(Scissors, getAIMock(Rock)).play shouldBe (Scissors, Rock, PlayerTwoWins)
        PlayerVsComputerGame(Scissors, getAIMock(Scissors)).play shouldBe (Scissors, Scissors, Tie)
        PlayerVsComputerGame(Scissors, getAIMock(Paper)).play shouldBe (Scissors, Paper, PlayerOneWins)
      }
    }
  }

  describe("Random Computer AI") {
    it("returns a valid random move for the game") {
      List(Rock, Paper, Scissors) contains RandomComputerAI().playMove
    }
  }

  /**
   * Returns a mock of the Computer AI
   */
  private def getAIMock(move: Move): ComputerAI = {
    val aiMock = mock[RandomComputerAI]
    (aiMock.playMove _).expects().returning(move)

    aiMock
  }
}
