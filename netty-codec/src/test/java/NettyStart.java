import handler.BusinessHandler;
import handler.ClientHandler;
import handler.ServerHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.junit.Test;

/**
 * @author guht
 * @create 2020/5/6
 * @Description
 */
public class NettyStart {

    @Test
    public void testServer() throws Exception {
        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup work = new NioEventLoopGroup(1);

        final ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss,work)
                .channel(NioServerSocketChannel.class)
                // Socket参数，服务端接受连接的队列长度，如果队列已满，客户端连接将被拒绝
                .option(ChannelOption.SO_BACKLOG, 100)
                //Socket参数，TCP数据接受缓冲区大小。该缓冲区即TCP发送滑动窗口
                .option(ChannelOption.SO_RCVBUF, 2048)
                //Socket参数，TCP数据发送缓冲区大小。该缓冲区即TCP发送滑动窗口
                .option(ChannelOption.SO_SNDBUF, 2048)
                // TCP参数，立即发送数据，默认值为Ture
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new StringEncoder());
                        pipeline.addLast(new StringDecoder());
                        pipeline.addLast(new ServerHandler());
                    }
                });

        ChannelFuture channelFuture = bootstrap.bind(9009).sync();

//        new Thread(()->{
//            System.out.println("即将关闭channel");
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            channelFuture.channel().close();
//        }).start();
        ChannelFuture closeFuture=  channelFuture.channel().closeFuture();
        closeFuture.sync();

        boss.shutdownGracefully();
        work.shutdownGracefully();
    }

    @Test
    public void testClient() throws Exception {
        EventLoopGroup work = new NioEventLoopGroup(1);

        final Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(work)
                .channel(NioSocketChannel.class)
                //Socket参数，TCP数据接受缓冲区大小。该缓冲区即TCP发送滑动窗口
                .option(ChannelOption.SO_RCVBUF, 2048)
                //Socket参数，TCP数据发送缓冲区大小。该缓冲区即TCP发送滑动窗口
                .option(ChannelOption.SO_SNDBUF, 2048)
                // TCP参数，立即发送数据，默认值为Ture
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .option(ChannelOption.RCVBUF_ALLOCATOR,new FixedRecvByteBufAllocator(1024))
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new StringEncoder());
                        pipeline.addLast(new StringDecoder());
                        pipeline.addLast(new ClientHandler());
                    }
                });

        ChannelFuture channelFuture = bootstrap.connect("localhost",9009).sync();
        ChannelFuture closeFuture=  channelFuture.channel().closeFuture();
        closeFuture.sync();

        work.shutdownGracefully();
    }
}
