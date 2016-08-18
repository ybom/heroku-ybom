name := "heroku-ybom"

version := "1.0"

scalaVersion := "2.11.8"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

resolvers += Resolver.sonatypeRepo("public")

libraryDependencies ++= Seq(
  "com.typesafe.play" % "play_2.11" % "2.5.4",
  "com.typesafe.play" % "play-json_2.11" % "2.5.4"
)

