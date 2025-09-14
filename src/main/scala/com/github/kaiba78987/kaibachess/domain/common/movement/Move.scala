package com.github.kaiba78987.kaibachess.domain.common.movement

import com.github.kaiba78987.kaibachess.domain.common.Coordinate
import com.github.kaiba78987.kaibachess.domain.common.pieces.PieceType

trait Move:

    def from: Coordinate

    def to: Coordinate

    def promotion: Option[PieceType]
