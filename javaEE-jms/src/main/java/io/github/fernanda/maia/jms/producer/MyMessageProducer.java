package io.github.fernanda.maia.jms.producer;

import javax.jms.Queue;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ejb.LocalBean;
import javax.jms.JMSContext;
import javax.annotation.Resource;

@Stateless
@LocalBean
public class MyMessageProducer {

    @Resource(mappedName = "java:/queue/myQueue")
    Queue myQueue;

    @Inject
    JMSContext context;

    public void sendMessage(String message) {
        context.createProducer().send(myQueue, message);
    }
}
