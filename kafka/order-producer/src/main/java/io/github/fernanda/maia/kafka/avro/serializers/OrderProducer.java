package io.github.fernanda.maia.kafka.avro.serializers;

import io.github.fernanda.maia.kafka.avro.Order;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import io.confluent.kafka.serializers.KafkaAvroSerializer;

import java.util.Properties;

public class OrderProducer {

    public static void main(String[] args) {
        Properties props = new Properties();

        props.setProperty("bootstrap.servers", "localhost:9092");

        // Producer >> serializer | Consumer >> deserializer
        props.setProperty("key.serializer", KafkaAvroSerializer.class.getName());
        props.setProperty("value.serializer", KafkaAvroSerializer.class.getName());
        props.setProperty("schema.registry.url", "http://localhost:8081");

        try(KafkaProducer<String, Order> producer = new KafkaProducer<>(props)) {
            Order order = new Order();

            order.setCustomer("John Doe");
            order.setProduct("Macbook Pro");
            order.setQuantity(1);

            ProducerRecord<String, Order> record = new ProducerRecord<>("OrderAvroTopic", "data", order);

            producer.send(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
