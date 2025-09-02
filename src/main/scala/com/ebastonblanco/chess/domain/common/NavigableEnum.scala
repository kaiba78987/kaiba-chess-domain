package   com.ebastonblanco.chess.domain.common

trait NavigableEnum[E <: Enum[E]]:

  self: E =>
  
  def previous(using values: Array[E]): Option[E] =
    val prevOrdinal = self.ordinal - 1
    if prevOrdinal >= 0 then Some(values(prevOrdinal)) else None

  def next(using values: Array[E]): Option[E] =
    val nextOrdinal = self.ordinal + 1
    if nextOrdinal < values.length then Some(values(nextOrdinal)) else None

