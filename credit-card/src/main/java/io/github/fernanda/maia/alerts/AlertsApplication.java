package io.github.fernanda.maia.alerts;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class AlertsApplication {

    public static void main(String[] args) throws NamingException {
        InitialContext initialContext = new InitialContext();
        Topic responseTopic = (Topic) initialContext.lookup("topic/responseTopic");

        try(ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
            JMSContext context = cf.createContext()) {

            JMSConsumer consumer1 = context.createSharedConsumer(responseTopic, "getResponse");
            JMSConsumer consumer2 = context.createSharedConsumer(responseTopic, "getResponse");

            for(int i = 1; i < 6; i++) {
                MapMessage message = (MapMessage) consumer1.receive();

                System.out.println(message.getObject("transaction"));
                System.out.println(message.getBoolean("status")? "APPROVED" : "REJECTED");

                message = (MapMessage) consumer2.receive();

                System.out.println(message.getObject("transaction"));
                System.out.println(message.getBoolean("status")? "APPROVED" : "REJECTED");
            }


        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
