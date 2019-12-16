# Spark-Kryo template

A basic template of using Kryo serialization in spark jobs with an example provided which will help you to compare both the serialization libraries provided by the spark.
## Table of contents  
1. [Getting Started](#Getting-Started)  
2. [Configuring](#Configuring-Kryo-Serialization)
3. [Running](#Running)  
5. [Example](#Example-and-Results)  
  
## Getting Started  
#### Minimum requirements  
To run this example you will need  **Java 1.8+, Scala 2.12.7, SBT 1.2.8**.   
  
## Configuring

To configure the spark jobs to run with Kryo serialization, you need to set the property in spark configuration as : 
```scala
`.set("spark.serializer","org.apache.spark.serializer.KryoSerializer")`
```
And then you need to pass this another property for optimization followed by registration of classes :
```scala
.set("spark.kryo.registrationRequired","true")
.registerKryoClasses(
    Array(classOf[Person],classOf[Furniture])
)
```

In the example used in the template, you can configure the serializer in `application.conf`
## Running 
There is an example spark job as `KryoExample` in `examples` package which you just need to compile and run.
You can create your own and compare the results by extending the `Job` trait and overriding the `run` method and writing your job logic there.

## Example and Results

There is a simple spark job which creates a list of 999999 Employees which is filtered with an even age and then persisted on the memory. If we run this job one with Kryo and another with Java serializtion, we get the following results :

### Storage sections :
Kryo Serialization :

![Storage Section of Job run with Kryo Serialization](https://springflee.files.wordpress.com/2019/12/kryostorage.png)

Java Serialization :

![Storage Section of Job run with Java Serialization](https://springflee.files.wordpress.com/2019/12/javastorage.png)

### Task Time :
Kro Serialization :

![Kryo Task Time](https://springflee.files.wordpress.com/2019/12/kryotasktime.png)

Java Serialization :

![Java Task Time](https://springflee.files.wordpress.com/2019/12/javatasktime.png)

### Additional Metrics :

Kryo Serialization :

![Kryo Metrics](https://springflee.files.wordpress.com/2019/12/kryometrics.png)

Java Serialization :

![Java Metrics](https://springflee.files.wordpress.com/2019/12/javametrics.png)
So, if we look at the stats we can see that Kryo outperforms every time, you can run more jobs and analyze the result furthur.