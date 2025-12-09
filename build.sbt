val scala3Version = "3.7.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "kaiba-chess-domain",
    version := "1.0.0",
    scalaVersion := scala3Version,
    licenses := Seq(
      "Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")
    ),
    libraryDependencies += "org.scalameta" %% "munit" % "1.0.0" % Test
  )
