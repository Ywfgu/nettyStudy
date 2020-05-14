package Test;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author guht
 * @create 2020/5/8
 * @Description
 */
public class LRUCache {

    private LinkedHashMap linkedHashMap = new LinkedHashMap();
    private LinkedList<Integer> linkedList = new LinkedList<Integer>();
    private static Map<Integer,Integer> HashMap = new HashMap<Integer,Integer>();
    int limit = -1;
    public LRUCache(int capacity) {
        limit = capacity;
    }

    public int get(int key) {
        //TODO 如果存在返回值，并将值放在队列尾
        if(HashMap.containsKey(key)){
            linkedList.remove(linkedList.indexOf(key));
            linkedList.addLast(key);
            return HashMap.get(key);
        }
        //TODO 如果不存在返回-1
        return -1;
    }

    public void put(int key, int value) {
        //TODO 检查是否存在，存在更新
        if(HashMap.containsKey(key)){
            HashMap.put(key,value);
            linkedList.remove(linkedList.indexOf(key));
            linkedList.addLast(key);
            return;
        }
        //TODO 检查队列是否超过limit,超过则删除一个队头上的元素,不超过添加到队列尾部
        if(linkedList.size()==limit){
            HashMap.remove(linkedList.getFirst());
            linkedList.removeFirst();
        }
        linkedList.addLast(key);
        HashMap.put(key,value);
    }

    public static void main(String[] args) {

        LRUCache cache = new LRUCache( 1 /* 缓存容量 */ );
        cache.put(2, 1);
        System.out.println(cache.get(2));
        cache.put(2, 2);// 返回  1
//        cache.put(3, 3);    // 该操作会使得密钥 2 作废
//        System.out.println(cache.get(2));       // 返回 -1 (未找到)
//        cache.put(4, 4);    // 该操作会使得密钥 1 作废
//        System.out.println(cache.get(1));       // 返回 -1 (未找到)
//        System.out.println(cache.get(3));       // 返回  3
//        System.out.println(cache.get(4));       // 返回  4
    }
    @Test
    public void test(){


    }
}
