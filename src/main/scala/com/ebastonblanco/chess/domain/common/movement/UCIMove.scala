package com.ebastonblanco.chess.domain.common.movement

import com.ebastonblanco.chess.domain.common.Coordinate
import com.ebastonblanco.chess.domain.common.pieces.PieceType

final class UCIMove(val from: Coordinate, val to: Coordinate, val promotion: Option[PieceType]) extends Move:
    def this(from: Coordinate, to: Coordinate) = this(from, to, None)