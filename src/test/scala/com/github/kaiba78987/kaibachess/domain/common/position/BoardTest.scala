package com.github.kaiba78987.kaibachess.domain.common.position

import munit.FunSuite
import com.github.kaiba78987.kaibachess.domain.common._
import com.github.kaiba78987.kaibachess.domain.common.pieces._

class BoardTest extends FunSuite {

  test("Board.empty creates an empty board") {
    val board = Board.empty
    assert(board.getPieces(Color.White).isEmpty)
    assert(board.getPieces(Color.Black).isEmpty)
  }

  test("Board.apply creates board with pieces") {
    val pawn = Piece(PieceType.Pawn, Color.White)
    val coord = Coordinate(File.A, Rank.Rank1)
    val board = Board(Map(coord -> pawn))

    assert(board(coord).contains(pawn))
    assert(board.getPieces(Color.White).size == 1)
  }

  test("Board.set adds a piece") {
    val board = Board.empty
    val pawn = Piece(PieceType.Pawn, Color.White)
    val coord = Coordinate(File.A, Rank.Rank1)

    val newBoard = board.set(coord, pawn)

    assert(newBoard(coord).contains(pawn))
    assert(board(coord).isEmpty) // Original board unchanged
  }

  test("Board.remove removes a piece") {
    val pawn = Piece(PieceType.Pawn, Color.White)
    val coord = Coordinate(File.A, Rank.Rank1)
    val board = Board(Map(coord -> pawn))

    val newBoard = board.remove(coord)

    assert(newBoard(coord).isEmpty)
    assert(board(coord).contains(pawn)) // Original board unchanged
  }
}
