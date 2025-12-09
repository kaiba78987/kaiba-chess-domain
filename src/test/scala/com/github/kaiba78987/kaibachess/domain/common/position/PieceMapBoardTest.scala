package com.github.kaiba78987.kaibachess.domain.common.position

import munit.FunSuite
import com.github.kaiba78987.kaibachess.domain.common._
import com.github.kaiba78987.kaibachess.domain.common.pieces._

class PieceMapBoardTest extends FunSuite {

  test("PieceMapBoard.apply returns piece at coordinate") {
    val pawn = Piece(PieceType.Pawn, Color.White)
    val coord = Coordinate(File.A, Rank.Rank1)
    val board = PieceMapBoard(Map(coord -> pawn))

    assert(board(coord).contains(pawn))
    assert(board(Coordinate(File.B, Rank.Rank1)).isEmpty)
  }

  test("PieceMapBoard.set adds a piece and returns new board") {
    val board = PieceMapBoard(Map.empty)
    val pawn = Piece(PieceType.Pawn, Color.White)
    val coord = Coordinate(File.A, Rank.Rank1)

    val newBoard = board.set(coord, pawn)

    assert(newBoard(coord).contains(pawn))
    assert(board(coord).isEmpty) // Immutability check
  }

  test("PieceMapBoard.remove removes a piece and returns new board") {
    val pawn = Piece(PieceType.Pawn, Color.White)
    val coord = Coordinate(File.A, Rank.Rank1)
    val board = PieceMapBoard(Map(coord -> pawn))

    val newBoard = board.remove(coord)

    assert(newBoard(coord).isEmpty)
    assert(board(coord).contains(pawn)) // Immutability check
  }

  test("PieceMapBoard.getPieces(side) returns pieces for specific color") {
    val whitePawn = Piece(PieceType.Pawn, Color.White)
    val blackPawn = Piece(PieceType.Pawn, Color.Black)
    val whiteCoord = Coordinate(File.A, Rank.Rank1)
    val blackCoord = Coordinate(File.A, Rank.Rank8)

    val board = PieceMapBoard(
      Map(
        whiteCoord -> whitePawn,
        blackCoord -> blackPawn
      )
    )

    val whitePieces = board.getPieces(Color.White)
    assert(whitePieces.size == 1)
    assert(whitePieces.contains((whiteCoord, whitePawn)))

    val blackPieces = board.getPieces(Color.Black)
    assert(blackPieces.size == 1)
    assert(blackPieces.contains((blackCoord, blackPawn)))
  }

  test("PieceMapBoard.getPieces(side, type) returns specific pieces") {
    val whitePawn = Piece(PieceType.Pawn, Color.White)
    val whiteRook = Piece(PieceType.Rook, Color.White)
    val blackPawn = Piece(PieceType.Pawn, Color.Black)

    val board = PieceMapBoard(
      Map(
        Coordinate(File.A, Rank.Rank2) -> whitePawn,
        Coordinate(File.A, Rank.Rank1) -> whiteRook,
        Coordinate(File.A, Rank.Rank7) -> blackPawn
      )
    )

    val whitePawns = board.getPieces(Color.White, PieceType.Pawn)
    assert(whitePawns.size == 1)
    assert(whitePawns.head._2 == whitePawn)

    val whiteRooks = board.getPieces(Color.White, PieceType.Rook)
    assert(whiteRooks.size == 1)
    assert(whiteRooks.head._2 == whiteRook)
  }

  test("PieceMapBoard.getPiece returns option of piece") {
    val pawn = Piece(PieceType.Pawn, Color.White)
    val coord = Coordinate(File.E, Rank.Rank4)
    val board = PieceMapBoard(Map(coord -> pawn))

    assert(board.getPiece(coord).contains(pawn))
    assert(board.getPiece(Coordinate(File.A, Rank.Rank1)).isEmpty)
  }
}
