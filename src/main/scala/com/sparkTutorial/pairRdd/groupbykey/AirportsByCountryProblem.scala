package com.sparkTutorial.pairRdd.groupbykey

import com.sparkTutorial.commons.Utils
import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}


object AirportsByCountryProblem {

  def main(args: Array[String]) {

    Logger.getLogger("org").setLevel(Level.ERROR)
    val conf = new SparkConf().setAppName("AirportsByCountryProblem").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val df_pair = sc.textFile("in/airports.text").map(line => (line.split(Utils.COMMA_DELIMITER)(3),
      line.split(Utils.COMMA_DELIMITER)(1))).groupByKey()

    //df_pair.saveAsTextFile("out/AirportsByCountryProblem.text")

    for((key,value) <- df_pair.collectAsMap()) println(key + ":" + value)


    /* Create a Spark program to read the airport data from in/airports.text,
       output the the list of the names of the airports located in each country.

       Each row of the input file contains the following columns:
       Airport ID, Name of airport, Main city served by airport, Country where airport is located, IATA/FAA code,
       ICAO Code, Latitude, Longitude, Altitude, Timezone, DST, Timezone in Olson format

       Sample output:

       "Canada", List("Bagotville", "Montreal", "Coronation", ...)
       "Norway" : List("Vigra", "Andenes", "Alta", "Bomoen", "Bronnoy",..)
       "Papua New Guinea",  List("Goroka", "Madang", ...)
       ...
     */
  }
}
