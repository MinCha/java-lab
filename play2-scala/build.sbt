name := """play2-scala"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

resolvers += "spray" at "http://repo.spray.io/"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws,
  "io.spray" %  "spray-json" % "1.2.6"
)

allDependencies ++= Seq(
  "com.google.code.gson" % "gson" % "2.2.4"
)
