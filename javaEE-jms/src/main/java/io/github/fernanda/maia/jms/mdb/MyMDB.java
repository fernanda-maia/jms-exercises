package io.github.fernanda.maia.jms.mdb;

import javax.jms.Message;
import javax.jms.TextMessage;
import javax.jms.JMSException;
import javax.ejb.MessageDriven;
import java.util.logging.Logger;
import javax.jms.MessageListener;
import javax.ejb.ActivationConfigProperty;

@MessageDriven(name = "MyMDB", activationConfig = {
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/myQueue"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
})
public class MyMDB implements MessageListener {
    private static Logger LOGGER = Logger.getLogger(MyMDB.class.toString());

    @Override
    public void onMessage(Message message) {
        if(message instanceof TextMessage) {
            try {
                String text = ((TextMessage) message).getText();
                LOGGER.info("RECEIVED MESSAGE IS: " + text);

            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}