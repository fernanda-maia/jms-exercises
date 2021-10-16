package io.github.fernanda.maia.checkin;

import io.github.fernanda.maia.model.Passenger;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class CheckinApplication {

    public static void main(String[] args) throws NamingException {
        InitialContext initialContext = new InitialContext();
        Queue requestQueue = (Queue) initialContext.lookup("queue/requestQueue");
        Queue replyQueue = (Queue) initialContext.lookup("queue/replyQueue");

        try(ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
            JMSContext context = cf.createContext()) {

            JMSProducer producer = context.createProducer();

            ObjectMessage request = context.createObjectMessage();
            request.setJMSReplyTo(replyQueue);

            Passenger details = Passenger
                    .builder()
                    .id(1L)
                    .firstName("John")
                    .lastName("Doe")
                    .email("email@email.com")
                    .isReserved(true)
                    .phoneNumber("999999999")
                    .build();

            request.setObject(details);
            producer.send(requestQueue, request);

            JMSConsumer consumer = context.createConsumer(request.getJMSReplyTo());
            MapMessage response = (MapMessage) consumer.receive();

            System.out.println("CHECKIN RESERVATION STATUS: " + response.getString("status"));

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
