package com.github.kaiba78987.kaibachess.domain.common.pieces

import munit.FunSuite
import com.github.kaiba78987.kaibachess.domain.common._
import com.github.kaiba78987.kaibachess.domain.common.position._
import com.github.kaiba78987.kaibachess.domain.common.pieces._

class StrategiesTest extends FunSuite {

  case class TestBoard(pieces: Map[Coordinate, Piece]) extends Board {
    def apply(coordinate: Coordinate): Option[Piece] = pieces.get(coordinate)
    def update(coordinate: Coordinate, piece: Option[Piece]): Board =
      piece match
        case Some(p) => copy(pieces = pieces + (coordinate -> p))
        case None    => copy(pieces = pieces - coordinate)

    def getPieces(side: Color): Set[(Coordinate, Piece)] =
      pieces.filter(_._2.color == side).toSet

    def getPieces(side: Color, pieceType: PieceType): Set[(Coordinate, Piece)] =
      pieces
        .filter(p => p._2.color == side && p._2.pieceType == pieceType)
        .toSet
  }

  def createPosition(
      board: Board,
      activeSide: Color = Color.White,
      castling: CastlingAvailability = CastlingAvailability.all,
      enPassant: Option[Coordinate] = None
  ): Position =
    Position(board, activeSide, castling, enPassant, 0, 1)

  test("Pawn moves white") {
    val pawn = Piece(PieceType.Pawn, Color.White)
    val start = Coordinate(File.E, Rank.Rank2)
    val board = TestBoard(Map(start -> pawn))
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
    val board = TestBoard(Map(start -> pawn, target -> enemy))
    val position = createPosition(board)

    val moves = pawn.potentialMoves(start, position)
    assert(moves.contains(target))
    assert(moves.contains(Coordinate(File.E, Rank.Rank5)))
  }

  test("Knight moves") {
    val knight = Piece(PieceType.Knight, Color.White)
    val start = Coordinate(File.E, Rank.Rank4)
    val board = TestBoard(Map(start -> knight))
    val position = createPosition(board)

    val moves = knight.potentialMoves(start, position)
    assert(moves.contains(Coordinate(File.F, Rank.Rank6)))
    assert(moves.contains(Coordinate(File.D, Rank.Rank6)))
    assert(moves.contains(Coordinate(File.F, Rank.Rank2)))
    assert(moves.contains(Coordinate(File.D, Rank.Rank2)))
    assert(moves.contains(Coordinate(File.G, Rank.Rank5)))
    assert(moves.contains(Coordinate(File.C, Rank.Rank5)))
    assert(moves.contains(Coordinate(File.G, Rank.Rank3)))
    assert(moves.contains(Coordinate(File.C, Rank.Rank3)))
  }

  test("King castling kingside white") {
    val king = Piece(PieceType.King, Color.White)
    val start = Coordinate(File.E, Rank.Rank1)
    val board = TestBoard(Map(start -> king)) // Empty F1, G1
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
      TestBoard(Map(start -> king, Coordinate(File.F, Rank.Rank1) -> blocker))
    val position = createPosition(board, castling = CastlingAvailability.all)

    val moves = king.potentialMoves(start, position)
    assert(!moves.contains(Coordinate(File.G, Rank.Rank1)))
  }
}
