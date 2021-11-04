package io.github.fernanda.maia;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

public class TrackCallback implements Callback {
    @Override
    public void onCompletion(RecordMetadata metadata, Exception e) {
        System.out.println(metadata.partition());
        System.out.println("Message sent successfully!");
    }
}
