package com.ebastonblanco.chess.domain.common

enum File(val representation: String):
  case A extends File("a")
  case B extends File("b")
  case C extends File("c")
  case D extends File("d")
  case E extends File("e")
  case F extends File("f")
  case G extends File("g")
  case H extends File("h")

  def previous: Option[File] =
    val prevOrdinal = this.ordinal - 1
    if prevOrdinal >= 0 then Some(File.fromOrdinal(prevOrdinal))
    else None

  def next: Option[File] =
    val nextOrdinal = this.ordinal + 1
    if nextOrdinal < File.values.length then Some(File.fromOrdinal(nextOrdinal))
    else None

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
