package com.github.kaiba78987.kaibachess.domain.common.pieces

import munit.FunSuite
import com.github.kaiba78987.kaibachess.domain.common._
import com.github.kaiba78987.kaibachess.domain.common.position._

class PawnStrategyTest extends FunSuite with StrategyTestHelpers {

  test("Pawn moves white") {
    val pawn = Piece(PieceType.Pawn, Color.White)
    val start = Coordinate(File.E, Rank.Rank2)
    val board = Board(Map(start -> pawn))
    val position = createPosition(board)

    val moves = pawn.potentialMoves(start, position)
    assert(moves.contains(Coordinate(File.E, Rank.Rank3)))
    assert(moves.contains(Coordinate(File.E, Rank.Rank4)))
  }

  test("Pawn capture white") {
    val pawn = Piece(PieceType.Pawn, Color.White)
    val enemy = Piece(PieceType.Pawn, Color.Black)
    val start = Coordinate(File.E, Rank.Rank4)
    val target = Coordinate(File.D, Rank.Rank5)
    val board = Board(Map(start -> pawn, target -> enemy))
    val position = createPosition(board)

    val moves = pawn.potentialMoves(start, position)
    assert(moves.contains(target))
    assert(moves.contains(Coordinate(File.E, Rank.Rank5)))
  }
}
