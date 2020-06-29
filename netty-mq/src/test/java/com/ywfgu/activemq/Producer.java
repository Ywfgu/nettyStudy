package com.ywfgu.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

/**
 * @author guht
 * @create 2020/5/15
 * @Description
 */
public class Producer {

    @Test
    public void Producer() throws JMSException, InterruptedException {
        String url ="failover:(tcp://10.8.132.61:62616,tcp://10.8.132.63:62616,tcp://10.8.132.65:62616)";
        String topicName = "NETTY_TEST_QUEUE";

        //1. 创建ConnectionFactory
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin","eastcom",url);
        //2. 创建Connection
        Connection connection = connectionFactory.createConnection();
//        connection.setClientID("Producer-test");
        //3. 启动连接
        connection.start();
        //4. 创建会话
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        //5. 创建消息队列
        Destination destination=session.createQueue(topicName);
//        Topic destination=session.createTopic(topicName);
//        Queue selector1 = session.createQueue("selector1");
        //6. 创建消息生产者
        MessageProducer messageProducer=session.createProducer(destination);
//        messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);//使用持久模式
        //7. 发送消息
        for (int i=0;i<2000;i++) {
            TextMessage message = session.createTextMessage(""+i);
//            message.setStringProperty("agentId","81AFF737D67746C99A9B9D4905D9724"+i);
//            message.setStringProperty("agentName","local"+i);
            messageProducer.send(message);
//            messageProducer.send(session.createTextMessage("topic"+i));
//            System.out.println("send topic"+i);
//            Thread.sleep(100L);
            if(i % 2000 ==0){
                session.commit();
                System.out.println("commit count="+i);
//                Thread.sleep(1000L);
            }
        }
        //8. 提交
        session.commit();
        connection.close();
    }
}
