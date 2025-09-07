package com.ebastonblanco.chess.domain.common.position

trait FenWritter[T]:
  
  def toFen(positionComponent: T): String
