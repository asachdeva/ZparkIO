val v = IO.readLines(new File("VERSION")).head
val projectName = IO.readLines(new File("PROJECT_NAME")).head

val sparkVersion = "2.3.1"

lazy val commonSettings = Seq(
  version      := v,
  organization := "com.leobenkel",
  scalaVersion := "2.11.12"
)

lazy val library = (project in file("Library"))
  .settings(
    commonSettings,
    name := projectName,
    libraryDependencies ++= Seq(
      // https://zio.dev/docs/getting_started.html
      "dev.zio" %% "zio" % "1.0.0-RC16",
      // https://github.com/scallop/scallop
      "org.rogach" %% "scallop" % "3.3.1",
      // https://mvnrepository.com/artifact/org.apache.spark/spark-core
      "org.apache.spark" %% "spark-core" % sparkVersion % Provided
    )
  )

lazy val testProject = (project in file("ProjectExample"))
  .settings(
    commonSettings,
    name := s"${projectName}_testProject"
  )
  .dependsOn(library)
