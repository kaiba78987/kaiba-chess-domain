package com.github.kaiba78987.kaibachess.domain.common.position

trait FENReader[T]:

  def fromFen(fen: String): T
