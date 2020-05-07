package handler;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author guht
 * @create 2020/5/7
 * @Description
 */
public class ServerHandler extends ChannelDuplexHandler {
    private static final Logger logger = LoggerFactory.getLogger(BusinessHandler.class);
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info(msg.toString());
        ctx.channel().writeAndFlush(msg.toString());
        super.channelRead(ctx, msg);
    }
}
