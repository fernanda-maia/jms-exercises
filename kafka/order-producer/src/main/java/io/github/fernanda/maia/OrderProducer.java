package io.github.fernanda.maia;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.Future;

public class OrderProducer {

    public static void main(String[] args) {
        Properties props = new Properties();

        props.setProperty("bootstrap.servers", "localhost:9092");

        // Produce >> serializer | Consumer >> deserializer
        props.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.setProperty("value.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");

         try(KafkaProducer<String, Integer> producer = new KafkaProducer<>(props);) {
             ProducerRecord<String, Integer> record = new ProducerRecord<>("OrderTopic", "Macbook Pro", 10);

             // Sync call
             Future<RecordMetadata> send = producer.send(record);
             RecordMetadata metadata = send.get();

             // Async call
             producer.send(record, new OrderCallback());

             System.out.println(metadata.partition());
             System.out.println(metadata.offset());

             System.out.println("Message sent successfully!");

         } catch (Exception e) {
             e.printStackTrace();
         }



    }
}
