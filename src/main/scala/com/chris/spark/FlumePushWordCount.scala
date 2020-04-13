package com.chris.spark

import org.apache.spark.SparkConf
import org.apache.spark.streaming.flume.FlumeUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}


object FlumePushWordCount {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("FlumePush")
    var ssc = new StreamingContext(sparkConf, Seconds(5))

    FlumeUtils.createStream(ssc, "localhost", 41414)

    ssc.start()
    ssc.awaitTermination()
  }
}
