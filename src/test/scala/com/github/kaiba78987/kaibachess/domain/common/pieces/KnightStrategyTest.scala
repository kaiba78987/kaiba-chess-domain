package com.github.kaiba78987.kaibachess.domain.common.pieces

import munit.FunSuite
import com.github.kaiba78987.kaibachess.domain.common._
import com.github.kaiba78987.kaibachess.domain.common.position._

class KnightStrategyTest extends FunSuite with StrategyTestHelpers {

  test("Knight moves") {
    val knight = Piece(PieceType.Knight, Color.White)
    val start = Coordinate(File.E, Rank.Rank4)
    val board = Board(Map(start -> knight))
    val position = createPosition(board)

    val moves = knight.potentialMoves(start, position)
    assert(moves.contains(Coordinate(File.F, Rank.Rank6)))
    assert(moves.contains(Coordinate(File.D, Rank.Rank6)))
    assert(moves.contains(Coordinate(File.F, Rank.Rank2)))
    assert(moves.contains(Coordinate(File.D, Rank.Rank2)))
    assert(moves.contains(Coordinate(File.G, Rank.Rank5)))
    assert(moves.contains(Coordinate(File.C, Rank.Rank5)))
    assert(moves.contains(Coordinate(File.G, Rank.Rank3)))
    assert(moves.contains(Coordinate(File.C, Rank.Rank3)))
  }
}
