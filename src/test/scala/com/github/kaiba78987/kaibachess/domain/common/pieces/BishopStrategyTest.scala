package com.github.kaiba78987.kaibachess.domain.common.pieces

import munit.FunSuite
import com.github.kaiba78987.kaibachess.domain.common._
import com.github.kaiba78987.kaibachess.domain.common.position._

class BishopStrategyTest extends FunSuite with StrategyTestHelpers {

  test("Bishop moves empty board") {
    val bishop = Piece(PieceType.Bishop, Color.White)
    val start = Coordinate(File.D, Rank.Rank4)
    val board = Board(Map(start -> bishop))
    val position = createPosition(board)

    val moves = bishop.potentialMoves(start, position)

    // Check some expected moves in diagonals
    assert(moves.contains(Coordinate(File.A, Rank.Rank1))) // South-West
    assert(moves.contains(Coordinate(File.H, Rank.Rank8))) // North-East
    assert(moves.contains(Coordinate(File.A, Rank.Rank7))) // North-West
    assert(moves.contains(Coordinate(File.G, Rank.Rank1))) // South-East

    // Should be 13 moves total on empty board from D4
    assert(moves.size == 13)
  }

  test("Bishop blocked by friendly piece") {
    val bishop = Piece(PieceType.Bishop, Color.White)
    val blocker = Piece(PieceType.Pawn, Color.White)
    val start = Coordinate(File.D, Rank.Rank4)
    val blockPos = Coordinate(File.F, Rank.Rank6)
    val board = Board(Map(start -> bishop, blockPos -> blocker))
    val position = createPosition(board)

    val moves = bishop.potentialMoves(start, position)

    assert(moves.contains(Coordinate(File.E, Rank.Rank5)))
    assert(!moves.contains(blockPos)) // Cannot capture friendly
    assert(!moves.contains(Coordinate(File.G, Rank.Rank7))) // Blocked
  }

  test("Bishop captures enemy piece") {
    val bishop = Piece(PieceType.Bishop, Color.White)
    val enemy = Piece(PieceType.Pawn, Color.Black)
    val start = Coordinate(File.D, Rank.Rank4)
    val enemyPos = Coordinate(File.F, Rank.Rank6)
    val board = Board(Map(start -> bishop, enemyPos -> enemy))
    val position = createPosition(board)

    val moves = bishop.potentialMoves(start, position)

    assert(moves.contains(Coordinate(File.E, Rank.Rank5)))
    assert(moves.contains(enemyPos)) // Can capture
    assert(
      !moves.contains(Coordinate(File.G, Rank.Rank7))
    ) // Blocked after capture
  }
}
