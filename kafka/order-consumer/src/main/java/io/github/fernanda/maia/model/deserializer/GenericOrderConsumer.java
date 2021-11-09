package io.github.fernanda.maia.model.deserializer;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class GenericOrderConsumer {

    public static void main(String[] args) {
        Properties props = new Properties();

        props.setProperty("bootstrap.servers", "http://localhost:9092");
        props.setProperty("key.deserializer", KafkaAvroDeserializer.class.getName());
        props.setProperty("value.deserializer", KafkaAvroDeserializer.class.getName());
        props.setProperty("group.id", "OrderGroup");
        props.setProperty("schema.registry.url", "http://localhost:8081");

        KafkaConsumer<String, GenericRecord> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("OrderAvroGRTopic"));

        ConsumerRecords<String, GenericRecord> records = consumer.poll(Duration.ofSeconds(20));

        records.forEach(r -> {
            GenericRecord temp = r.value();

            System.out.println("Key: " + r.key());
            System.out.println("CUSTOMER: " + temp.get("customer"));
            System.out.println("PRODUCT: " + temp.get("product"));
            System.out.println("QUANTITY: " + temp.get("quantity"));
        });
        consumer.close();

    }
}
