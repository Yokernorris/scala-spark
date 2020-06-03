package com.sparkTutorial.pairRdd.sort
import org.apache.spark.{SparkContext, SparkConf}
import org.apache.log4j.{Level,Logger}

object SortedWordCountProblem {
  def main(args: Array[String]): Unit = {

    Logger.getLogger("org").setLevel(Level.ERROR)
    val conf = new SparkConf().setAppName("SortedWordCountProblem").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val words_count = sc.textFile("in/word_count.text")
      .flatMap(line => line.split(" "))
      .map(word => (word,1))
      .reduceByKey((x,y) => x+y)

    val ajuste = words_count.map(pair => (pair._2,pair._1)).sortByKey(false).map(pair => (pair._2,pair._1))

    for((key,value) <- ajuste.collect()) println(key + ":" + value)
  }

    /* Create a Spark program to read the an article from in/word_count.text,
       output the number of occurrence of each word in descending order.

       Sample output:

       apple : 200
       shoes : 193
       bag : 176
       ...
     */
}

