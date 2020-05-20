# kafka-java-quotes

Before running the Zookeeper run the maven 

create a temp folder and open the powershell in this folder and run this command

```mvn archetype:generate ```


### To start the ZooKeeper service
```.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties ```
### To start the kafka service
``` .\bin\windows\kafka-server-start.bat .\config\server.properties ```
### To create a kafka topic
```.\bin\windows\kafka-topics.bat --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --create --topic quotes```
### To list the created topics 
``` .\bin\windows\kafka-topics.bat --zookeeper localhost:2181 --list ```

### To run the producer
This allows you to send the messages to the topic
``` java -cp target/kafka-java-quotes-1.0-SNAPSHOT-jar-with-dependencies.jar edu.nwmissouri.quotes.QuoteProducer quotes```
### To run the consumer 
It will display the messages sent to the topic
``` java -cp target/kafka-java-quotes-1.0-SNAPSHOT-jar-with-dependencies.jar edu.nwmissouri.quotes.QuoteConsumer quotes group1 ```
