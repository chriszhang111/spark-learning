package com.chris.spark

import org.apache.spark.SparkConf
import org.apache.spark.streaming.flume.FlumeUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}


object FlumePushWordCount {

  def main(args: Array[String]): Unit = {

    if(args.length != 2){
      System.err.println("Usage: FlumePushWordCount <hostname> <port>")
    }

    val Array(hostname, port) = args

    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("FlumePush")
    var ssc = new StreamingContext(sparkConf, Seconds(5))

    val flumeStream = FlumeUtils.createStream(ssc, hostname, port.toInt)
    flumeStream.map(x => new String(x.event.getBody.array()).trim)
      .flatMap(_.split(" ")).map((_, 1)).reduceByKey(_+_).print()



    ssc.start()
    ssc.awaitTermination()
  }
}
