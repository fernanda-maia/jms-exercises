package io.github.fernanda.maia;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

public class OrderCallback implements Callback {
    @Override
    public void onCompletion(RecordMetadata metadata, Exception e) {
        System.out.println(metadata.partition());
        System.out.println(metadata.offset());

        System.out.println("Message callback sent successfully!");

        if(e != null) {
            e.printStackTrace();
        }
    }
}
