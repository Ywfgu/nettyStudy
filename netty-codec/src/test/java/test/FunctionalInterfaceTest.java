package test;

import javafx.util.Pair;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author guht
 * @create 2020/6/16
 * @Description
 */
public class FunctionalInterfaceTest {

    class pr implements Predicate<Integer> {
        @Override
        public boolean test(Integer integer) {
            return integer.intValue() > 3;
        }
    }

    class Student {
                int id;
        String name;
        int age;


        public Student(int id) {
            this.id = id;
        }
        public int getId() {
            return id;
        }
        public String getName() {
            return name;
        }
        public int getAge() {
            return age;
        }
    }

    @Test
    public void test(){
        List<Integer> list = Arrays.asList(5,3,1,2,3,4);
//        List<String> list = Arrays.asList("zhangsan","lisi","wangwu","xiaoming","zhaoliu");
        list.stream()
                .map(value -> value+1)
                .filter(value -> value>1)
                .filter(new pr())
                .sorted()
                .forEach(value -> System.out.println(value));

        List<Pair<String, Double>> pairArrayList = new ArrayList<>(3);
        pairArrayList.add(new Pair<>("version", 6.19));
        pairArrayList.add(new Pair<>("version", 10.24));
        pairArrayList.add(new Pair<>("version", 13.14));
        Map<String, Double> map = pairArrayList.stream().collect(
        // 生成的 map 集合中只有一个键值对：{version=13.14}
        Collectors.toMap(Pair::getKey, Pair::getValue, (v1, v2) -> v2));

        List<Student> lists = Arrays.asList(new Student(1), new Student(4), new Student(2));
        lists.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getId() > o2.getId() ? 1 : -1; // 未考虑两个元素的自反性，需考虑相等的情况
            }
        });


        HashMap cycle = new HashMap();
        cycle.put("a", 2);
        cycle.put("b", 1);
        cycle.put("c", 7);
        cycle.put("d", 4);
        cycle.forEach((key, value)-> System.out.println(key+" "+ value));

        Student stu = new Student(666);
//        tl.set(stu);
        FunctionalInterfaceTest fit = new FunctionalInterfaceTest();

        CountDownLatch cdl = new CountDownLatch(1);
        new Thread(()->{
            try {
                cdl.await();
                System.out.println("ok1"+fit.getString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1").start();
        new Thread(()->{
            try {
                cdl.await();
                tl.get();
                System.out.println("ok2"+fit.getString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2").start();
        cdl.countDown();
    }
    static ThreadLocal tl = new ThreadLocal();
    ThreadLocal<String> stringLocal = new ThreadLocal<String>(){;
        protected String initialValue() {
            return Thread.currentThread().getName();
        }
    };
    public String getString() {
        return stringLocal.get();
    }

    public static void main(String[] args) {
        System.out.println("hello blog");
    }




}
