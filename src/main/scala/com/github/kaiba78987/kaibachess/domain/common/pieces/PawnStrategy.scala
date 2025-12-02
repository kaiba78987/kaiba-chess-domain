package com.github.kaiba78987.kaibachess.domain.common.pieces

import com.github.kaiba78987.kaibachess.domain.common.Coordinate
import com.github.kaiba78987.kaibachess.domain.common.movement.Move
import com.github.kaiba78987.kaibachess.domain.common.position.Position
import com.github.kaiba78987.kaibachess.domain.common.Color
import com.github.kaiba78987.kaibachess.domain.common.Rank

object PawnStrategy extends PieceStrategy:

  val pieceType = PieceType.Pawn

  def potentialMoves(
      from: Coordinate,
      position: Position,
      self: Piece
  ): List[Coordinate] =
    val direction = self.color match
      case Color.White => Coordinate.Direction.North
      case Color.Black => Coordinate.Direction.South

    val forwardOne = Coordinate.navigate(from, direction)
    val forwardTwo = forwardOne.flatMap(c => Coordinate.navigate(c, direction))

    val moves = scala.collection.mutable.ListBuffer[Coordinate]()

    // Move forward 1
    if forwardOne.isDefined && position.board(forwardOne.get).isEmpty then
      moves += forwardOne.get
      // Move forward 2
      val isStartingRank =
        (self.color == Color.White && from.rank == Rank.Rank2) || (self.color == Color.Black && from.rank == Rank.Rank7)
      if isStartingRank && forwardTwo.isDefined && position
          .board(forwardTwo.get)
          .isEmpty
      then moves += forwardTwo.get

    // Captures
    val captureDirections = self.color match
      case Color.White =>
        List(Coordinate.Direction.NorthEast, Coordinate.Direction.NorthWest)
      case Color.Black =>
        List(Coordinate.Direction.SouthEast, Coordinate.Direction.SouthWest)

    for dir <- captureDirections do
      val target = Coordinate.navigate(from, dir)
      if target.isDefined then
        val targetCoord = target.get
        // Normal capture
        position.board(targetCoord) match
          case Some(piece) if piece.color != self.color => moves += targetCoord
          case _ => // Check En Passant
            if position.enPassantTarget.contains(targetCoord) then
              moves += targetCoord

    moves.toList
