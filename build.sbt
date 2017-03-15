name := "MentorActorApplication"

version := "1.0"

scalaVersion := "2.11.8"
  
libraryDependencies ++= {
	val akkaVersion = "2.4.9"
	Seq(
		"com.typesafe.akka" % "akka-actor_2.11" % akkaVersion,
		"com.typesafe.akka" %% "akka-http-core" % akkaVersion,
		"com.typesafe.akka" %% "akka-http-experimental" % akkaVersion,
		"com.typesafe.akka" %% "akka-http-spray-json-experimental" % akkaVersion,
		"io.spray"          %% "spray-json" % "1.3.1",
		"com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
		"com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test",
		"org.scalatest"      % "scalatest_2.11" % "3.0.1",
		"com.google.cloud"  % "google-cloud-datastore" % "0.9.4-beta"
	)
}

