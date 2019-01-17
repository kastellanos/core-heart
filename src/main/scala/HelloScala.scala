import java.time.Duration
import java.util
import java.util.Properties
import java.util.Collections

import scala.collection.JavaConverters._
import scala.collection.JavaConverters.asJavaCollection
import org.apache.kafka.clients.consumer.{ConsumerRecords, KafkaConsumer}
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.sql.types.{StructType, StructField, StringType, IntegerType, FloatType};
import org.apache.spark.sql._

object HelloScala {

  def main(args:  Array[String]): Unit = {
    val properties = new Propexrties()
    properties.put("bootstrap.servers","kafka-service:9092")
    properties.put("group.id","scala-spark")
    properties.put("key.deserializer", classOf[StringDeserializer])
    properties.put("value.deserializer", classOf[StringDeserializer])
	val kafkaConsumer = new KafkaConsumer[String, String](properties)
  	//args.filter(_.remove("__consumer_offsets")
  	//var temp :Array[_] = _
  	//temp= args.filter( name => name.contains(__consumer_offsets)
	val ls : util.Collection[String] = util.Arrays.asList("Austin","Boston","Chicago","LasVegas","LosAngeles","Miami","NewOrleans","NewYork","Orlando","SaltLake","SanDiego","Washington")
	kafkaConsumer.subscribe(ls)
	println("Here")
  val sqlSchema= List(StructField("id",IntegerType,true),StructField("cityName",StringType,true),StructField("x",IntegerType,true),StructField("y",IntegerType,true),StructField("value",FloatType,true),StructField("element",StringType,true),StructField("ref_year",IntegerType,true),StructField("ref_month",IntegerType,true),StructField("ref_day",IntegerType,true))
  val cnt = 1
	while (true) {
	      val results = kafkaConsumer.poll( Duration.ofSeconds(100)).asScala
	      for (record <- results.iterator ) {
	        println(s"Here's your $record")
          val row = $record.value.split(",")
          val clients = Seq( Row(cnt, row(0),row(3),row(4),row(5),row(8),row(9),row(10),row(11)))
          cnt = cnt + 1
          val dataDF = spark.createDataFrame( spark.sparkContext.parallelize(client), StructType(sqlSchema))
          dataDF.write.format("org.apache.spark.sql.cassandra").mode(SaveMode.Append).option("spark.cassandra.connection.host","cassandra").option("spark.cassandra.connection.port","9042").option("keyspace","sdtd").option("table","elements").save()

	      }
	      println("Here")
	}



  }
}
