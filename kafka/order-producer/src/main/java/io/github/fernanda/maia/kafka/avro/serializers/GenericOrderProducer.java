package io.github.fernanda.maia.kafka.avro.serializers;

import org.apache.avro.Schema;
import org.apache.avro.Schema.Parser;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.GenericData.Record;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import io.confluent.kafka.serializers.KafkaAvroSerializer;

import java.util.Properties;

public class GenericOrderProducer {

    public static void main(String[] args) {
        Properties props = new Properties();

        props.setProperty("bootstrap.servers", "http://localhost:9092");
        props.setProperty("key.serializer", KafkaAvroSerializer.class.getName());
        props.setProperty("value.serializer", KafkaAvroSerializer.class.getName());
        props.setProperty("schema.registry.url", "http://localhost:8081");

        try(KafkaProducer<String, GenericRecord> producer = new KafkaProducer<>(props)) {
           Parser parser = new Parser();

           Schema schema = parser.parse("{\n" +
                   "  \"namespace\": \"io.github.fernanda.maia.kafka.avro\",\n" +
                   "  \"type\": \"record\",\n" +
                   "  \"name\": \"Order\",\n" +
                   "  \"fields\": [\n" +
                   "    { \"name\": \"customer\", \"type\": \"string\" },\n" +
                   "    { \"name\": \"product\", \"type\": \"string\" },\n" +
                   "    { \"name\": \"quantity\", \"type\": \"int\" }\n" +
                   "  ]\n" +
                   "}");

           GenericRecord order = new Record(schema);
           order.put("customer", "John Doe");
           order.put("product", "Iphone");
           order.put("quantity", 1);

            ProducerRecord<String, GenericRecord> record = new ProducerRecord<>("OrderAvroGRTopic", "data", order);
            producer.send(record);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
