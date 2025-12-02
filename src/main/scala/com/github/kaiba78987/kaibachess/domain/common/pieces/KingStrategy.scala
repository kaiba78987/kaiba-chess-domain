package com.github.kaiba78987.kaibachess.domain.common.pieces

import com.github.kaiba78987.kaibachess.domain.common.Coordinate
import com.github.kaiba78987.kaibachess.domain.common.movement.Move
import com.github.kaiba78987.kaibachess.domain.common.position.Position
import com.github.kaiba78987.kaibachess.domain.common.Coordinate.Direction
import com.github.kaiba78987.kaibachess.domain.common.Color
import com.github.kaiba78987.kaibachess.domain.common.position.Castling
import com.github.kaiba78987.kaibachess.domain.common.File
import com.github.kaiba78987.kaibachess.domain.common.Rank

object KingStrategy extends PieceStrategy:

  val pieceType = PieceType.King

  def potentialMoves(
      from: Coordinate,
      position: Position,
      self: Piece
  ): List[Coordinate] =
    val directions = Coordinate.Direction.values.toList
    val moves = scala.collection.mutable.ListBuffer[Coordinate]()

    // Normal moves
    for dir <- directions do
      Coordinate.navigate(from, dir).foreach { target =>
        position.board(target) match
          case None                                     => moves += target
          case Some(piece) if piece.color != self.color => moves += target
          case _                                        =>
      }

    // Castling
    val oppositeColor =
      if self.color == Color.White then Color.Black else Color.White

    if !isSquareAttacked(from, position, oppositeColor) then
      // Kingside
      if canCastleKingside(from, position, self.color, oppositeColor) then
        val rank = if self.color == Color.White then Rank.Rank1 else Rank.Rank8
        moves += Coordinate(File.G, rank)

      // Queenside
      if canCastleQueenside(from, position, self.color, oppositeColor) then
        val rank = if self.color == Color.White then Rank.Rank1 else Rank.Rank8
        moves += Coordinate(File.C, rank)

    moves.toList

  private def canCastleKingside(
      from: Coordinate,
      position: Position,
      color: Color,
      oppositeColor: Color
  ): Boolean =
    val castlingRight =
      if color == Color.White then Castling.WhiteKingside
      else Castling.BlackKingside
    if position.castlingAvailability.contains(castlingRight) then
      val rank = from.rank
      // Path F, G
      val f = Coordinate(File.F, rank)
      val g = Coordinate(File.G, rank)

      position.board(f).isEmpty && position.board(g).isEmpty &&
      !isSquareAttacked(f, position, oppositeColor) &&
      !isSquareAttacked(g, position, oppositeColor)
    else false

  private def canCastleQueenside(
      from: Coordinate,
      position: Position,
      color: Color,
      oppositeColor: Color
  ): Boolean =
    val castlingRight =
      if color == Color.White then Castling.WhiteQueenside
      else Castling.BlackQueenside
    if position.castlingAvailability.contains(castlingRight) then
      val rank = from.rank
      // Path D, C, B (B must be empty, D and C must be safe and empty)
      val d = Coordinate(File.D, rank)
      val c = Coordinate(File.C, rank)
      val b = Coordinate(File.B, rank)

      position.board(d).isEmpty && position.board(c).isEmpty && position
        .board(b)
        .isEmpty &&
      !isSquareAttacked(d, position, oppositeColor) &&
      !isSquareAttacked(c, position, oppositeColor)
    else false

  private def isSquareAttacked(
      target: Coordinate,
      position: Position,
      attackerColor: Color
  ): Boolean =
    val enemyPieces = position.board.getPieces(attackerColor)
    enemyPieces.exists { case (coord, piece) =>
      piece.pieceType match
        case PieceType.Pawn =>
          val attackDirs =
            if piece.color == Color.White
            then
              List(
                Coordinate.Direction.NorthEast,
                Coordinate.Direction.NorthWest
              )
            else
              List(
                Coordinate.Direction.SouthEast,
                Coordinate.Direction.SouthWest
              )

          attackDirs.exists { dir =>
            Coordinate.navigate(coord, dir).contains(target)
          }
        case PieceType.King =>
          Coordinate.Direction.values.exists { dir =>
            Coordinate.navigate(coord, dir).contains(target)
          }
        case _ =>
          piece.potentialMoves(coord, position).contains(target)
    }
