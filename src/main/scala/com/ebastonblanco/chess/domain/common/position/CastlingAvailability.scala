package com.ebastonblanco.chess.domain.common.position

import scala.collection.BitSet

class CastlingAvailability private (private val availableCastlings: BitSet):

    def +(castling: Castling): CastlingAvailability = new CastlingAvailability(availableCastlings + castling.ordinal)

    def - (castling: Castling): CastlingAvailability = new CastlingAvailability(availableCastlings - castling.ordinal)

    def contains(castling: Castling): Boolean = availableCastlings.contains(castling.ordinal)
    
  
object CastlingAvailability extends FENReader[CastlingAvailability] with FenWritter[CastlingAvailability]:

    def empty: CastlingAvailability = new CastlingAvailability(BitSet.empty)

    def all: CastlingAvailability = new CastlingAvailability(BitSet(Castling.values.map(_.ordinal)*))

    def apply(availableCastlings: Set[Castling]) =
        new CastlingAvailability(BitSet(availableCastlings.map(_.ordinal).toSeq*))

    def fromFen(fen: String): CastlingAvailability =
        if fen == "-" then new CastlingAvailability(BitSet.empty)
        else
            val castlings = fen.flatMap {
                case 'K' => Some(Castling.WhiteKingside)
                case 'Q' => Some(Castling.WhiteQueenside)
                case 'k' => Some(Castling.BlackKingside)
                case 'q' => Some(Castling.BlackQueenside)
                case _   => None
            }
            new CastlingAvailability(BitSet(castlings.map(_.ordinal)*))

    def toFen(positionComponent: CastlingAvailability): String = 
        if positionComponent.availableCastlings.isEmpty then "-"
        else
            Castling.values
                .filter(c => positionComponent.availableCastlings.contains(c.ordinal))
                .map(_.representation)
                .mkString


enum Castling(val representation: String):

    case WhiteKingside extends Castling("K")
    case WhiteQueenside extends Castling("Q")
    case BlackKingside extends Castling("k")
    case BlackQueenside extends Castling("q")
