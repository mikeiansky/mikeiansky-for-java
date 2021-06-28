package com.winson.framework.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.*;
import java.util.concurrent.CountDownLatch;

/**
 * @author winson
 * @date 2021/6/28
 **/
public class ActiveMQTopicProducerDemo {

    public static void main(String[] args) throws JMSException, InterruptedException {

        final CountDownLatch latch = new CountDownLatch(1);

        ConnectionFactory connectionFactory =  new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                MQConfig.CONNECT_WITH_TCP);

        Connection connection = connectionFactory.createConnection();
        connection.start();

//        ActiveMQQueue qu = new ActiveMQQueue("");

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(MQConfig.TOPIC_WINSON_HOME_DEV_1);
        MessageProducer producer = session.createProducer(topic);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        producer.send(session.createTextMessage("hello world, this is topic 3!"));

        latch.await();
        connection.close();

    }

}
