package com.github.kaiba78987.kaibachess.domain.common.position

import munit.FunSuite

class CastlingAvailabilitySuite extends FunSuite:

  test("empty CastlingAvailability has no castlings and FEN is '-'") {
    val ca = CastlingAvailability.empty
    assertEquals(CastlingAvailability.toFen(ca), "-")
    assert(!ca.contains(Castling.WhiteKingside))
    assert(!ca.contains(Castling.WhiteQueenside))
    assert(!ca.contains(Castling.BlackKingside))
    assert(!ca.contains(Castling.BlackQueenside))
  }

  test("all CastlingAvailability has all castlings and correct FEN") {
    val ca = CastlingAvailability.all
    assert(ca.contains(Castling.WhiteKingside))
    assert(ca.contains(Castling.WhiteQueenside))
    assert(ca.contains(Castling.BlackKingside))
    assert(ca.contains(Castling.BlackQueenside))
    assertEquals(CastlingAvailability.toFen(ca), "KQkq")
  }

  test("add and remove castling") {
    val ca = CastlingAvailability.empty + Castling.WhiteKingside
    assert(ca.contains(Castling.WhiteKingside))
    assertEquals(CastlingAvailability.toFen(ca), "K")

    val ca2 = ca + Castling.BlackKingside
    assert(ca2.contains(Castling.WhiteKingside))
    assert(ca2.contains(Castling.BlackKingside))
    assertEquals(CastlingAvailability.toFen(ca2), "Kk")

    val ca3 = ca2 - Castling.WhiteKingside
    assert(!ca3.contains(Castling.WhiteKingside))
    assert(ca3.contains(Castling.BlackKingside))
    assertEquals(CastlingAvailability.toFen(ca3), "k")
  }

  test("apply from Set[Castling]") {
    val ca = CastlingAvailability(Set(Castling.WhiteKingside, Castling.BlackQueenside))
    assert(ca.contains(Castling.WhiteKingside))
    assert(ca.contains(Castling.BlackQueenside))
    assert(!ca.contains(Castling.WhiteQueenside))
    assert(!ca.contains(Castling.BlackKingside))
    assertEquals(CastlingAvailability.toFen(ca), "Kq")
  }

  test("fromFen parses FEN correctly") {
    val ca1 = CastlingAvailability.fromFen("KQkq")
    assert(ca1.contains(Castling.WhiteKingside))
    assert(ca1.contains(Castling.WhiteQueenside))
    assert(ca1.contains(Castling.BlackKingside))
    assert(ca1.contains(Castling.BlackQueenside))

    val ca2 = CastlingAvailability.fromFen("Kq")
    assert(ca2.contains(Castling.WhiteKingside))
    assert(ca2.contains(Castling.BlackQueenside))
    assert(!ca2.contains(Castling.WhiteQueenside))
    assert(!ca2.contains(Castling.BlackKingside))

    val ca3 = CastlingAvailability.fromFen("-")
    assertEquals(CastlingAvailability.toFen(ca3), "-")
    assert(!ca3.contains(Castling.WhiteKingside))
    assert(!ca3.contains(Castling.WhiteQueenside))
    assert(!ca3.contains(Castling.BlackKingside))
    assert(!ca3.contains(Castling.BlackQueenside))
  }
