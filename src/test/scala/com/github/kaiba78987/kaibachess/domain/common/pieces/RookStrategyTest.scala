package com.github.kaiba78987.kaibachess.domain.common.pieces

import munit.FunSuite
import com.github.kaiba78987.kaibachess.domain.common._
import com.github.kaiba78987.kaibachess.domain.common.position._

class RookStrategyTest extends FunSuite with StrategyTestHelpers {

  test("Rook moves empty board") {
    val rook = Piece(PieceType.Rook, Color.White)
    val start = Coordinate(File.D, Rank.Rank4)
    val board = Board(Map(start -> rook))
    val position = createPosition(board)

    val moves = rook.potentialMoves(start, position)

    // Check some expected moves in all directions
    assert(moves.contains(Coordinate(File.D, Rank.Rank8))) // North
    assert(moves.contains(Coordinate(File.D, Rank.Rank1))) // South
    assert(moves.contains(Coordinate(File.A, Rank.Rank4))) // West
    assert(moves.contains(Coordinate(File.H, Rank.Rank4))) // East

    // Should be 14 moves total on empty board from D4
    assert(moves.size == 14)
  }

  test("Rook blocked by friendly piece") {
    val rook = Piece(PieceType.Rook, Color.White)
    val blocker = Piece(PieceType.Pawn, Color.White)
    val start = Coordinate(File.D, Rank.Rank4)
    val blockPos = Coordinate(File.D, Rank.Rank6)
    val board = Board(Map(start -> rook, blockPos -> blocker))
    val position = createPosition(board)

    val moves = rook.potentialMoves(start, position)

    assert(moves.contains(Coordinate(File.D, Rank.Rank5)))
    assert(!moves.contains(blockPos)) // Cannot capture friendly
    assert(!moves.contains(Coordinate(File.D, Rank.Rank7))) // Blocked
  }

  test("Rook captures enemy piece") {
    val rook = Piece(PieceType.Rook, Color.White)
    val enemy = Piece(PieceType.Pawn, Color.Black)
    val start = Coordinate(File.D, Rank.Rank4)
    val enemyPos = Coordinate(File.D, Rank.Rank6)
    val board = Board(Map(start -> rook, enemyPos -> enemy))
    val position = createPosition(board)

    val moves = rook.potentialMoves(start, position)

    assert(moves.contains(Coordinate(File.D, Rank.Rank5)))
    assert(moves.contains(enemyPos)) // Can capture
    assert(
      !moves.contains(Coordinate(File.D, Rank.Rank7))
    ) // Blocked after capture
  }
}
