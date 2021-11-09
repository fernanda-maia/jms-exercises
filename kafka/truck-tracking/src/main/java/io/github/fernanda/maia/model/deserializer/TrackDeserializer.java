package io.github.fernanda.maia.model.deserializer;

import lombok.SneakyThrows;
import io.github.fernanda.maia.model.Track;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

public class TrackDeserializer implements Deserializer<Track> {

    @Override
    @SneakyThrows
    public Track deserialize(String topic, byte[] data) {
        Track response = null;
        ObjectMapper mapper = new ObjectMapper();

        response = mapper.readValue(data, Track.class);

        return response;
    }
}
