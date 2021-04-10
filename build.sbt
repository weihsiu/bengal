lazy val root = project
  .in(file("."))
  .settings(
    name := "bengal",
    version := "0.1.0",
    scalaVersion := "3.0.0-RC2",
    scalacOptions ++= Seq(
      "-Yindent-colons"
    ),
    libraryDependencies ++= Seq(
    )
  )
