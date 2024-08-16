import Dependencies._

ThisBuild / scalaVersion     := "2.13.12"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "GuessWho"
  ).enablePlugins()

libraryDependencies ++= Seq(munit % Test,
"org.scalatest" %% "scalatest" % "3.2.19" % Test
)