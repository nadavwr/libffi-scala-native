lazy val commonSettings = Def.settings(
  scalaVersion := "2.11.8",
  organization := "com.github.nadavwr",
  version := "0.3.1",
  publishArtifact in (Compile, packageDoc) := false,
  licenses += ("MIT", url("http://opensource.org/licenses/MIT"))
)

lazy val unpublished = Def.settings(
  publish := {},
  publishLocal := {},
  publishM2 := {}
)

lazy val `libffi-scala-native` = project
  .enablePlugins(ScalaNativePlugin)
  .settings(commonSettings)

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
    run := { (run in sample).evaluated },
    publish := { (publish in `libffi-scala-native`).value }
  )

