package com.chris.spark;


import org.apache.kafka.clients.producer.Producer;

/**
 * @Author: Chris Zhang
 * @Date: 4/8/20 14:42
 */
public class KafkaProducer {


    private String topic;



    public KafkaProducer(String topic){
        this.topic = topic;

    }
}
