name := "salimamukhit"
 
version := "1.0" 
      
lazy val `salimamukhit` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/"
      
scalaVersion := "2.13.5"

libraryDependencies ++= Seq( jdbc , ehcache , ws , specs2 % Test , guice, javaJpa,
  "org.hibernate" % "hibernate-core" % "5.4.30.Final", "org.postgresql" % "postgresql" % "42.3.1", "net.sourceforge.htmlunit" % "htmlunit" % "2.54.0" )

PlayKeys.externalizeResourcesExcludes += baseDirectory.value / "conf" / "META-INF" / "persistence.xml"
      