package it.com.pasculli.rps

import org.scalatest.FlatSpec
import org.scalatest.Matchers

/**
 * Tests for Move
 */
class MoveTest extends FlatSpec with Matchers {

  "Rock" should "defeat Scissors" in {
    Rock defeat Scissors should be (true)
  }

  "Scissors" should "defeat Paper" in {
    Scissors defeat Paper should be (true)
  }

  "Paper" should "defeat Rock" in {
    Paper defeat Rock should be (true)
  }
  
  "Rock" should "not defeat Paper" in {
    Rock defeat Paper should be (false)
  }

  "Scissors" should "not defeat Rock" in {
    Scissors defeat Rock should be (false)
  }

  "Paper" should "not defeat Scissors" in {
    Paper defeat Scissors should be (false)
  }
  
  "Moves all" should "contain all the moves" in {
    Moves.all should contain (Rock) 
    Moves.all should contain (Scissors)
    Moves.all should contain(Paper)
  }

}
