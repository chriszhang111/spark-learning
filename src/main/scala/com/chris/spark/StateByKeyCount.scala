package com.chris.spark

import java.sql.DriverManager

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  *
  */

object StateByKeyCount {

  def main(args: Array[String]): Unit = {
    var sparkConf = new SparkConf().setAppName("statefulwordcount").setMaster("local[2]");
    var ssc = new StreamingContext(sparkConf, Seconds(5));

    ssc.checkpoint(".");
    val lines = ssc.socketTextStream("localhost", 6789);

    val result = lines.flatMap(_.split(" ")).map((_, 1))

    result.foreachRDD(rdd => {
      val connection = createConnection();
      rdd.foreach(record => {
        val sql = "insert into wordcount(word, wordcount) values('"+record._1 + "'," + record._2+")"
        connection.createStatement().execute(sql);
      })
    });
//    var state = result.updateStateByKey(updateFunction _);

  }

  def createConnection() = {
    Class.forName("com.mysql.jdbc.Driver")
    DriverManager.getConnection("jdbc:mysql://localhost3306/imooc_spark", "root", "31415926");

  }

  def updateFunction(newValues: Seq[Int], preValue: Option[Int]) : Option[Int] = {
    val current = newValues.sum;
    val pre = preValue.getOrElse(0);
    Some(current + pre)
  }
}
