package handler;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import static java.util.concurrent.TimeUnit.*;

/**
 * @author guht
 * @create 2020/5/7
 * @Description
 */
public class ClientHandler extends ChannelDuplexHandler {
    private static final Logger logger = LoggerFactory.getLogger(BusinessHandler.class);
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("client receive:{}",msg.toString());
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        AtomicLong al = new AtomicLong(1);
        new Thread(()->{
            while (true){
                ctx.channel().writeAndFlush("hello netty!"+al.getAndIncrement());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        super.channelActive(ctx);
    }
}
