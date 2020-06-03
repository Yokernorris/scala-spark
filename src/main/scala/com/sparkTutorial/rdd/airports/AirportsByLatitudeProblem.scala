package com.sparkTutorial.rdd.airports

import com.sparkTutorial.commons.Utils
import org.apache.spark.{SparkConf, SparkContext}

object AirportsByLatitudeProblem {

  def main(args: Array[String]) {

    val conf = new SparkConf().setAppName("airport latitude").setMaster("local[2]")
    val sc = new SparkContext(conf)

    val airports = sc.textFile("in/airports.text")

    val df = airports.filter(line => line.split(Utils.COMMA_DELIMITER)(6) > "40")

    val result = df.map(line => {
      val split = line.split(Utils.COMMA_DELIMITER)
      split(3) + "," + split(6)
    })

    result.saveAsTextFile("out/latitudes.text")
  }

}
