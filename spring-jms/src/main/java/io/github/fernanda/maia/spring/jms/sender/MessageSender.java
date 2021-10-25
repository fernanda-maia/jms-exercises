package io.github.fernanda.maia.spring.jms.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value(value = "${springjms.springQueue}")
    private String queue;

    public void send(String message) {
        MessageCreator mc = session -> session.createTextMessage("Hello from message creator!");
        jmsTemplate.convertAndSend(queue, message);
        jmsTemplate.send(queue, mc);
    }
 }
