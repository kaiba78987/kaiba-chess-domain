package com.github.kaiba78987.kaibachess.domain.common.movement

import com.github.kaiba78987.kaibachess.domain.common.Coordinate
import com.github.kaiba78987.kaibachess.domain.common.pieces.PieceType

final class UCIMove(val from: Coordinate, val to: Coordinate, val promotion: Option[PieceType]) extends Move:
    def this(from: Coordinate, to: Coordinate) = this(from, to, None)