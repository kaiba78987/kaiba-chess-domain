package com.github.kaiba78987.kaibachess.domain.common.pieces

import com.github.kaiba78987.kaibachess.domain.common.Coordinate
import com.github.kaiba78987.kaibachess.domain.common.movement.Move
import com.github.kaiba78987.kaibachess.domain.common.position.Position

object QueenStrategy extends PieceStrategy:

  val pieceType = PieceType.Queen

  def potentialMoves(
      from: Coordinate,
      position: Position,
      self: Piece
  ): List[Coordinate] =
    BishopStrategy.potentialMoves(from, position, self) ++ RookStrategy
      .potentialMoves(from, position, self)
