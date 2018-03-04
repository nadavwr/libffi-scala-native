scalaVersion := "2.11.12"
organization := "com.github.nadavwr"
publishArtifact in (Compile, packageDoc) := false
licenses += ("MIT", url("http://opensource.org/licenses/MIT"))
nativeLinkStubs := true

enablePlugins(ScalaNativePlugin)
libraryDependencies += "com.lihaoyi" %%% "utest" % "0.6.3" % Test
testFrameworks += new TestFramework("utest.runner.Framework")
name := "libffi-scala-native"


