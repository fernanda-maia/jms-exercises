package io.github.fernanda.maia;

import io.github.fernanda.maia.deserializer.TrackDeserializer;
import io.github.fernanda.maia.model.Track;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Properties;
import java.util.Collections;

public class TrackConsumer {

    public static void main(String[] args) {
        Properties props = new Properties();

        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("key.deserializer", StringDeserializer.class.getName());
        props.setProperty("value.deserializer", TrackDeserializer.class.getName());
        props.setProperty("group.id", "TrackGroup");

        KafkaConsumer<String, Track> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("TrackCSTopic"));

        ConsumerRecords<String, Track> records = consumer.poll(Duration.ofSeconds(20));

        records.forEach(c -> {
            System.out.println(c.key());
            System.out.println("ID: " + c.value().getId());
            System.out.println("Latitude: " + c.value().getLatitude());
            System.out.println("Longitude: " + c.value().getLongitude());
        });

        consumer.close();
    }
}
