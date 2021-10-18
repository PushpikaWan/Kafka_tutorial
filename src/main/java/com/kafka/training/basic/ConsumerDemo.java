package com.kafka.training.basic;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import static com.kafka.training.basic.Constants.BOOTSTRAP_SERVER;
import static com.kafka.training.basic.Constants.FIRST_TOPIC;

public class ConsumerDemo {

    private final static Logger logger = LoggerFactory.getLogger(ConsumerDemo.class.getName());
    private final static String GROUP_ID = "group_one";

    public static void main(String[] args) {
        //create properties
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        //create consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        //subscribe consumer to topic
        consumer.subscribe(Collections.singletonList(FIRST_TOPIC));

        //poll for new data
        while(true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

            for (ConsumerRecord<String, String> consumerRecord: records) {
                logger.info("value: ",consumerRecord.value());
                logger.info("Partition: ", consumerRecord.partition()," offset: ", consumerRecord.offset());
            }
        }
    }
}
