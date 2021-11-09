package io.github.fernanda.maia.model.serializer;

import lombok.SneakyThrows;
import io.github.fernanda.maia.model.Track;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

public class TrackSerializer implements Serializer<Track> {

    @Override
    @SneakyThrows
    public byte[] serialize(String topic, Track track) {
        ObjectMapper mapper = new ObjectMapper();
        byte[] response = mapper.writeValueAsString(track).getBytes();

        return response;
    }
}
