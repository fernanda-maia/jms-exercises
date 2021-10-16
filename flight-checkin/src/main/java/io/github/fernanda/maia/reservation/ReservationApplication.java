package io.github.fernanda.maia.reservation;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ReservationApplication {

    public static void main(String[] args) throws NamingException {
        InitialContext initialContext = new InitialContext();
        Queue requestQueue = (Queue) initialContext.lookup("queue/requestQueue");

        try(ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
            JMSContext context = cf.createContext()) {

            JMSConsumer consumer = context.createConsumer(requestQueue);
            consumer.setMessageListener(new ReservationListener());

            Thread.sleep(1500);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
