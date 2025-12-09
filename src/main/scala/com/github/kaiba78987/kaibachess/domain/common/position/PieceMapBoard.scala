package com.github.kaiba78987.kaibachess.domain.common.position

import com.github.kaiba78987.kaibachess.domain.common.Coordinate
import com.github.kaiba78987.kaibachess.domain.common.pieces.Piece
import com.github.kaiba78987.kaibachess.domain.common.pieces.PieceType
import com.github.kaiba78987.kaibachess.domain.common.Color

case class PieceMapBoard(pieces: Map[Coordinate, Piece]) extends Board:
  def apply(coordinate: Coordinate): Option[Piece] = pieces.get(coordinate)

  def set(coordinate: Coordinate, piece: Piece): Board =
    copy(pieces = pieces + (coordinate -> piece))

  def remove(coordinate: Coordinate): Board =
    copy(pieces = pieces - coordinate)

  def getPieces(side: Color): Set[(Coordinate, Piece)] =
    pieces.filter(_._2.color == side).toSet

  def getPieces(side: Color, pieceType: PieceType): Set[(Coordinate, Piece)] =
    pieces
      .filter(p => p._2.color == side && p._2.pieceType == pieceType)
      .toSet

  def getPiece(coordinate: Coordinate): Option[Piece] = pieces.get(coordinate)

object PieceMapBoard extends BoardFactory:
  def empty: Board = PieceMapBoard(Map.empty)
  def apply(pieces: Map[Coordinate, Piece]): Board = new PieceMapBoard(pieces)
