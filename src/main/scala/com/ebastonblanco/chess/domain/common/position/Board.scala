package com.ebastonblanco.chess.domain.common.position

import com.ebastonblanco.chess.domain.common.Coordinate
import com.ebastonblanco.chess.domain.common.pieces.Piece
import com.ebastonblanco.chess.domain.common.pieces.PieceType
import com.ebastonblanco.chess.domain.common.Color

trait Board:

  def apply(coordinate: Coordinate): Piece

  def update(coordinate: Coordinate, piece: Piece): Board

  def getPieces(side: Color): Set[(Coordinate, Piece)]

  def getPieces(side: Color, pieceType: PieceType): Set[(Coordinate, Piece)]
