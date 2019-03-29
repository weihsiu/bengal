val dottyVersion = "0.13.0-RC1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "bengal",
    version := "0.1.0",
    scalaVersion := dottyVersion,
    libraryDependencies ++= Seq(
      ("org.typelevel" %% "cats-core" % "1.6.0").withDottyCompat(scalaVersion.value)
    )
  )
