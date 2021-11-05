package io.github.fernanda.maia;

import io.github.fernanda.maia.model.Track;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import io.github.fernanda.maia.serializer.TrackSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class TrackProducer {

    public static void main(String[] args) {
        Properties props = new Properties();

        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("key.serializer", StringSerializer.class.getName());
        props.setProperty("value.serializer", TrackSerializer.class.getName());

        try(KafkaProducer<String, Track> producer = new KafkaProducer<>(props)) {
            Track data = new Track();

            data.setId(1L);
            data.setLatitude("22.576N");
            data.setLongitude("88.3639E");

            ProducerRecord<String, Track> record = new ProducerRecord<>("TrackCSTopic", "coordinates", data);
            producer.send(record);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
