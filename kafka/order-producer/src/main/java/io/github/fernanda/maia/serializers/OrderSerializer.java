package io.github.fernanda.maia.serializers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.fernanda.maia.model.Order;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.StandardCharsets;

public class OrderSerializer implements Serializer<Order> {

    @Override
    @SneakyThrows
    public byte[] serialize(String topic, Order order) {
        // Covert Java POJO in byte array
        byte[] response = null;
        ObjectMapper mapper = new ObjectMapper();
        response = mapper.writeValueAsString(order).getBytes();

        return response;
    }
}
