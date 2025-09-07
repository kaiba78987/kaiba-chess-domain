package com.ebastonblanco.chess.domain.common.position

trait FENReader[T]:

  def fromFen(fen: String): T
