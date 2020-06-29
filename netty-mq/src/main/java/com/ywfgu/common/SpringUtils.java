package com.ywfgu.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

/**
 * @author guht
 * @create 2020/6/15
 * @Description
 */
public class SpringUtils {
    private volatile static ApplicationContext applicationContext = null;

    public static void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringUtils.applicationContext == null) {
            SpringUtils.applicationContext = applicationContext;
            System.out.println("SpringUtils setApplicationContext...........");
        }
    }
}
