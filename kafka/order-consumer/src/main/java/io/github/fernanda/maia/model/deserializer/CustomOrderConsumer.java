package io.github.fernanda.maia.model.deserializer;

import io.github.fernanda.maia.OrderDeserializer;
import io.github.fernanda.maia.model.Order;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class CustomOrderConsumer {

    public static void main(String[] args) {
        Properties props = new Properties();

        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("key.deserializer", StringDeserializer.class.getName());
        props.setProperty("value.deserializer", OrderDeserializer.class.getName());
        props.setProperty("group.id", "OrderCSGroup");

        KafkaConsumer<String, Order> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("OrderPartitionTopic"));

        try {
            while (true) {
                ConsumerRecords<String, Order> records = consumer.poll(Duration.ofSeconds(20));

                records.forEach(o -> {
                    Order order = o.value();

                    System.out.println("\nCUSTOMER: " + order.getCustomer());
                    System.out.println("PRODUCT: " + order.getProduct());
                    System.out.println("QUANTITY: " + order.getQuantity());
                    System.out.println("PARTITION: " + o.partition());
                });
            }

        } catch (Exception e) {
            consumer.close();
        }

    }
}
