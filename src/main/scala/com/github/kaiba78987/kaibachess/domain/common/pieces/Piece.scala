package com.github.kaiba78987.kaibachess.domain.common.pieces

import com.github.kaiba78987.kaibachess.domain.common.Color
import com.github.kaiba78987.kaibachess.domain.common.movement.Move
import com.github.kaiba78987.kaibachess.domain.common.position.Position
import com.github.kaiba78987.kaibachess.domain.common.position.FENReader
import com.github.kaiba78987.kaibachess.domain.common.position.FenWritter
import com.github.kaiba78987.kaibachess.domain.common.Coordinate

final case class Piece private (
    private val strategy: PieceStrategy,
    val color: Color
):

  def pieceType = strategy.pieceType

  def canMove(move: Move, position: Position): Boolean =
    strategy.canMove(move, position, this)

  def potentialMoves(from: Coordinate, position: Position): List[Coordinate] =
    strategy.potentialMoves(from, position, this)

object Piece extends FenWritter[Piece]:


  override def toFen(positionComponent: Piece): String = ???

  def apply(pieceType: PieceType, color: Color): Piece =
    new Piece(PieceStrategyFactory(pieceType), color)

def toFen(piece: Piece): String =
  val representation = piece.pieceType.representation
  piece.color match
    case Color.White => representation.toString
    case Color.Black => representation.toLower.toString

trait PieceStrategy:

  val pieceType: PieceType

  def canMove(move: Move, position: Position, self: Piece): Boolean =
    potentialMoves(move.from, position, self).contains(move.to)

  def potentialMoves(
      from: Coordinate,
      position: Position,
      self: Piece
  ): List[Coordinate]

enum PieceType(val representation: Char):

  case Pawn extends PieceType('P')
  case Knight extends PieceType('N')
  case Bishop extends PieceType('B')
  case Rook extends PieceType('R')
  case Queen extends PieceType('Q')
  case King extends PieceType('K')
