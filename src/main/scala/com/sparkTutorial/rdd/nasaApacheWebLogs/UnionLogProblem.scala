package com.sparkTutorial.rdd.nasaApacheWebLogs

import org.apache.spark.{SparkConf, SparkContext}

object UnionLogProblem {

  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("Union program").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val df1 = sc.textFile("in/nasa_19950701.tsv")
    val df2 = sc.textFile("in/nasa_19950801.tsv")

    val union = sc.union(df1,df2)
    val result = union.filter(line => isNotHeader(line)).sample(false,0.1)

    result.saveAsTextFile("out/sample_nasa_logs.tsv\"")
  }

  // esta funcion implementa el filtro para eliminar la cabecera, con .startWish seleccionamos el primer elemento del string y con contains buscamos una palabra
  def isNotHeader(line: String): Boolean = !(line.startsWith("host") && line.contains("bytes"))
}
