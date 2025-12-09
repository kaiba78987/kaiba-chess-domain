package com.github.kaiba78987.kaibachess.domain.common.position

import com.github.kaiba78987.kaibachess.domain.common.Coordinate
import com.github.kaiba78987.kaibachess.domain.common.pieces.Piece
import com.github.kaiba78987.kaibachess.domain.common.pieces.PieceType
import com.github.kaiba78987.kaibachess.domain.common.Color

trait Board:

  def apply(coordinate: Coordinate): Option[Piece]

  def set(coordinate: Coordinate, piece: Piece): Board

  def remove(coordinate: Coordinate): Board

  def getPieces(side: Color): Set[(Coordinate, Piece)]

  def getPieces(side: Color, pieceType: PieceType): Set[(Coordinate, Piece)]

  def getPiece(coordinate: Coordinate): Option[Piece]

trait BoardFactory:
  def empty: Board
  def apply(pieces: Map[Coordinate, Piece]): Board

object BoardFactory:
  given BoardFactory = PieceMapBoard

object Board:
  def empty(using factory: BoardFactory): Board = factory.empty
  def apply(pieces: Map[Coordinate, Piece])(using
      factory: BoardFactory
  ): Board = factory(pieces)
