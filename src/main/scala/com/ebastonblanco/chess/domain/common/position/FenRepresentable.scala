package com.ebastonblanco.chess.domain.common.position

trait FenRepresentable[T]:
  
  def toFen: String

  def fromFen(fen: String): T

