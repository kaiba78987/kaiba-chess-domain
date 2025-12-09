package com.github.kaiba78987.kaibachess.domain.common.pieces

import com.github.kaiba78987.kaibachess.domain.common._
import com.github.kaiba78987.kaibachess.domain.common.position._

trait StrategyTestHelpers {

  def createPosition(
      board: Board,
      activeSide: Color = Color.White,
      castling: CastlingAvailability = CastlingAvailability.all,
      enPassant: Option[Coordinate] = None
  ): Position =
    Position(board, activeSide, castling, enPassant, 0, 1)
}
