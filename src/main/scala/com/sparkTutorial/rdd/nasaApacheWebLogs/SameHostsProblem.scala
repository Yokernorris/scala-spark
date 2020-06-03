package com.sparkTutorial.rdd.nasaApacheWebLogs

import com.sparkTutorial.commons.Utils
import org.apache.spark.{SparkConf, SparkContext}

object SameHostsProblem {

  def main(args: Array[String]) {

    val conf = new SparkConf().setAppName("SameHostsProblem").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val df1 = sc.textFile("in/nasa_19950701.tsv").map(line => line.split("\t")(0))
    val df2 = sc.textFile("in/nasa_19950801.tsv").map(line => line.split("\t")(0))

    val intersection =df1.intersection(df2).filter(line => line != "host")

    intersection.saveAsTextFile("out/nasa_logs_same_hosts.csv")

  }

}
