package com.github.kaiba78987.kaibachess.domain.common

enum File(val representation: Char) extends Enum[File] with NavigableEnum[File]:
  case A extends File('a')
  case B extends File('b')
  case C extends File('c')
  case D extends File('d')
  case E extends File('e')
  case F extends File('f')
  case G extends File('g')
  case H extends File('h')

  def previous: Option[File] = super.previous(using File.values)
  def next: Option[File] = super.next(using File.values)

object File:
  def apply(representation: Char): File =
    representation match
      case 'a' => A
      case 'b' => B
      case 'c' => C
      case 'd' => D
      case 'e' => E
      case 'f' => F
      case 'g' => G
      case 'h' => H
      case _ =>
        throw new IllegalArgumentException("File must be between a and h.")
