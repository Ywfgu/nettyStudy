package com.ywfgu.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Stack;

/**
 * @author guht
 * @create 2020/6/4
 * @Description
 */
@RestController
public class SmsDeliverReceiver {
    private static Logger logger = LoggerFactory.getLogger(SmsDeliverReceiver.class);

    @RequestMapping(value="/hello",method= RequestMethod.GET )
    public String sayHello(@RequestParam(value ="name") String name){
        logger.info("hello {}!", name);
        return "ok";
    }

    @RequestMapping(value="/sms/deliver",method= RequestMethod.POST )
    public String smsRec(@RequestParam(value ="msg") String msg){
        logger.info("receive msg ={}", msg);
        return "ok";
    }
}
