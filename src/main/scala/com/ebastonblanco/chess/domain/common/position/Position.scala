package com.ebastonblanco.chess.domain.common.position

import com.ebastonblanco.chess.domain.common.Color
import com.ebastonblanco.chess.domain.common.Coordinate

final case class Position(board: Board, activeSide: Color, castlingAvailability: CastlingAvailability, enPassantTarget: Option[Coordinate], halfmoveClock: Int, fullmoveNumber: Int)

object Position extends FenWritter[Position]:

    override def toFen(positionComponent: Position): String =
        val boardFen = BoardFENWritter.toFen(positionComponent.board)

        val activeSideFen = positionComponent.activeSide match
        case Color.White => "w"
        case Color.Black => "b"

        val castlingAvailabilityFen = CastlingAvailability.toFen(positionComponent.castlingAvailability)
        
        val enPassantTargetFen = positionComponent.enPassantTarget.map(_.toString).getOrElse("-")
        val halfmoveClockFen = positionComponent.halfmoveClock.toString
        val fullmoveNumberFen = positionComponent.fullmoveNumber.toString
    
        s"$boardFen $activeSideFen $castlingAvailabilityFen $enPassantTargetFen $halfmoveClockFen $fullmoveNumberFen"

        