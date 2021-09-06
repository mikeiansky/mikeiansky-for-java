package com.winson.framework.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;

import javax.jms.*;
import java.util.concurrent.CountDownLatch;

/**
 * @author winson
 * @date 2021/6/28
 **/
public class ActiveMQQueueConsumerWithRetryExceptionDemo {

    public static void main(String[] args) throws InterruptedException, JMSException {

        CountDownLatch latch = new CountDownLatch(1);

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                MQConfig.CONNECT_WITH_TCP
        );

        // TODO 配置在producer这里是无效的，需要配置在consumer里面
        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
        redeliveryPolicy.setInitialRedeliveryDelay(5000);
        redeliveryPolicy.setMaximumRedeliveries(3);
        redeliveryPolicy.setQueue(MQConfig.QUEUE_WINSON_HOME_DEV_1);
        connectionFactory.setRedeliveryPolicy(redeliveryPolicy);

        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        Destination destination = session.createQueue(MQConfig.QUEUE_WINSON_HOME_DEV_1);
        MessageConsumer consumer = session.createConsumer(destination);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                System.out.println("mq service receive message : " + message);
                try {
//                    message.acknowledge();
                    session.recover();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        latch.await();
        consumer.close();
        session.close();
        connection.close();
    }

}
