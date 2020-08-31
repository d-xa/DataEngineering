package spark

//import ai.h2o.sparkling._
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
//import org.apache.spark.h2o._
//import ai.h2o.sparkling.H2OContext

object SparklingWaterApp {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder
      .appName("Simple Sparkling")
      .config("spark.master", "local")
      //.config("spark.locality.wait",0)
      //.config("spark.sql.autoBroadcastJoinThreshold", -1)
      .getOrCreate()

    spark.conf.set("spark.locality.wait",0)
    spark.conf.set("spark.sql.autoBroadcastJoinThreshold", -1)

    val sc = spark.sparkContext

    def createRDD(): RDD[(Int,String)] = {
      val rdd = sc.parallelize(Seq((1, "Spark"), (2, "Scala"), (3, "Big Data")))
      return rdd
    }

    val aRDD = createRDD()

    import spark.implicits._
    val df = aRDD.toDF("Id", "Name")

    df.show()

    import org.apache.spark.h2o._
    val conf = new H2OConf().setInternalClusterMode().setReplDisabled()
    val h2oContext = H2OContext.getOrCreate(conf)

  //val h2oContext = H2OContext.getOrCreate(sc)

  val h2oFrame = h2oContext.asH2OFrame(df)

 // val h2oFrame = new H2OFrame("iris.hex")
  println(h2oFrame.names().mkString(","))
  println(h2oFrame.numCols())

  df.show()
    import h2oContext._
    import spark.implicits._

/*    val df = Seq(
      (1, "2014/07/31 23:00:01"),
      (1, "2016/12/09 10:12:43")).toDF("id", "date")*/

    //val h2oTrainFrame = h2oContext.asH2OFrame(df)

    //println(s"h2oContext = ${h2oContext.toString()}")

  }

}
