
### initialize Project
- Create Maven / Gradle project
- Add Kafka dependency first (chose this https://mvnrepository.com/artifact/org.apache.kafka/kafka)
- Add SL4j simple binding (https://mvnrepository.com/artifact/org.slf4j/slf4j-simple) (default it has test scope, remove it)



## configuration
1. add dataDir path in zookeeper
2. add logs.dir in server.properties 
3. add below line to server.properties
    port = 9092
    advertised.host.name = localhost 

## run steps
1. Start zookeeper first
    `zookeepr-server-start.sh software/kafka/config/zookeeper.properties`
2. Start kafka
    `kafka-server-start.sh software/kafka/config/server.properties`
3. To create topic 
    `kafka-topics.sh --zookeeper 127.0.0.1:2181 --topic first_topic --create --partitions 3 --replication-factor 1`
4. To see all topic
    `kafka-topics.sh --zookeeper 127.0.0.1:2181 --list`
    `kafka-topics.sh --zookeeper 127.0.0.1:2181 --topic first_topic --decribe`
5. Delete topic
    `kafka-topics.sh --zookeeper 127.0.0.1:2181 --topic first_topic --delete`
6. Create Producer
    `kafka-console-producer.sh --broker-list 127.0.0.1:9092 --topic first_topic`
     additional flags  `--producer-propery acks=all`
7.  Create Consumer
    `kafka-console-consumer.sh --bootstrap-server 127.0.0.1:9092 --topic first_topic`
     additional flags  `--from-beginning`
8. Create group consumer
- then messages are deivided and go to different different consumers
    `kafka-console-consumer.sh --bootstrap-server 127.0.0.1:9092 --topic first_topic --group my-group-one`

## notes
* Cannot have replication-factor grater than number of broker
* Consumer groups, lags, offsets
* Replay-> --reset-offset flags
* Use key with topic and message in producer then we can make sure order of that with same key
* Rebalancing when new consumer added or removed
* Consumer assgin and seek
