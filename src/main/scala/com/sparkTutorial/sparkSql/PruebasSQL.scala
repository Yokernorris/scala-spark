package com.sparkTutorial.sparkSql

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

object PruebasSQL {
  def main(args: Array[String]): Unit = {

    Logger.getLogger("org").setLevel(Level.ERROR)

    val session = SparkSession.builder().appName("HousePriceProblem").master("local[1]").getOrCreate()

    val reader = session.read
    val response = reader
      .option("header", true)
      .option("inferSchema", value = true)
      .csv("in/RealEstate.csv")

    response.createOrReplaceTempView("tabla")
    val df = session.sql("select * from tabla limit 10")
    df.show()



  }

}
