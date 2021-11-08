package io.github.fernanda.maia;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.fernanda.maia.model.Order;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.Serializer;

public class OrderSerializer implements Serializer<Order> {

    @Override
    @SneakyThrows
    public byte[] serialize(String topic, Order order) {
        // Convert Java POJO in byte array
        byte[] response = null;
        ObjectMapper mapper = new ObjectMapper();
        response = mapper.writeValueAsString(order).getBytes();

        return response;
    }
}
