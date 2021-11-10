package io.github.fernanda.maia.kafka.avro.serializers;

import io.github.fernanda.maia.OrderSerializer;
import io.github.fernanda.maia.model.Order;
import io.github.fernanda.maia.partitioners.VIPPartitioner;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.record.Record;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class CustomOrderProducer {

    public static void main(String[] args) {
        Properties props = new Properties();

        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("key.serializer", StringSerializer.class.getName());
        props.setProperty("value.serializer", OrderSerializer.class.getName());
        props.setProperty("partitioner.class", VIPPartitioner.class.getName());

        try(KafkaProducer<String, Order> producer = new KafkaProducer<>(props)) {
            Order order = Order.builder()
                    .customer("John")
                    .product("Playstation 4")
                    .quantity(2)
                    .build();

            ProducerRecord<String, Order> record = new ProducerRecord<>("OrderPartitionTopic", order.getCustomer(), order);
            producer.send(record);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
