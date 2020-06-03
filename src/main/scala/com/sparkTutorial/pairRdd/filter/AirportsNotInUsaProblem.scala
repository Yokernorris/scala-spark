package com.sparkTutorial.pairRdd.filter

import com.sparkTutorial.commons.Utils
import org.apache.spark.{SparkConf, SparkContext}

object AirportsNotInUsaProblem {

  def main(args: Array[String]) {

    val conf = new SparkConf().setAppName("AirportsNotInUsaProblem").setMaster("local[*]")
    val sc = new SparkContext(conf)

    //en un pair rdd la salida del map debe ser => (x,z)
    val df_pair = sc.textFile("in/airports.text").map(line => (line.split(Utils.COMMA_DELIMITER)(1),
      line.split(Utils.COMMA_DELIMITER)(3)))
    val df_filter = df_pair.filter(dupla => dupla._2 != "\"United States\"")

    df_filter.saveAsTextFile("out/airports_not_in_usa_pair_rdd.text")



    /* Create a Spark program to read the airport data from in/airports.text;
       generate a pair RDD with airport name being the key and country name being the value.
       Then remove all the airports which are located in United States and output the pair RDD to out/airports_not_in_usa_pair_rdd.text

       Each row of the input file contains the following columns:
       Airport ID, Name of airport, Main city served by airport, Country where airport is located,
       IATA/FAA code, ICAO Code, Latitude, Longitude, Altitude, Timezone, DST, Timezone in Olson format

       Sample output:

       ("Kamloops", "Canada")
       ("Wewak Intl", "Papua New Guinea")
       ...
     */
  }
}
