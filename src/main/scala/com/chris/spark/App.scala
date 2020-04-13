package com.chris.spark

import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Hello world!
 *
 */
object App {
  def main(args: Array[String]): Unit = {
    var conf = new SparkConf().setMaster("local[1]").setAppName("name");
    var sc = new SparkContext(conf);
    var ssc = new StreamingContext(conf, Seconds(1));

  }

  def rdd1(): Unit ={
    var conf = new SparkConf().setMaster("local[1]").setAppName("name");
    var sc = new SparkContext(conf);

    val input = sc.textFile("/Users/chris/Desktop/spring/spark-learning/test.txt");

    val lines = input.flatMap(line=>line.split(" "));
    val count = lines.map(word => (word,1)).reduceByKey{
      case (x,y) => x+y
    }

    println("***********");
    println(count.count());
  }
}
