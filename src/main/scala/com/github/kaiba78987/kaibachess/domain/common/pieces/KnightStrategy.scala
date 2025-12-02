package com.github.kaiba78987.kaibachess.domain.common.pieces

import com.github.kaiba78987.kaibachess.domain.common.Coordinate
import com.github.kaiba78987.kaibachess.domain.common.movement.Move
import com.github.kaiba78987.kaibachess.domain.common.position.Position
import com.github.kaiba78987.kaibachess.domain.common.File
import com.github.kaiba78987.kaibachess.domain.common.Rank

object KnightStrategy extends PieceStrategy:

  val pieceType = PieceType.Knight

  def potentialMoves(
      from: Coordinate,
      position: Position,
      self: Piece
  ): List[Coordinate] =
    val offsets = List(
      (1, 2),
      (1, -2),
      (-1, 2),
      (-1, -2),
      (2, 1),
      (2, -1),
      (-2, 1),
      (-2, -1)
    )

    val moves = scala.collection.mutable.ListBuffer[Coordinate]()

    for (fileOffset, rankOffset) <- offsets do
      val newFileOrdinal = from.file.ordinal + fileOffset
      val newRankOrdinal = from.rank.ordinal + rankOffset

      if newFileOrdinal >= 0 && newFileOrdinal < File.values.length &&
        newRankOrdinal >= 0 && newRankOrdinal < Rank.values.length
      then
        val target =
          Coordinate(File.values(newFileOrdinal), Rank.values(newRankOrdinal))
        position.board(target) match
          case Some(piece)
              if piece.color == self.color => // Blocked by own piece
          case _ => moves += target

    moves.toList
