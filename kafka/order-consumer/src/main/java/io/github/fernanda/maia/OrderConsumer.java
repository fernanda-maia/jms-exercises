package io.github.fernanda.maia;

import io.github.fernanda.maia.deserializer.OrderDeserializer;
import io.github.fernanda.maia.model.Order;
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
        props.setProperty("value.deserializer", OrderDeserializer.class.getName());

        props.setProperty("group.id", "OrderGroup");

        KafkaConsumer<String, Order> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("OrderCSTopic"));

        ConsumerRecords<String, Order> records = consumer.poll(Duration.ofSeconds(20));

        records.forEach(order -> {
            System.out.println(order.key());
            System.out.println("Customer: " + order.value().getCustomer());
            System.out.println("Product Name: " + order.value().getProduct());
            System.out.println("Quantity: " + order.value().getQuantity());
        });

        consumer.close();

    }
}
