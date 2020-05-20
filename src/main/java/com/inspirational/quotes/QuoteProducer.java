package com.inspirational.quotes;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

public class QuoteProducer {
    private static Scanner in;

    public static void main(String[] argv) throws Exception {
      if (argv.length != 1) {
        System.err.println("Please specify 1 parameter (the name of the topic)");
        System.exit(-1);
      }
      String topicName = argv[0];
      in = new Scanner(System.in);
      System.out.println("Thank you for providing the topic " + topicName + "\n");
      System.out.println("Enter message (type exit to quit).\n");
  
      // Configure the Producer
      Properties configProperties = new Properties();
      configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
      configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
          "org.apache.kafka.common.serialization.ByteArraySerializer");
      configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
          "org.apache.kafka.common.serialization.StringSerializer");
      System.out.println("The configuration properties are: " + configProperties.toString());
      System.out.println("\nWill use this configuration to create a producer.\n");
  
      org.apache.kafka.clients.producer.Producer producer = new KafkaProducer(configProperties);
  
      // Make our own messages - create your custom logic here
  
     
        String message = createQuotes();
        ProducerRecord<String, String> rec = new ProducerRecord<String, String>(topicName, message);
        producer.send(rec);
    
  
      // still allow input from keyboard
  
      String line = in.nextLine();
      while (!line.equals("exit")) {
        ProducerRecord<String, String> rec1 = new ProducerRecord<String, String>(topicName, line);
        producer.send(rec1);
        line = in.nextLine();
      }
  
      in.close();
      producer.close();
  
    }
  
    private static String createQuotes() {
      String[] arrayOfQuotes = {"Dont take rest after your first victory because if you fail in second, more lips are waiting to say that your first victory was just luck.","You have to dream before your dreams can come true","Failure will never overtake me if my definition to succeed is strong enough.","Look at the sky. We are not alone. The whole universe is friendly to us and conspires only to those who dream and work."};
      String value = " ";
      for (int i=0;i<arrayOfQuotes.length;i++){
         value = value + "quote" + i  + " -- "+arrayOfQuotes[i]+"\n";
      }
      return value;
    }
}