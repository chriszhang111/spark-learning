package com.chris.spark

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}


object NetworkWordCount {

  def main(args: Array[String]): Unit = {
    var conf = new SparkConf().setMaster("local[2]").setAppName("NetworkCount");
    val ssc = new StreamingContext(conf, Seconds(5));

     var lines = ssc.socketTextStream("localhost", 4567);
//
     //var result = lines.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_+_);
//
//    result.print();
//
//    ssc.start();
//    ssc.awaitTermination();
  }
}
