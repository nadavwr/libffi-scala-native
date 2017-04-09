lazy val commonSettings = Def.settings(
  scalaVersion := "2.11.8",
  organization := "com.github.nadavwr",
  version := "0.3.0-SNAPSHOT",
  publishArtifact in (Compile, packageDoc) := false
)

lazy val unpublished = Def.settings(
  publish := {},
  publishLocal := {},
  publishM2 := {}
)

lazy val `libffi-scala-native` = project
  .enablePlugins(ScalaNativePlugin)
  .settings(
    commonSettings,
    nativeSharedLibrary := true
  )

lazy val sample = project
  .enablePlugins(ScalaNativePlugin)
  .settings(
    commonSettings,
    unpublished
  )
  .dependsOn(`libffi-scala-native`)

lazy val `libffi-scala-native-root` = (project in file("."))
  .aggregate(`libffi-scala-native`, sample)
  .settings(
    commonSettings,
    unpublished,
    run := { (run in sample).evaluated }
  )

