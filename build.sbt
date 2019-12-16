import Dependencies._

lazy val commonSettings = Seq(
  organization := "com.knoldus",
  scalaVersion := "2.12.7",
  version :="0.1"
)

lazy val root = project
  .in(file("."))
  .settings(
    commonSettings,
    name :="kryo-template",
    libraryDependencies ++= exampleDependencies
  )
