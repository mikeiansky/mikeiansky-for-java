package com.winson.framework.activemq.v1;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author winson
 * @date 2022/7/29
 **/
public class ActivemqProduceDemoV1 {

    public static void main(String[] args) throws JMSException {

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("nio://127.0.0.1:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(true, Session.CLIENT_ACKNOWLEDGE);
        System.out.println("--- 000 1");
        Queue queue = session.createQueue("winson-dev-002");
        System.out.println("--- 000 2");
        MessageProducer messageProducer = session.createProducer(queue);
        System.out.println("--- 000 3");
        TextMessage textMessage = session.createTextMessage("hello winson-dev-002 006");
        System.out.println("--- 000 4");
        messageProducer.send(textMessage);
        System.out.println("--- 000 5");
        session.commit();

        messageProducer.close();
        session.close();
        connection.close();

    }

}
