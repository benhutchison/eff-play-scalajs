

lazy val root = project.in(file(".")).
  aggregate(epJS, epJVM).
  settings(
    publish := {},
    publishLocal := {}
  )

lazy val ep = crossProject.in(file(".")).
  settings(
    name := "eff-cats-play",
    version := "0.1-SNAPSHOT",
    scalaVersion := "2.11.8",
    libraryDependencies += "org.atnos" %%% "eff-cats" % "1.4-20160430150555-d069eac",
	addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.7.1")
  ).
  jvmSettings(
    // Add JVM-specific settings here
  ).
  jsSettings(
    // Add JS-specific settings here
  )

lazy val epJVM = ep.jvm
lazy val epJS = ep.js
