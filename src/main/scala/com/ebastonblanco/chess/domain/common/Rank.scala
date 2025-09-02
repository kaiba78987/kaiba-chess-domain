package com.ebastonblanco.chess.domain.common

enum Rank(val representation: Char) extends Enum[Rank] with NavigableEnum[Rank]:
  case Rank1 extends Rank('1')
  case Rank2 extends Rank('2')
  case Rank3 extends Rank('3')
  case Rank4 extends Rank('4')
  case Rank5 extends Rank('5')
  case Rank6 extends Rank('6')
  case Rank7 extends Rank('7')
  case Rank8 extends Rank('8')

  def previous: Option[Rank] = super.previous(using Rank.values)
  def next: Option[Rank] = super.next(using Rank.values)

object Rank:
  def apply(representation: Char): Rank =
    representation match
      case '1' => Rank1
      case '2' => Rank2
      case '3' => Rank3
      case '4' => Rank4
      case '5' => Rank5
      case '6' => Rank6
      case '7' => Rank7
      case '8' => Rank8
      case _ =>
        throw new IllegalArgumentException("Rank must be between 1 and 8.")
