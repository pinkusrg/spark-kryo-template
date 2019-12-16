package com.knoldus.examples

import com.knoldus.models.{Address, Company, Employee}
import com.knoldus.spark.Job
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.storage.StorageLevel

import scala.collection.immutable

object KryoExample extends Job {
  override def run(sparkContext: SparkContext): Unit = {

    val employeeList: immutable.Seq[Employee] = (1 to 999999).map(value => {
      Employee("p" + value, value,
        Address("emp-lo" + value, "state" + value),
        value % 10,
        Company("comp_name",
          Address("comp_loca" + value, "state" + value)))
    })

    val rddEmployee: RDD[Employee] = sparkContext.parallelize(employeeList, 5)
    val evenAgeEmployee: RDD[Employee] = rddEmployee.filter(_.age % 2 == 0)

    evenAgeEmployee.persist(StorageLevel.MEMORY_ONLY_SER)

    evenAgeEmployee.count()

    Thread.sleep(200000)
  }

  /**
   * overriding the empty Set of classes which will be registered to Kryo used by the Job.
   * @return  Set of classes to be registered with kryo
   */
  override def registerKryoClasses: Set[Class[_]] = {
    Set(
      classOf[Employee], classOf[Array[Employee]],
      classOf[Address],classOf[Company],
      Class.forName("org.apache.spark.internal.io.FileCommitProtocol$TaskCommitMessage")
    )
  }
}
