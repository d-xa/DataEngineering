package spark


import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object SparkApp {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder
      .appName("Simple Application")
      .config("spark.master", "local")
      .getOrCreate()

    val sc = spark.sparkContext

    def createRDD(): RDD[(Int,String)] = {
      val rdd = sc.parallelize(Seq((1, "Spark"), (2, "Scala"), (3, "Big Data")))
      return rdd
    }

    val aRDD = createRDD()

    import spark.implicits._
    val df = aRDD.toDF("Id", "Name")

    df.show()

    spark.stop()
  }
}