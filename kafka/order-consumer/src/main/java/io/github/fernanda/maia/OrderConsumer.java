package io.github.fernanda.maia;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class OrderConsumer {

    public static void main(String[] args) {
        Properties props = new Properties();

        props.setProperty("bootstrap.servers", "localhost:9092");

        // Producer >> serializer | Consumer >> deserializer
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.IntegerDeserializer");

        props.setProperty("group.id", "OrderGroup");

        KafkaConsumer<String, Integer> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("OrderTopic"));

        ConsumerRecords<String, Integer> records = consumer.poll(Duration.ofSeconds(20));

        records.forEach(order -> {
            System.out.println("Product Name: " + order.key());
            System.out.println("Quantity: " + order.value());
        });

        consumer.close();

    }
}
