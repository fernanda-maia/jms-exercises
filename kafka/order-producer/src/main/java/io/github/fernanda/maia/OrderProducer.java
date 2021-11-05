package io.github.fernanda.maia;

import io.github.fernanda.maia.model.Order;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.Future;

public class OrderProducer {

    public static void main(String[] args) {
        Properties props = new Properties();

        props.setProperty("bootstrap.servers", "localhost:9092");

        // Producer >> serializer | Consumer >> deserializer
        props.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.setProperty("value.serializer", "io.github.fernanda.maia.serializers.OrderSerializer");

        try(KafkaProducer<String, Order> producer = new KafkaProducer<>(props)) {
            Order order = new Order();

            order.setCustomer("John Doe");
            order.setProduct("Macbook Pro");
            order.setQuantity(1);

            ProducerRecord<String, Order> record = new ProducerRecord<>("OrderCSTopic", "data", order);

            producer.send(record);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
