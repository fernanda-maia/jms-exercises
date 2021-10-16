package io.github.fernanda.maia.reservation;

import io.github.fernanda.maia.model.Passenger;
import lombok.SneakyThrows;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.naming.InitialContext;

public class ReservationListener implements MessageListener {

    @SneakyThrows
    @Override
    public void onMessage(Message message) {
        InitialContext initialContext = new InitialContext();

        try(ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
            JMSContext context = cf.createContext()) {

            JMSProducer producer = context.createProducer();

            ObjectMessage request = (ObjectMessage) message;
            Passenger details = (Passenger) request.getObject();

            MapMessage response = context.createMapMessage();
            response.setString("status", details.getIsReserved()? "APPROVED" : "REJECTED");

            producer.send(message.getJMSReplyTo(), response);

         }
    }
}
