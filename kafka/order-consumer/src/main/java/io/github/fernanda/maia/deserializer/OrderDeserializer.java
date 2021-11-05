package io.github.fernanda.maia.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.fernanda.maia.model.Order;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.Deserializer;

public class OrderDeserializer implements Deserializer<Order> {

    @Override
    @SneakyThrows
    public Order deserialize(String topic, byte[] data) {
        Order response = null;
        ObjectMapper mapper = new ObjectMapper();

        response = mapper.readValue(data, Order.class);
        return response;
    }
}
