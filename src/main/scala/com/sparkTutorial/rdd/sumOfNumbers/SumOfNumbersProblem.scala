package com.sparkTutorial.rdd.sumOfNumbers

import org.apache.spark.{SparkContext, SparkConf}

object SumOfNumbersProblem {

  def main(args: Array[String]) {

    val conf = new SparkConf().setAppName("SumOfNumbersProblem").setMaster("local[2]")
    val sc = new SparkContext(conf)

    val df = sc.textFile("in/prime_nums.text").flatMap(line => line.split("\\s+")).filter(number => !number.isEmpty).map(number => number.toInt)
    val result = df.reduce((x,y) => x + y)

    println("La suma es " + result)
    /* Create a Spark program to read the first 100 prime numbers from in/prime_nums.text,
       print the sum of those numbers to console.

       Each row of the input file contains 10 prime numbers separated by spaces.
     */
  }
}
