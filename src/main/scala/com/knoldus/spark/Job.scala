package com.knoldus.spark

import com.typesafe.config.{Config, ConfigFactory}
import org.apache.spark.{SparkConf, SparkContext}

trait Job {

  val config: Config = ConfigFactory.load()

  def main(args: Array[String]): Unit = {
    run(getSparkContext(getSparkConfig))
  }

  def getSparkContext(conf: SparkConf): SparkContext = new SparkContext(conf)

  def getSparkConfig: SparkConf = {
    new SparkConf()
      .setAppName(config.getString("spark.job.name"))
      .setMaster(config.getString("spark.master"))
      .set("spark.kryo.registrationRequired", config.getString("spark.kryoRegistrationRequired"))
      .registerKryoClasses(registerKryoClasses.toArray)
      .set("spark.serializer", config.getString("spark.serializer"))
  }

  /**
   * Set of classes which will be registered with kryo
   * @return  Set of Classes to be registered
   */
  def registerKryoClasses: Set[Class[_]] = Set()

  /**
   * Run the spark job with the given sparkContext
   * @param sparkContext  spark context
   */
  def run(sparkContext: SparkContext): Unit
}
