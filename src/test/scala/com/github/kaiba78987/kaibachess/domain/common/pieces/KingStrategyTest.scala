package com.github.kaiba78987.kaibachess.domain.common.pieces

import munit.FunSuite
import com.github.kaiba78987.kaibachess.domain.common._
import com.github.kaiba78987.kaibachess.domain.common.position._

class KingStrategyTest extends FunSuite with StrategyTestHelpers {

  test("King castling kingside white") {
    val king = Piece(PieceType.King, Color.White)
    val start = Coordinate(File.E, Rank.Rank1)
    val board = Board(Map(start -> king)) // Empty F1, G1
    val position = createPosition(board, castling = CastlingAvailability.all)

    val moves = king.potentialMoves(start, position)
    assert(moves.contains(Coordinate(File.G, Rank.Rank1))) // Castling move
    assert(moves.contains(Coordinate(File.F, Rank.Rank1))) // Normal move
  }

  test("King castling blocked by piece") {
    val king = Piece(PieceType.King, Color.White)
    val start = Coordinate(File.E, Rank.Rank1)
    val blocker = Piece(PieceType.Bishop, Color.White)
    val board =
      Board(Map(start -> king, Coordinate(File.F, Rank.Rank1) -> blocker))
    val position = createPosition(board, castling = CastlingAvailability.all)

    val moves = king.potentialMoves(start, position)
    assert(!moves.contains(Coordinate(File.G, Rank.Rank1)))
  }
}
