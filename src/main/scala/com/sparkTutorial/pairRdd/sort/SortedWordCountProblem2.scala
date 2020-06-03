package com.sparkTutorial.pairRdd.sort

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

object SortedWordCountProblem2 {

  def main(args: Array[String]): Unit = {

    Logger.getLogger("org").setLevel(Level.ERROR)
    val conf = new SparkConf().setAppName("SortedWordCountProblem").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val words_count = sc.textFile("in/word_count.text")
      .flatMap(line => line.split(" "))
      .map(word => (word,1))
      .reduceByKey((x,y) => x+y)

    // seleccionamos que ordene por el elemento value
    val words_order = words_count.sortBy(pair => pair._2, false)

    for((key,value) <- words_order.collect()) println(key + ":" + value)
  }

}
