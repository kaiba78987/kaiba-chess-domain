package com.github.kaiba78987.kaibachess.domain.common.pieces

import munit.FunSuite
import com.github.kaiba78987.kaibachess.domain.common._
import com.github.kaiba78987.kaibachess.domain.common.position._

class QueenStrategyTest extends FunSuite with StrategyTestHelpers {

  test("Queen moves empty board") {
    val queen = Piece(PieceType.Queen, Color.White)
    val start = Coordinate(File.D, Rank.Rank4)
    val board = Board(Map(start -> queen))
    val position = createPosition(board)

    val moves = queen.potentialMoves(start, position)

    // Should have moves of both Rook and Bishop
    // Rook moves (14) + Bishop moves (13) = 27
    assert(moves.size == 27)

    // Check some specific moves
    assert(moves.contains(Coordinate(File.D, Rank.Rank8))) // North (Rook-like)
    assert(
      moves.contains(Coordinate(File.H, Rank.Rank8))
    ) // North-East (Bishop-like)
  }

  test("Queen blocked by friendly piece") {
    val queen = Piece(PieceType.Queen, Color.White)
    val blocker = Piece(PieceType.Pawn, Color.White)
    val start = Coordinate(File.D, Rank.Rank4)
    val blockPos = Coordinate(File.D, Rank.Rank6) // Blocks vertical
    val board = Board(Map(start -> queen, blockPos -> blocker))
    val position = createPosition(board)

    val moves = queen.potentialMoves(start, position)

    assert(moves.contains(Coordinate(File.D, Rank.Rank5)))
    assert(!moves.contains(blockPos))
    assert(!moves.contains(Coordinate(File.D, Rank.Rank7)))

    // Diagonal should still work
    assert(moves.contains(Coordinate(File.F, Rank.Rank6)))
  }

  test("Queen captures enemy piece") {
    val queen = Piece(PieceType.Queen, Color.White)
    val enemy = Piece(PieceType.Pawn, Color.Black)
    val start = Coordinate(File.D, Rank.Rank4)
    val enemyPos = Coordinate(File.F, Rank.Rank6) // Diagonal enemy
    val board = Board(Map(start -> queen, enemyPos -> enemy))
    val position = createPosition(board)

    val moves = queen.potentialMoves(start, position)

    assert(moves.contains(Coordinate(File.E, Rank.Rank5)))
    assert(moves.contains(enemyPos))
    assert(!moves.contains(Coordinate(File.G, Rank.Rank7)))
  }
}
