package com.ywfgu.activemq.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;


public class JMSConsumerTest {
    private static final String ACTIVE_URL = "tcp://localhost:61616";
    private static final String QUEUE_NAME = "TEST_TOPIC";

    public static void main(String[] args) throws JMSException, IOException {
        //1.创建连接工厂,采用默认的用户名和密码
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVE_URL);
        //2.获取连接并启动访问
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        //3.创建session(参数：事务、签收)
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        //4.创建目的地（具体是队列还是主题）
        Queue queue = session.createQueue(QUEUE_NAME);
        Topic topic = session.createTopic(QUEUE_NAME);
        //5.创建消费者
        MessageConsumer messageConsumer = session.createConsumer(topic);
        while (true) {
            TextMessage textMessage = (TextMessage)messageConsumer.receive(1000L); //只等待5s
//            TextMessage textMessage = (TextMessage)messageConsumer.receive(); //阻塞等待
            if(textMessage != null){
                String messageText = textMessage.getText();
                System.out.println("接收到消息："+messageText);
                session.rollback();
            }else{
//                break;
            }
        }
        //通过监听的方式来接收消息
//        messageConsumer.setMessageListener(new MessageListener() {
//            @Override
//            public void onMessage(Message message) {
//                if(null != message && message instanceof TextMessage){
//                    TextMessage textMessage = (TextMessage) message;
//                    try {
//                        System.out.println("接收到消息："+textMessage.getText());
//                    } catch (JMSException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//        messageConsumer.setMessageListener((message) ->{
//            if(null != message && message instanceof TextMessage){
//                TextMessage textMessage = (TextMessage) message;
//                try {
//                    System.out.println("接收到消息："+textMessage.getText());
//                } catch (JMSException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        System.in.read(); //防止还为接收到消息的时候主进程就跑完
//        messageConsumer.close();
//        session.close();
//        connection.close();
    }
}
