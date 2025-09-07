package com.ebastonblanco.chess.domain.common.pieces

trait Piece
  

enum PieceType(val representation: Char):
  
    case Pawn extends PieceType('P')
    case Knight extends PieceType('N')
    case Bishop extends PieceType('B')
    case Rook extends PieceType('R')
    case Queen extends PieceType('Q')
    case King extends PieceType('K')
