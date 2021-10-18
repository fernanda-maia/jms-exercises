package io.github.fernanda.maia;

import io.github.fernanda.maia.model.Claim;
import io.github.fernanda.maia.util.mocks.ClaimMock;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ClaimManagementApplication {

    public static void main(String[] args) throws NamingException {
        InitialContext initialContext = new InitialContext();
        Queue claimQueue = (Queue) initialContext.lookup("queue/claimQueue");

        try(ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
            JMSContext context = cf.createContext()) {

            JMSProducer producer = context.createProducer();
            ObjectMessage message = context.createObjectMessage();

            Claim payload = ClaimMock.createClaim();

            message.setObject(payload);
            message.setStringProperty("provider", payload.getProvider().getProviderName().toLowerCase());
            message.setStringProperty("doctor", payload.getDoctor().getFullName());
            message.setDoubleProperty("amount", payload.getClaimAmount());

            producer.send(claimQueue, message);

            String query = "provider IN ('blue cross', 'america')" +
                    " AND doctor LIKE 'H%'" +
                    " AND amount%10=0";

            JMSConsumer consumer = context.createConsumer(claimQueue, query);

            ObjectMessage messageReceived = (ObjectMessage) consumer.receive(3000);
            Claim claimReceived = (Claim) messageReceived.getObject();

            System.out.println(claimReceived);

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
