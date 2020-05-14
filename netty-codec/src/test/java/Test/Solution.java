package Test;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.locks.Lock;

/**
 * @author guht
 * @create 2020/5/11
 * @Description
 */
public class Solution {
    public int majorityElement(int[] nums) {
        if(null == nums || nums.length<0){
            return -1;
        }
        //你可以假设数组是非空的，并且给定的数组总是存在多数元素。
        int length = nums.length;
        double mid = length/2;
        Map<Integer,Integer> map = new HashMap();
        for (int num: nums) {
            if (!map.containsKey(num)){
                map.put(num,1);
            }else {
                int count = map.get(num) +1;
                map.put(num,count);
                if(count>mid) return num;
            }
        }
        return nums[0];
    }

    public int majorityElement2(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }

    /**
     * 寻找多数元素
     * 假设数组是非空的，并且给定的数组总是存在多数元素。
     */
    @Test
    public void Test1(){
        int[] nums = {1,2,1,2,3,1};
        System.out.println(majorityElement2(nums));

    }

    public int rob(int[] nums) {
        if(null == nums){
            return 0;
        }

        if(nums.length == 1){
            return nums[0];
        }else if(nums.length == 2){
            return nums[0]-nums[1]<0? nums[1]: nums[0];
        }else {
            int maxcount = (int)Math.ceil(nums.length*1.0/2);
            int max = 0;
            int now = 0;
            for (int i = maxcount; i > 0; i--) {




            }
            return max;
        }
    }

    /**
     * [2,7,9,3,1,3,5]
     *  2,  9,  1,  5
     *  2,  9,    3
     *  2,    3,  3
     *  2,    3,    5
     *    7,  3,  3
     *    7,  3,    5
     *    7,    1,  5
     */
    @Test
    public void testRob(){
        int count =(int) Math.ceil(7*1.0/2);
        System.out.println(count);
    }


    public int fib(int N) {
        if(N==1){
            return 1;
        }else if(N==0){
            return 0;
        }
        return fib(N-1)+fib(N-2);
    }

    /**
     * 斐波那契数列
     */
    @Test
    public void testFib(){
        System.out.println(fib(2));
        System.out.println(fib(3));
        System.out.println(fib(4));
        System.out.println(fib(5));
        System.out.println(fib(6));
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(null == root){
            return result;
        }
        List<TreeNode> solList = new ArrayList<TreeNode>();
        solList.add(root);
        getTreeList(root,0);

        return result;
    }
    public void getTreeList(TreeNode node,int ceil){
        if(result.size() <= ceil){
            result.add(new ArrayList<>());
        }

        result.get(ceil).add(node.val);
        TreeNode left = node.left;
        TreeNode right = node.right;
        if(null != left){
            getTreeList(left,ceil+1);
        }
        if(null != right){
            getTreeList(right,ceil+1);
        }
    }

    /**
     * 输出二叉树每层的数字集合
     */
    @Test
    public void TestlevelOrder(){
        TreeNode n1 = new TreeNode(3);
        TreeNode n2 = new TreeNode(9);
        TreeNode n3 = new TreeNode(20);
        TreeNode n4 = new TreeNode(15);
        TreeNode n5 = new TreeNode(7);

        n1.left = n2;
        n1.right = n3;

        n3.left = n4;
        n3.right = n5;
        System.out.println(levelOrder(n1));
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null){
            return l2;
        }
        else if (l2 == null){
            return l1;
        }
        else if (l1.val <= l2.val){
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
        else{
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }

    }
//    ListNode start = null;
//    ListNode end = null;
//    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
//
//        if(null == l1){
//            return end == null ? l2: (end.next = l2);
//        }
//
//        if(null == l2){
//            return end == null ? l1: (end.next = l1);
//        }
//
//
//        if(l2.val < l1.val ){
//            if(null == start){
//                start = l2;
//                end = l2;
//            }else {
//                end.next = l2;
//                end = end.next;
//            }
//            l2 = l2.next;
//        }else{
//            if(null == start){
//                start = l1;
//                end = l1;
//            }else{
//                end.next = l1;
//                end = end.next;
//            }
//            l1 = l1.next;
//        }
//        mergeTwoLists(l1,l2);
//        return start;
//    }



    /**
     *  合并两个有序链表
     */
    @Test
    public void testmergeTwoLists(){
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(2);
        ListNode a3 = new ListNode(4);
        ListNode b1 = new ListNode(1);
        ListNode b2 = new ListNode(3);
        ListNode b3 = new ListNode(4);
        a1.next = a2;
        a2.next = a3;
        b1.next = b2;
        b2.next = b3;

        ListNode ln = mergeTwoLists(a1,b1);
        System.out.println(ln);
    }


    public String longestPalindrome(String s) {
        if(null == s || "".equals(s)) return s;
        int offset = 0;
        int endset = 0;
        char arr[] = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            offset = firstindex(arr,arr[i]);
            endset = lastindex(arr,arr[i]);
            if(offset != endset){
                return new String(Arrays.copyOfRange(arr,i,endset+1));
            }
        }
        return new String(Arrays.copyOfRange(arr,0,1));
    }
    public int firstindex(char[] arr,char dest){
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == dest) return i;
        }
        return 0;
    }
    public int lastindex(char[] arr,char dest){
        int start = arr.length-1;
        for (int i = start ; i >= 1 ; i--) {
            if(arr[i] == dest) return i;
        }
        return 0;
    }

    /**
     * 最长回文子串,未完成
     */
    @Test
    public void testlongestPalindrome(){
        System.out.println(longestPalindrome("ac"));
    }


    public int singleNumber(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            int second = -1;
            for (int j = 0; j < nums.length; j++) {
                if(i == j){
                    continue;
                }else if(n == nums[j]){
                    second = j;
                    break;
                }
            }
            if(second ==-1) return n;
        }
        return 0;
    }
    @Test
    public void testsingleNumber(){
        int[] nums = {1,1,2,2,3};
        System.out.println(singleNumber(nums));
    }
}
