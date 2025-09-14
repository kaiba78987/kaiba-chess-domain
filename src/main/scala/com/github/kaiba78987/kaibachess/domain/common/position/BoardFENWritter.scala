package com.github.kaiba78987.kaibachess.domain.common.position

import com.github.kaiba78987.kaibachess.domain.common.Rank
import com.github.kaiba78987.kaibachess.domain.common.File
import com.github.kaiba78987.kaibachess.domain.common.Coordinate
import com.github.kaiba78987.kaibachess.domain.common.pieces.Piece

object BoardFENWritter extends FenWritter[Board]:

  private def rankToFen(positionComponent: Board, rank: Rank): String =
    var blankCount = 0

    val rowFen = File.values.map { file =>
      positionComponent(Coordinate(file, rank)) match

        case Some(piece) =>
          val fenPart = pieceFoundFEN(piece, blankCount)
          blankCount = 0
          fenPart

        case None =>
          blankCount += 1
          ""
    }.mkString

    if blankCount > 0 then rowFen + blankCount.toString else rowFen

  private def pieceFoundFEN(piece: Piece, blankCount: Int): String =
    if blankCount > 0 then blankCount.toString + Piece.toFen(piece)
    else Piece.toFen(piece)

  def toFen(positionComponent: Board): String =
    Rank.values.reverse
      .map { rank =>
        rankToFen(positionComponent, rank)
      }
      .mkString("/")
