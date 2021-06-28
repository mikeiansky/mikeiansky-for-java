package com.winson.framework.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.concurrent.CountDownLatch;

/**
 * @author winson
 * @date 2021/6/28
 **/
public class ActiveMQTopicConsumerDemo2 {

    public static void main(String[] args) throws JMSException, InterruptedException {

        final CountDownLatch latch = new CountDownLatch(1);

        ConnectionFactory connectionFactory =  new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                MQConfig.CONNECT_WITH_TCP);

        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(MQConfig.TOPIC_WINSON_HOME_DEV_1);
        MessageConsumer consumer = session.createConsumer(topic);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                System.out.println("topic client 2 receive message : " + message);
            }
        });

        latch.await();
        connection.close();

    }

}
