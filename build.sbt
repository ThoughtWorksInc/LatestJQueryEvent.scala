ThisBuild / organization := "com.thoughtworks.binding"

name := "LatestJQueryEvent"

enablePlugins(ScalaJSPlugin)

libraryDependencies += "be.doeraene" %%% "scalajs-jquery" % "0.9.4"

libraryDependencies += "com.thoughtworks.binding" %%% "binding" % "11.7.0"
