package com.ebastonblanco.chess.domain.common

import munit.FunSuite

class RankSuite extends FunSuite {

  test("Rank.apply returns correct Rank for valid chars") {
    assertEquals(Rank('1'), Rank.Rank1)
    assertEquals(Rank('2'), Rank.Rank2)
    assertEquals(Rank('3'), Rank.Rank3)
    assertEquals(Rank('4'), Rank.Rank4)
    assertEquals(Rank('5'), Rank.Rank5)
    assertEquals(Rank('6'), Rank.Rank6)
    assertEquals(Rank('7'), Rank.Rank7)
    assertEquals(Rank('8'), Rank.Rank8)
  }

  test("Rank.apply throws for invalid chars") {
    intercept[IllegalArgumentException] { Rank('0') }
    intercept[IllegalArgumentException] { Rank('9') }
    intercept[IllegalArgumentException] { Rank('a') }
  }

  test("Rank.previous returns correct value") {
    assertEquals(Rank.Rank1.previous, None)
    assertEquals(Rank.Rank2.previous, Some(Rank.Rank1))
    assertEquals(Rank.Rank3.previous, Some(Rank.Rank2))
    assertEquals(Rank.Rank4.previous, Some(Rank.Rank3))
    assertEquals(Rank.Rank5.previous, Some(Rank.Rank4))
    assertEquals(Rank.Rank6.previous, Some(Rank.Rank5))
    assertEquals(Rank.Rank7.previous, Some(Rank.Rank6))
    assertEquals(Rank.Rank8.previous, Some(Rank.Rank7))
  }

  test("Rank.next returns correct value") {
    assertEquals(Rank.Rank1.next, Some(Rank.Rank2))
    assertEquals(Rank.Rank2.next, Some(Rank.Rank3))
    assertEquals(Rank.Rank3.next, Some(Rank.Rank4))
    assertEquals(Rank.Rank4.next, Some(Rank.Rank5))
    assertEquals(Rank.Rank5.next, Some(Rank.Rank6))
    assertEquals(Rank.Rank6.next, Some(Rank.Rank7))
    assertEquals(Rank.Rank7.next, Some(Rank.Rank8))
    assertEquals(Rank.Rank8.next, None)
  }

  test("Rank.representation returns correct char") {
    assertEquals(Rank.Rank1.representation, '1')
    assertEquals(Rank.Rank2.representation, '2')
    assertEquals(Rank.Rank3.representation, '3')
    assertEquals(Rank.Rank4.representation, '4')
    assertEquals(Rank.Rank5.representation, '5')
    assertEquals(Rank.Rank6.representation, '6')
    assertEquals(Rank.Rank7.representation, '7')
    assertEquals(Rank.Rank8.representation, '8')
  }
}