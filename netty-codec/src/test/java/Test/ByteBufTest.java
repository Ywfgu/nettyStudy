package Test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.junit.Test;

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
}
