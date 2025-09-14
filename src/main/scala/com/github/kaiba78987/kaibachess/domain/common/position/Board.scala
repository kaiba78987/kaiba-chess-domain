package com.github.kaiba78987.kaibachess.domain.common.position

import com.github.kaiba78987.kaibachess.domain.common.Coordinate
import com.github.kaiba78987.kaibachess.domain.common.pieces.Piece
import com.github.kaiba78987.kaibachess.domain.common.pieces.PieceType
import com.github.kaiba78987.kaibachess.domain.common.Color

trait Board:

  def apply(coordinate: Coordinate): Option[Piece]

  def update(coordinate: Coordinate, piece: Option[Piece]): Board

  def getPieces(side: Color): Set[(Coordinate, Piece)]

  def getPieces(side: Color, pieceType: PieceType): Set[(Coordinate, Piece)]
