package com.ebastonblanco.chess.domain.common.position

import com.ebastonblanco.chess.domain.common.Rank
import com.ebastonblanco.chess.domain.common.File
import com.ebastonblanco.chess.domain.common.Coordinate
import com.ebastonblanco.chess.domain.common.pieces.Piece

trait BoardFenManager extends FENReader[Board] with FenWritter[Board]:

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
