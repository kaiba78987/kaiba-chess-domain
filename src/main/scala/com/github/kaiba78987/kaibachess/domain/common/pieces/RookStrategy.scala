package com.github.kaiba78987.kaibachess.domain.common.pieces

import com.github.kaiba78987.kaibachess.domain.common.Coordinate
import com.github.kaiba78987.kaibachess.domain.common.movement.Move
import com.github.kaiba78987.kaibachess.domain.common.position.Position
import com.github.kaiba78987.kaibachess.domain.common.Coordinate.Direction

object RookStrategy extends PieceStrategy:

  val pieceType = PieceType.Rook

  def potentialMoves(
      from: Coordinate,
      position: Position,
      self: Piece
  ): List[Coordinate] =
    val directions =
      List(Direction.North, Direction.South, Direction.East, Direction.West)
    directions.flatMap(dir => slide(from, dir, position, self))

  private def slide(
      from: Coordinate,
      direction: Direction,
      position: Position,
      self: Piece
  ): List[Coordinate] =
    val moves = scala.collection.mutable.ListBuffer[Coordinate]()
    var current = Coordinate.navigate(from, direction)

    while current.isDefined do
      val coord = current.get
      position.board(coord) match
        case None =>
          moves += coord
          current = Coordinate.navigate(coord, direction)
        case Some(piece) =>
          if piece.color != self.color then moves += coord
          current = None // Stop sliding

    moves.toList
