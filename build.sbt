

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
    libraryDependencies += "org.atnos" %%% "eff-cats" % "1.5.1-20160508090222-541f0a6",
	  addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.7.1"),
    addCompilerPlugin("com.milessabin" % "si2712fix-plugin" % "1.1.0" cross CrossVersion.full)
  ).
  jvmSettings(
    // Add JVM-specific settings here
  ).
  jsSettings(
    // Add JS-specific settings here
  )

lazy val epJVM = ep.jvm
lazy val epJS = ep.js
