package com.ywfgu.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author guht
 * @create 2020/6/9
 * @Description
 */
@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... strings) throws Exception {
        System.out.println(">>>  MyCommandLineRunner run");
    }
}
