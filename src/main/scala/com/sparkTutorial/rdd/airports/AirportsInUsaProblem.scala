package com.sparkTutorial.rdd.airports

import com.sparkTutorial.commons.Utils
import org.apache.spark.{SparkConf, SparkContext}

object AirportsInUsaProblem {
  def main(args: Array[String]) {

    val conf = new SparkConf().setAppName("AirportsInUsaProblem").setMaster("local[*]") // local 1 nucleo, locan[2] 2 nucleos, local[*] todos los nucleos
    val sc = new SparkContext(conf)

    val airports = sc.textFile("in/airports.text")

    //Utils.COMMA_DELIMITER tiene una funcion con una expresión regular ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)" para no coger las comas de dentro de las comillas
    val air = airports.filter(line => line.split(Utils.COMMA_DELIMITER)(3) == "\"United States\"" )
    val result = air.map(line =>{
      val splits =  line.split(Utils.COMMA_DELIMITER)
      splits(1) + "," + splits(2)
    })


    result.saveAsTextFile("out/airports_in_usa.text")

  }
}
