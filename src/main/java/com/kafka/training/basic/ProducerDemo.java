package com.kafka.training.basic;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

import static com.kafka.training.basic.Constants.BOOTSTRAP_SERVER;
import static com.kafka.training.basic.Constants.FIRST_TOPIC;

public class ProducerDemo {

    public static void main(String[] args) {


        //create properties - https://kafka.apache.org/documentation/#producerconfigs
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());


        //create the producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        //create a producer record
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(FIRST_TOPIC, "hello world2");

        //send data
        producer.send(producerRecord);
        producer.flush();
        producer.close();
    }
}
