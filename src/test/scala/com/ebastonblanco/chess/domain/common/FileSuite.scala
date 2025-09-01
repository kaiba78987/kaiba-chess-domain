package com.ebastonblanco.chess.domain.common

import munit.FunSuite

class FileSuite extends FunSuite {

  test("File.apply returns correct File for valid chars") {
    assertEquals(File('a'), File.A)
    assertEquals(File('b'), File.B)
    assertEquals(File('c'), File.C)
    assertEquals(File('d'), File.D)
    assertEquals(File('e'), File.E)
    assertEquals(File('f'), File.F)
    assertEquals(File('g'), File.G)
    assertEquals(File('h'), File.H)
  }

  test("File.apply throws for invalid chars") {
    intercept[IllegalArgumentException] { File('i') }
    intercept[IllegalArgumentException] { File('z') }
    intercept[IllegalArgumentException] { File('1') }
  }

  test("File.previous returns correct value") {
    assertEquals(File.A.previous, None)
    assertEquals(File.B.previous, Some(File.A))
    assertEquals(File.C.previous, Some(File.B))
    assertEquals(File.D.previous, Some(File.C))
    assertEquals(File.E.previous, Some(File.D))
    assertEquals(File.F.previous, Some(File.E))
    assertEquals(File.G.previous, Some(File.F))
    assertEquals(File.H.previous, Some(File.G))
  }

  test("File.next returns correct value") {
    assertEquals(File.A.next, Some(File.B))
    assertEquals(File.B.next, Some(File.C))
    assertEquals(File.C.next, Some(File.D))
    assertEquals(File.D.next, Some(File.E))
    assertEquals(File.E.next, Some(File.F))
    assertEquals(File.F.next, Some(File.G))
    assertEquals(File.G.next, Some(File.H))
    assertEquals(File.H.next, None)
  }

  test("File.representation returns correct char") {
    assertEquals(File.A.representation, 'a')
    assertEquals(File.B.representation, 'b')
    assertEquals(File.C.representation, 'c')
    assertEquals(File.D.representation, 'd')
    assertEquals(File.E.representation, 'e')
    assertEquals(File.F.representation, 'f')
    assertEquals(File.G.representation, 'g')
    assertEquals(File.H.representation, 'h')
  }
}