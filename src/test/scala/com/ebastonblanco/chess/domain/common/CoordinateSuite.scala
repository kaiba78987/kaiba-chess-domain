package com.ebastonblanco.chess.domain.common

import munit.FunSuite

class CoordinateSuite extends FunSuite:

  test("Coordinate.apply creates correct Coordinate for valid string") {
    val coord = Coordinate("e4")
    assertEquals(coord.file, File.E)
    assertEquals(coord.rank, Rank.Rank4)
  }

  test("Coordinate.apply throws for invalid string length") {
    intercept[IllegalArgumentException] { Coordinate("e") }
    intercept[IllegalArgumentException] { Coordinate("e44") }
    intercept[IllegalArgumentException] { Coordinate("") }
  }

  test("Coordinate.apply throws for invalid file or rank") {
    intercept[IllegalArgumentException] { Coordinate("i4") }
    intercept[IllegalArgumentException] { Coordinate("e9") }
    intercept[IllegalArgumentException] { Coordinate("z0") }
  }

  test("toString returns correct representation") {
    assertEquals(Coordinate(File.A, Rank.Rank1).toString, "a1")
    assertEquals(Coordinate(File.H, Rank.Rank8).toString, "h8")
    assertEquals(Coordinate(File.D, Rank.Rank5).toString, "d5")
  }

  test("north returns correct coordinate or None") {
    assertEquals(Coordinate(File.E, Rank.Rank4).north, Some(Coordinate(File.E, Rank.Rank5)))
    assertEquals(Coordinate(File.E, Rank.Rank8).north, None)
  }

  test("south returns correct coordinate or None") {
    assertEquals(Coordinate(File.E, Rank.Rank4).south, Some(Coordinate(File.E, Rank.Rank3)))
    assertEquals(Coordinate(File.E, Rank.Rank1).south, None)
  }

  test("east returns correct coordinate or None") {
    assertEquals(Coordinate(File.E, Rank.Rank4).east, Some(Coordinate(File.F, Rank.Rank4)))
    assertEquals(Coordinate(File.H, Rank.Rank4).east, None)
  }

  test("west returns correct coordinate or None") {
    assertEquals(Coordinate(File.E, Rank.Rank4).west, Some(Coordinate(File.D, Rank.Rank4)))
    assertEquals(Coordinate(File.A, Rank.Rank4).west, None)
  }

  test("northEast returns correct coordinate or None") {
    assertEquals(Coordinate(File.E, Rank.Rank4).northEast, Some(Coordinate(File.F, Rank.Rank5)))
    assertEquals(Coordinate(File.H, Rank.Rank8).northEast, None)
    assertEquals(Coordinate(File.H, Rank.Rank4).northEast, None)
    assertEquals(Coordinate(File.E, Rank.Rank8).northEast, None)
  }

  test("northWest returns correct coordinate or None") {
    assertEquals(Coordinate(File.E, Rank.Rank4).northWest, Some(Coordinate(File.D, Rank.Rank5)))
    assertEquals(Coordinate(File.A, Rank.Rank8).northWest, None)
    assertEquals(Coordinate(File.A, Rank.Rank4).northWest, None)
    assertEquals(Coordinate(File.E, Rank.Rank8).northWest, None)
  }

  test("southEast returns correct coordinate or None") {
    assertEquals(Coordinate(File.E, Rank.Rank4).southEast, Some(Coordinate(File.F, Rank.Rank3)))
    assertEquals(Coordinate(File.H, Rank.Rank1).southEast, None)
    assertEquals(Coordinate(File.H, Rank.Rank4).southEast, None)
    assertEquals(Coordinate(File.E, Rank.Rank1).southEast, None)
  }

  test("southWest returns correct coordinate or None") {
    assertEquals(Coordinate(File.E, Rank.Rank4).southWest, Some(Coordinate(File.D, Rank.Rank3)))
    assertEquals(Coordinate(File.A, Rank.Rank1).southWest, None)
    assertEquals(Coordinate(File.A, Rank.Rank4).southWest, None)
    assertEquals(Coordinate(File.E, Rank.Rank1).southWest, None)
  }

