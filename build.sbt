lazy val commonSettings = Def.settings(
  scalaVersion := "2.11.8",
  organization := "com.github.nadavwr",
  version := "0.2.0"
)

lazy val unpublished = Def.settings(
  publish := {},
  publishLocal := {},
  publishM2 := {}
)

lazy val ffi = project
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
  .dependsOn(ffi)

lazy val ffiRoot = (project in file("."))
  .aggregate(ffi, sample)
  .settings(
    commonSettings,
    unpublished,
    run := { (run in sample).evaluated }
  )
