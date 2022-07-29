package com.winson.framework.activemq.v1;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.*;

/**
 * @author winson
 * @date 2022/7/29
 **/
public class ActivemqConsumerDemoV1 {

    public static void main(String[] args) throws JMSException {

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        connection.start();
//        Queue queue = session.createQueue("winson-dev-002");
        ActiveMQQueue queue = new ActiveMQQueue("winson-dev-002");
        MessageConsumer consumer = session.createConsumer(queue);
//        consumer.setMessageListener(new MessageListener() {
//            @Override
//            public void onMessage(Message message) {
//                System.out.println("receive message : " + message);
//            }
//        });

        for(;;){
            Message message = consumer.receive();
            TextMessage textMessage = (TextMessage) message;
            System.out.println("receive message is " + textMessage.getText());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("commit .... ");
            message.acknowledge();

//            session.recover();
//            session.commit();
        }

    }

}
