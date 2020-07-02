package test;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @author guht
 * @create 2020/5/7
 * @Description
 */
public class CommonTest {

    @Test
    public void testInt(){
//        System.out.println(2/10);
        System.out.println(isHappy(19));
        isHappy2(19);

    }

    public int squareSum(int n) {
        int sum = 0;
        while(n > 0){
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }

    public boolean isHappy(int n) {
        int slow = n, fast = squareSum(n);
        while (slow != fast){
            slow = squareSum(slow);
            fast = squareSum(squareSum(fast));
            System.out.println("slow:"+slow+",fast:"+fast);
        };
        return slow == 1;
    }
    public boolean isHappy2(int n) {
        Set set = new HashSet();
        while(n != 1 && !set.contains(n)){
            set.add(n);
            int temp = 0;
            int c ;
            while(n>0){
                c = n%10;
                temp += c*c;
                n /= 10;
            }
            n = temp;
            System.out.println(n);
        }
        return n == 1;
    }

    public static final int LEN = 2;
    @Test
    public void byteToUnsignedBytes(){

        byte input = (byte) 255; // -2 (有符号)  254 (无符号)
        System.out.println(input);                              // -2
        System.out.println(LEN + input & 0xFF); // 254
        // Java 8
        System.out.println(Byte.toUnsignedInt(input));
    }

    @Test
    public void bytesToBean() throws Exception {
        byte[] bytes = new byte[]{86, 36, -6, 64, 39, 28, 118, -42, 67, 77, 58, 48, 49, 52, 50, 50, 48, 48, 53, 49, 50, 48, 57, 49, 53, 50, 48, 48, 53, 49, 50, 48, 57, 49, 53, 49, 56, 55, 53, 56, 53, 53, 49, 49, 48, 53, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
//        bytes = new byte[]{79,96,89,125};
        InputStream in = new ByteArrayInputStream(bytes);
        DataInputStream dis = new DataInputStream(in);
//        System.out.println(getStringByByte(4, dis));
        System.out.println(dis.readLong());
        System.out.println(getStringByByte(7, dis));
        System.out.println(getStringByByte(10, dis));
        System.out.println(getStringByByte(10, dis));
        System.out.println(getStringByByte(32, dis));
        System.out.println(dis.readInt());

    }
    public static String getStringByByte(int len, DataInputStream dis) throws Exception {
        byte[] bytes = new byte[len];
        dis.readFully(bytes);
        return new String(bytes).trim();
    }

    @Test
    public void testNewString() {
        byte[] bytes = new byte[]{79,96,89,125};
        System.out.println(new String(bytes, Charset.forName("UTF_16BE")));
    }

    @Test
    public void testAliChallenge(){
        float a = 0.125f; double b = 0.125d;
        System.out.println((a - b) == 0.0);

        double c = 0.8; double d = 0.7; double e = 0.6;
        System.out.println( (c-d) == (d-e));

        System.out.println(1.0 / 0);

        System.out.println(0.0 / 0.0);

        Object obj = null;
        F f = new F(1);
        System.out.println("");


        Map m = new HashMap();
        m.put(1,2);
    }

    class F{
        Double d;
        String s;
        Integer i;
        Object o;
        public F(Double d) {
            this.d = d;
        }
        public F(String s) {
            this.s = s;
        }
        public F(Integer i) {
            this.i = i;
        }

        public F(Object o) {
            this.o = o;
        }
    }
    <String, T, Alibaba> String get(String string, T t) { return string; }


    @Test
    public void test(){
        String s = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome(s));
    }

    public boolean isPalindrome(String s) {
        //int length = s.length();
        //if(length <= 1) return true;
        //s = s.toLowerCase();
        int left = 0;
        int right = s.length()-1;
        while(left < right){
            while(left < right
                    && !((s.charAt(left) >= 'a' && s.charAt(left) <= 'z')
                    //|| (s.charAt(left) >= 'A' && s.charAt(left) <= 'Z')
                    || (s.charAt(left) >= '0' && s.charAt(left) <= '9'))){
                left ++;
            }
            while(left < right
                    && !((s.charAt(right) >= 'a' && s.charAt(right) <= 'z')
                    //|| (s.charAt(right) >= 'A' && s.charAt(right) <= 'Z')
                    || (s.charAt(right) >= '0' && s.charAt(right) <= '9'))){
                right --;
            }
            //System.out.println(s.charAt(left) +" compare to "+ s.charAt(right));
            if(left < right
                    && Math.abs(s.charAt(left)- s.charAt(right)) == 32 ){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


}
