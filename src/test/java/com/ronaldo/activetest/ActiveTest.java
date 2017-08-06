package com.ronaldo.activetest;

import com.ronaldo.entity.Order;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jms.*;
import java.util.Date;

/**
 * Created by ronaldo on 2017/8/4.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-root.xml", "classpath:activemq.xml"})
public class ActiveTest {

    @Autowired
    JmsTemplate jmsTemplate;

    @Resource(name = "demoQueueDestination")
    Destination testQueue;

    @Test
    @Transactional
    @Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚
    public void testproducer() {
        jmsTemplate.send(testQueue, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                // TextMessage message = session.createTextMessage();
                //  message.setText("QueueProducerService发送消息: 我是王浩锡");
                ObjectMessage objectMessage = session.createObjectMessage();
                Order order = new Order();
                order.setId(1);
                order.setOrderDate(new Date());
                order.setOrderNo("1234567");
                objectMessage.setObject(order);
                return objectMessage;
            }
        });
    }

    @Test
    @Transactional
    @Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚
    public void testConsumer() {
        TextMessage message = (TextMessage) jmsTemplate.receive(testQueue);
        try {
            System.out.println("从队列" + testQueue.toString() + "收到了消息：\t"
                    + message.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    @Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚
    public void testConsumerOrder() {
        ObjectMessage message = (ObjectMessage) jmsTemplate.receive(testQueue);
        try {
            Order order = (Order) message.getObject();
            System.out.println("从队列" + testQueue.toString() + "收到了订单编号：\t"
                    + order.getOrderNo());
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
