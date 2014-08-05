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
  "org.springframework.data" % "spring-data-jpa" % "1.6.2.RELEASE",
  "org.springframework" % "spring-context" % "3.2.2.RELEASE",
  "org.springframework" % "spring-test" % "3.2.2.RELEASE",
  "com.h2database" % "h2" % "1.4.180",
  "org.hibernate" % "hibernate-core" % "4.3.6.Final",
  "org.hibernate" % "hibernate-entitymanager" % "4.3.6.Final",
  "commons-dbcp" % "commons-dbcp" % "1.4"
)
