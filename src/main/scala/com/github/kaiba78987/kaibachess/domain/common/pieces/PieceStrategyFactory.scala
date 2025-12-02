package com.github.kaiba78987.kaibachess.domain.common.pieces

object PieceStrategyFactory:
  def apply(pieceType: PieceType): PieceStrategy =
    pieceType match
      case PieceType.Pawn   => PawnStrategy
      case PieceType.Knight => KnightStrategy
      case PieceType.Bishop => BishopStrategy
      case PieceType.Rook   => RookStrategy
      case PieceType.Queen  => QueenStrategy
      case PieceType.King   => KingStrategy
