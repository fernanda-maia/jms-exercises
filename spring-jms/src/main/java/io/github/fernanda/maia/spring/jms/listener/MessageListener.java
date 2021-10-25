package io.github.fernanda.maia.spring.jms.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @JmsListener(destination = "${springjms.springQueue}")
    public void receive(String message) {
        System.out.println("Message receive: " + message);
    }
}
