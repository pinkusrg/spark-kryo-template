import sbt._
object Dependencies{
  lazy val sparkSqlVersion = "2.4.4"
  lazy val slf4jLoggingVersion: String = "2.0.0-alpha0"
  lazy val typeSafeVersion = "1.3.2"

  lazy val sparkSqlDependency: ModuleID = "org.apache.spark" %% "spark-sql" % sparkSqlVersion
  lazy val typeSafeDependency = "com.typesafe" % "config" % typeSafeVersion
  lazy val slf4jSimpleLoggingDependency: ModuleID = "org.slf4j" % "slf4j-simple" % slf4jLoggingVersion

  lazy val exampleDependencies = List(sparkSqlDependency,slf4jSimpleLoggingDependency,typeSafeDependency)
}
