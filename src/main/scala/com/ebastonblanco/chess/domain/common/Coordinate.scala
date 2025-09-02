package com.ebastonblanco.chess.domain.common

final case class Coordinate(file: File, rank: Rank):

  override def toString = s"${file.representation}${rank.representation}"

  def north: Option[Coordinate] =
    rank.next match
      case Some(r) => Some(new Coordinate(file, r))
      case None    => None

  def south: Option[Coordinate] =
    rank.previous match
      case Some(r) => Some(new Coordinate(file, r))
      case None    => None

  def east: Option[Coordinate] =
    file.next match
      case Some(f) => Some(new Coordinate(f, rank))
      case None    => None

  def west: Option[Coordinate] =
    file.previous match
      case Some(f) => Some(new Coordinate(f, rank))
      case None    => None

  def northEast: Option[Coordinate] =
    (file.next, rank.next) match
      case (Some(f), Some(r)) => Some(new Coordinate(f, r))
      case _                  => None

  def northWest: Option[Coordinate] =
    (file.previous, rank.next) match
      case (Some(f), Some(r)) => Some(new Coordinate(f, r))
      case _                  => None

  def southEast: Option[Coordinate] =
    (file.next, rank.previous) match
      case (Some(f), Some(r)) => Some(new Coordinate(f, r))
      case _                  => None

  def southWest: Option[Coordinate] =
    (file.previous, rank.previous) match
      case (Some(f), Some(r)) => Some(new Coordinate(f, r))
      case _                  => None

object Coordinate:

  enum Direction:
    case North, South, East, West, NorthEast, NorthWest, SouthEast, SouthWest

  def navigate(coordinate: Coordinate, direction: Direction): Option[Coordinate] =
    direction match
      case Direction.North     => coordinate.north
      case Direction.South     => coordinate.south
      case Direction.East      => coordinate.east
      case Direction.West      => coordinate.west
      case Direction.NorthEast => coordinate.northEast
      case Direction.NorthWest => coordinate.northWest
      case Direction.SouthEast => coordinate.southEast
      case Direction.SouthWest => coordinate.southWest

  def apply(representation: String): Coordinate =
    if representation.length != 2 then
      throw new IllegalArgumentException("Coordinate length must be 2.")
    else Coordinate(File(representation(0)), Rank(representation(1)))
