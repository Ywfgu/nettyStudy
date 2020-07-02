package com.ywfgu;

import com.ywfgu.init.MySpringBootBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = {"classpath:applicationContext.xml" })
public class NettyMqApplication {

    public static void main(String[] args) {
        System.out.println("NettyMqApplication start");
        SpringApplication application = new SpringApplication(NettyMqApplication.class);
        application.setBanner(new MySpringBootBanner());
        application.run(args);
        System.out.println("NettyMqApplication end");
	}

}
