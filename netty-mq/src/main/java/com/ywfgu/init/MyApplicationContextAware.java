package com.ywfgu.init;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author guht
 * @create 2020/6/9
 * @Description
 */
@Component
public class MyApplicationContextAware implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println(">>> applicationContext正在初始化,application:"+applicationContext);
        MyApplicationContextAware.applicationContext =applicationContext;
    }

    public static <T> T getBean(Class<T> clazz){
        if(applicationContext==null){
            System.out.println(">>> applicationContext是空的");
        }else{
            System.out.println(">>> applicationContext不是空的");
        }
        return applicationContext.getBean(clazz);
    }

    public static <T> T getBean(String name, Class<T> clazz){
        if(applicationContext==null){
            System.out.println(">>> applicationContext是空的");
        }else{
            System.out.println(">>> applicationContext不是空的");
        }
        return applicationContext.getBean(name, clazz);
    }

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }
}
