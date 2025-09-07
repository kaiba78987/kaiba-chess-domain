package com.ebastonblanco.chess.domain.common.pieces

import com.ebastonblanco.chess.domain.common.Color
import com.ebastonblanco.chess.domain.common.movement.Move
import com.ebastonblanco.chess.domain.common.position.Position
import com.ebastonblanco.chess.domain.common.position.FENReader
import com.ebastonblanco.chess.domain.common.position.FenWritter

final case class Piece private(private val strategy: PieceStrategy, val color: Color):

  def pieceType = strategy.pieceType

    def canMove(move: Move, position: Position): Boolean =
      strategy.canMove(move, position, this)


object Piece extends FenWritter[Piece]:

    def toFen(piece: Piece): String =
      val representation = piece.pieceType.representation
      piece.color match
        case Color.White => representation.toString
        case Color.Black => representation.toLower.toString


trait PieceStrategy:

    val pieceType: PieceType

    def canMove(move: Move, position: Position, self: Piece): Boolean


enum PieceType(val representation: Char):
  
    case Pawn extends PieceType('P')
    case Knight extends PieceType('N')
    case Bishop extends PieceType('B')
    case Rook extends PieceType('R')
    case Queen extends PieceType('Q')
    case King extends PieceType('K')
