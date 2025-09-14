package com.github.kaiba78987.kaibachess.domain.common.position

trait FenWritter[T]:
  
  def toFen(positionComponent: T): String
