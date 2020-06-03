package com.sparkTutorial.pairRdd.mapValues

import com.sparkTutorial.commons.Utils
import org.apache.spark.{SparkConf, SparkContext}

import org.apache.log4j.Level
import org.apache.log4j.Logger

object AirportsUppercaseProblem {

  def main(args: Array[String]) {

    Logger.getLogger("org").setLevel(Level.ERROR)

    val conf = new SparkConf().setAppName("AirportsNotInUsaProblem").setMaster("local[*]")
    val sc = new SparkContext(conf)

    //en un pair rdd la salida del map debe ser => (x,z)
    val df_pair = sc.textFile("in/airports.text").map(line => (line.split(Utils.COMMA_DELIMITER)(1),
      line.split(Utils.COMMA_DELIMITER)(3)))

    val upperPair = df_pair.mapValues(value => value.toUpperCase)

    // estructura <- rango
    for(upperPair <- upperPair){
      println(upperPair)
    }

    /* Create a Spark program to read the airport data from in/airports.text, generate a pair RDD with airport name
       being the key and country name being the value. Then convert the country name to uppercase and
       output the pair RDD to out/airports_uppercase.text

       Each row of the input file contains the following columns:

       Airport ID, Name of airport, Main city served by airport, Country where airport is located, IATA/FAA code,
       ICAO Code, Latitude, Longitude, Altitude, Timezone, DST, Timezone in Olson format

       Sample output:

       ("Kamloops", "CANADA")
       ("Wewak Intl", "PAPUA NEW GUINEA")
       ...
     */
  }
}
