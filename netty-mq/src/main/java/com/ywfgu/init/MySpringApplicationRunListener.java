package com.ywfgu.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author guht
 * @create 2020/6/9
 * @Description
 */
public class MySpringApplicationRunListener implements SpringApplicationRunListener {
    private static Logger logger = LoggerFactory.getLogger(MySpringApplicationRunListener.class);

    public MySpringApplicationRunListener(SpringApplication application, String[]  args){
        System.out.println(">>>  constructor");
        // 自定义手动设置banner，如果配置了banner.txt则不生效
        application.setBanner(new MySpringBootBanner());
    }

    @Override
    public void starting() {
        System.out.println(">>>  1 starting");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        System.out.println(">>>  2 environmentPrepared");
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        System.out.println(">>>  3 contextPrepared");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        System.out.println(">>>  4 contextLoaded");
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        System.out.println(">>>  5 started");
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        System.out.println(">>>  6 running");
        new InitThread().start();
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        System.out.println(">>>  7 failed");
    }
}
