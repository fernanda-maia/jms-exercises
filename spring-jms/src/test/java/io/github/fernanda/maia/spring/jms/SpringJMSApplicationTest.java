package io.github.fernanda.maia.spring.jms;

import io.github.fernanda.maia.spring.jms.sender.MessageSender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SpringJMSApplicationTest {

    @Autowired
    MessageSender messageSender;

    @Test
    public void testSendAndReceive() {
        messageSender.send("Hello, Spring JMS!");
    }
}
