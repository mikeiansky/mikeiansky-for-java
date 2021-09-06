package com.winson.framework.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.concurrent.ConcurrentHashMap;
import javax.jms.*;
import java.util.concurrent.CountDownLatch;

/**
 * @author winson
 * @date 2021/6/28
 **/
public class ActiveMQQueueConsumerDemo {

    public static void main(String[] args) throws InterruptedException, JMSException {

        CountDownLatch latch = new CountDownLatch(1);

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                MQConfig.CONNECT_WITH_TCP
        );

        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(MQConfig.QUEUE_WINSON_HOME_DEV_1);
        MessageConsumer consumer = session.createConsumer(destination);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                System.out.println("mq service receive message : " + message);
//                try {
//                    message.acknowledge();
////                    session.recover();
//                } catch (JMSException e) {
//                    e.printStackTrace();
//                }
            }
        });

        latch.await();
        consumer.close();
        session.close();
        connection.close();
    }

}
