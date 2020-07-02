package test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * @author guht
 * @create 2020/5/9
 * @Description
 */
public class ByteBufTest {

    @Test
    public void test(){
        ByteBuf buf = Unpooled.buffer(8);
        byte[] bytes = new byte[4];
        buf.writeInt(1);
        buf.writeInt(2);
        System.out.println(buf.readInt());
        System.out.println(buf.slice(0,4).readBytes(bytes));
        System.out.println(Arrays.toString(bytes));
        System.out.println(Arrays.toString(buf.array()));
    }

    @Test
    public void test2() throws UnsupportedEncodingException {
        String s = "应用程序的最小线程数应该等于可用的处理器核数。如果所有的任务都是计算密集型的，如果所有则创建处理器可用核心数那么多个线程就可以了,所以你好的";
        byte[] gbks = s.getBytes("GBK");
        System.out.println(Arrays.toString(gbks));
        System.out.println(Arrays.toString( Arrays.copyOfRange(gbks, 0, 132)));
        System.out.println(Arrays.toString( Arrays.copyOfRange(gbks, 132, gbks.length)));


    }
}
