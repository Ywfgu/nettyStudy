package com.ywfgu.init;

import com.ywfgu.control.SmsDeliverReceiver;
import org.springframework.stereotype.Component;

/**
 * @author guht
 * @create 2020/6/15
 * @Description
 */
@Component
public class InitThread extends Thread {
    @Override
    public void run() {
        SmsDeliverReceiver sdr = (SmsDeliverReceiver)MyApplicationContextAware.getApplicationContext().getBean(SmsDeliverReceiver.class);
        System.out.println(sdr.sayHello("ght"));
        System.out.println("ok!!!!");
    }
}
