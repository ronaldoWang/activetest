package com.ronaldo.activetest.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.SessionAwareMessageListener;

import javax.annotation.Resource;
import javax.jms.*;

/**
 * Created by ronaldo on 2017/8/5.
 */
public class MessageMqListener implements SessionAwareMessageListener<Message> {

    @Autowired
    private JmsTemplate jmsTemplate;
    @Resource(name = "demoQueueDestination")
    private Destination destination;

    public void onMessage(Message message, Session session) throws JMSException {
        TextMessage textMessage = (TextMessage) message;
        System.out.println(textMessage.getText());
    }
}
