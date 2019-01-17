name := "scala-spark"

version := "0.1"

scalaVersion := "2.12.8"

organization := "kastellanos"

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.1.0"
libraryDependencies += "org.apache.spark" % "spark-streaming_2.11" % "2.1.0"
libraryDependencies += "org.apache.kafka" %% "kafka" % "2.1.0"
libraryDependencies += "com.datastax.cassandra" % "cassandra-driver-core" % "2.0.1"

enablePlugins(DockerPlugin)

dockerAutoPackageJavaApplication()
