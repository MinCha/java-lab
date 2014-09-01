name := """play2-scala"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.2"

resolvers += "spray" at "http://repo.spray.io/"

libraryDependencies ++= Seq(
  "org.springframework.data" % "spring-data-jpa" % "1.5.1.RELEASE",
  "org.springframework" % "spring-context" % "4.0.6.RELEASE",
  "org.springframework" % "spring-test" % "4.0.6.RELEASE",
  "org.springframework" % "spring-aop" % "4.0.6.RELEASE",
  "com.h2database" % "h2" % "1.4.181",
  "net.liftweb" % "lift-json_2.11" % "2.6-M4",
  "org.hibernate" % "hibernate-core" % "4.3.4.Final",
  "org.hibernate" % "hibernate-entitymanager" % "4.3.4.Final",
  "commons-dbcp" % "commons-dbcp" % "1.4",
  "mysql" % "mysql-connector-java" % "5.1.25",
  "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test"
)