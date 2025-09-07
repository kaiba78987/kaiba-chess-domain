package com.ebastonblanco.chess.domain.common.movement

import com.ebastonblanco.chess.domain.common.Coordinate
import com.ebastonblanco.chess.domain.common.pieces.PieceType

trait Move:

    def from: Coordinate

    def to: Coordinate

    def promotion: Option[PieceType]
