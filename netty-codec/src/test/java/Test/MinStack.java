package Test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * @author guht
 * @create 2020/5/12
 * @Description 155. 最小栈
 */
public class MinStack {

    class node{
        Integer value=null;
        node next=null;

        public node() {}
        public node(int x) {
            value = x;
        }
    }

    private List<Integer> cache = new ArrayList<Integer>();
    node top;
    node min;

    public MinStack() {
        top = new node();
        top.next = top;
        min.next = top;
    }

    public void push(int x) {
        node n = new node(x);

        node nowtop = top.next;
        n.next = nowtop;
        n.value = x;
        top.next = n;

        cache.add(x);
        if(null == min || n.value < min.value){
            min.next = n;
        }else {
            min.next = min;
        }
    }

    public void pop() {
        node nowtop = top.next;
        top.next = nowtop.next;
        cache.remove(cache.size()-1);
    }

    public int top() {
        return top.next.value;
    }

    public int getMin() {
        return min.value;
    }

    @Test
    public void TestMinStack(){
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());

    }
}
