package com.ronaldo.springmvc;

import com.ronaldo.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.jms.*;
import java.util.Date;

/**
 * Created by ronaldo on 2017/8/6.
 */
@Controller
public class ActiveMqController {

    @Autowired
    JmsTemplate jmsTemplate;

    @Resource(name = "demoQueueDestination")
    Destination testQueue;

    @RequestMapping(name = "/sendMessage")
    public String sendMessage() {
        System.out.println("start send message");
        jmsTemplate.send(testQueue, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                // TextMessage message = session.createTextMessage();
                //  message.setText("QueueProducerService发送消息: 我是王浩锡");
                ObjectMessage objectMessage = session.createObjectMessage();
                Order order = new Order();
                order.setId(1);
                order.setOrderDate(new Date());
                order.setOrderNo("123456");
                objectMessage.setObject(order);
                return objectMessage;
            }
        });
        return "success";
    }
}
