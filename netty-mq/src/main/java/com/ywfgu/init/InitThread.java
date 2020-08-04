package com.ywfgu.init;

import com.ywfgu.common.SpringUtils;
import com.ywfgu.control.SmsDeliverReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * @author guht
 * @create 2020/6/15
 * @Description
 */
@Component
public class InitThread extends Thread {

    @Autowired
    private JmsTemplate jmsActiveMQQueueTemplate;

    @Override
    public void run() {
        SmsDeliverReceiver sdr = (SmsDeliverReceiver)MyApplicationContextAware.getApplicationContext().getBean(SmsDeliverReceiver.class);
        System.out.println(sdr.sayHello("ght"));
        System.out.println("ok!!!!");

//        jmsActiveMQQueueTemplate = MyApplicationContextAware.getBean("jmsActiveMQQueueTemplate" ,JmsTemplate.class);
//        jmsActiveMQQueueTemplate.convertAndSend("TEST_SELECTOR", "hello");
    }
}
