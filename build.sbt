ThisBuild / organization := "com.thoughtworks.binding"

name := "LatestJQueryEvent"

enablePlugins(ScalaJSPlugin)

libraryDependencies += "be.doeraene" %%% "scalajs-jquery" % "0.9.4"

libraryDependencies += "com.thoughtworks.binding" %%% "binding" % "11.7.0"

scalacOptions ++= {
  import Ordering.Implicits._
  if (VersionNumber(scalaVersion.value).numbers < Seq(2L, 12L)) {
    // Enable SAM types for Scala 2.11
    Some("-Xexperimental")
  } else {
    None
  }
}
