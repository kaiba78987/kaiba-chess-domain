package com.ebastonblanco.chess.domain.common.position

import com.ebastonblanco.chess.domain.common.Color
import com.ebastonblanco.chess.domain.common.Coordinate

final case class Position(board: Board, activeSide: Color, castlingAvailability: CastlingAvailability, enPassantTarget: Option[Coordinate], halfmoveClock: Int, fullmoveNumber: Int)
