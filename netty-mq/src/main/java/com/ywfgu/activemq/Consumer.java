package com.ywfgu.activemq;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.BytesMessage;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author guht
 * @create 2020/5/15
 * @Description
 */
@Component
public class Consumer {
    private static Logger logger = LoggerFactory.getLogger(Consumer.class);

    private ConcurrentHashMap<Long,Long> CMap = new ConcurrentHashMap<Long,Long>();


//    @JmsListener(destination = "SPXM_DELIVER_TOPIC", containerFactory = "jmsActiveMQTopicDurableListener", concurrency = "1")
    public void receiveMessage(Message message, Session session) {
        try {
            if(message instanceof BytesMessage){
                BytesMessage ms = (BytesMessage)message;
                byte[] bytes = new byte[(int) ms.getBodyLength()];
                ms.readBytes(bytes);
                logger.info(Arrays.toString(bytes));
            }else{
                TextMessage m = (TextMessage) message;
                logger.info(m.getText());
            }
            message.acknowledge();
        } catch (Exception e) {
//            session.recover();// 此不可省略 重发信息使用
        }
    }

//    @JmsListener(destination = "SPXM_2_MAS_106509712190001", containerFactory = "jmsActiveMQQueueListener", concurrency = "5")
//    public void receiveMessage2(Message message, Session session) {
//        try {
//            if(message instanceof BytesMessage){
//                BytesMessage ms = (BytesMessage)message;
//                byte[] bytes = new byte[(int) ms.getBodyLength()];
//                ms.readBytes(bytes);
//                logger.info(Arrays.toString(bytes));
//            }
//            message.acknowledge();// 使用手动签收模式，需要手动的调用，如果不在catch中调用session.recover()消息只会在重启服务后重发
//        } catch (Exception e) {
////            session.recover();// 此不可省略 重发信息使用
//        }
//    }

//    @JmsListener(destination = "CMPP_2_SPXM_1", containerFactory = "jmsActiveMQQueueListener", concurrency = "10")
//    public void receiveMessage3(Message message, Session session) {
//        try {
//            if(message instanceof BytesMessage){
//                BytesMessage ms = (BytesMessage)message;
//                byte[] bytes = new byte[(int) ms.getBodyLength()];
//                ms.readBytes(bytes);
//                logger.info(Arrays.toString(bytes));
//            }
//            message.acknowledge();// 使用手动签收模式，需要手动的调用，如果不在catch中调用session.recover()消息只会在重启服务后重发
//        } catch (Exception e) {
////            session.recover();// 此不可省略 重发信息使用
//        }
//    }

//    @JmsListener(id="testTopic_clientId1.testTopic_clientId1",subscription = "testTopic_clientId2.testTopic_clientId2",destination = "USER_TOPIC2", containerFactory = "jmsActiveMQQueueListener", concurrency = "10")
//    public void receiveMessage3(Message message, Session session) {
//        try {
//            logger.info("RECEIVE MESSAGE={}",((TextMessage)message).getText());
//            message.acknowledge();// 使用手动签收模式，需要手动的调用，如果不在catch中调用session.recover()消息只会在重启服务后重发
//        } catch (Exception e) {
//    //            session.recover();// 此不可省略 重发信息使用
//        }
//    }
}
