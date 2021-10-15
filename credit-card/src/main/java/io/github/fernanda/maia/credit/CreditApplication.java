package io.github.fernanda.maia.credit;

import io.github.fernanda.maia.model.CreditTransaction;
import io.github.fernanda.maia.util.enums.TransactionType;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class CreditApplication {

    public static void main(String[] args) throws NamingException {
        InitialContext initialContext = new InitialContext();

        Topic creditTopic = (Topic) initialContext.lookup("topic/creditTopic");
        Topic responseTopic = (Topic) initialContext.lookup("topic/responseTopic");

        try(ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
            JMSContext context = cf.createContext()) {

            JMSProducer producer = context.createProducer();
            ObjectMessage message = context.createObjectMessage();

            CreditTransaction transaction = CreditTransaction
                    .builder()
                    .id(1L)
                    .client_id(1L)
                    .type(TransactionType.DEPOSIT)
                    .amount(1000.00)
                    .build();

            message.setJMSCorrelationID("creditApp");
            message.setJMSReplyTo(responseTopic);
            message.setObject(transaction);

            for(int i = 1; i < 11; i++) {
                producer.send(creditTopic, message);
                System.out.printf("Message %d Sent!\n", i);
            }

            for(int i = 1; i < 11; i++) {
                JMSConsumer consumer = context.createConsumer(message.getJMSReplyTo());
                System.out.println(consumer.receive());
            }


        } catch (JMSException e) {

            e.printStackTrace();
        }
    }
}
