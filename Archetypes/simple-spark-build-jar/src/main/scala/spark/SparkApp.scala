package spark


import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object SparkApp {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder
      .appName("Simple Application")
      .config("spark.master", "local")
      .getOrCreate()

    spark.sparkContext.setLogLevel("ERROR")

    val sc = spark.sparkContext
    val rdd = sc.parallelize(Seq((1, "Spark"), (2, "Scala"), (3, "Big Data")))

    import spark.implicits._
    val df = rdd.toDF("Id", "Name")

    df.show()

    spark.stop()
  }
}