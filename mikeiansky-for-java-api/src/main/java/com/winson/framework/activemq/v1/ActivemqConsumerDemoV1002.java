package com.winson.framework.activemq.v1;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.*;

/**
 * @author winson
 * @date 2022/7/29
 **/
public class ActivemqConsumerDemoV1002 {

    public static void main(String[] args) throws JMSException {

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        connection.start();
//        Queue queue = session.createQueue("winson-dev-002");
        ActiveMQQueue queue = new ActiveMQQueue("winson-dev-002");
        MessageConsumer consumer = session.createConsumer(queue);

        Message message = consumer.receive();
        TextMessage textMessage = (TextMessage) message;
        System.out.println("-----<> <> <> <> ---- receive message is " + textMessage.getText());
        message.acknowledge();
//        session.commit();
        System.out.println("commit ......... ");

    }

}
