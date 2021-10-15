package io.github.fernanda.maia.security;

import io.github.fernanda.maia.model.CreditTransaction;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class SecurityApplication {

    public static void main(String[] args) throws NamingException {
        InitialContext initialContext = new InitialContext();
        Topic creditTopic = (Topic) initialContext.lookup("topic/creditTopic");

        try(ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
            JMSContext context = cf.createContext()) {
            context.setClientID("securityApp");

            JMSConsumer consumer = context.createDurableConsumer(creditTopic, "securityApp");
            MapMessage response = context.createMapMessage();
            consumer.close();

            System.out.println("Consumer Closed!");
            Thread.sleep(25000);
            System.out.println("Consumer Created Again!");

            consumer = context.createDurableConsumer(creditTopic, "securityApp");


            for(int i = 1; i < 11; i++) {
                ObjectMessage request = (ObjectMessage) consumer.receive();
                CreditTransaction transaction = (CreditTransaction) request.getObject();

                response.setString("transaction", transaction.toString());
                response.setBoolean("status", transaction.getAmount() < 5000.00);

                JMSProducer producer = context.createProducer();
                producer.send(request.getJMSReplyTo(), response);
            }

            consumer.close();
            context.unsubscribe("securityApp");

        } catch (JMSException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
