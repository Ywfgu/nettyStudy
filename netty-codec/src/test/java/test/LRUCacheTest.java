package test;

import org.junit.Test;

/**
 * @author guht
 * @create 2020/5/11
 * @Description
 */
public class LRUCacheTest {

    @Test
    public void TestLRU(){
        LRUCacheOfficial cache = new LRUCacheOfficial(1);
        cache.put(1, 1);
        cache.put(2, 2);
//        cache.get(1);       // 返回  1
//        cache.put(3, 3);    // 该操作会使得密钥 2 作废
//        cache.get(2);       // 返回 -1 (未找到)
//        cache.put(4, 4);    // 该操作会使得密钥 1 作废
//        cache.get(1);       // 返回 -1 (未找到)
//        cache.get(3);       // 返回  3
//        cache.get(4);       // 返回  4

    }
}
