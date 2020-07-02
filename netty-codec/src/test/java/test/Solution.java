package test;

import com.sun.deploy.util.StringUtils;
import org.junit.Test;

import java.util.*;

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

    public String serialize(TreeNode root) {
        List<String> res = new ArrayList<>();
        LinkedList<TreeNode> deque = new LinkedList<>();
        deque.addLast(root);
        while (!deque.isEmpty()){
            TreeNode node = deque.pop();
            if(node != null){

                res.add(String.valueOf(node.val));

                deque.addLast(node.left);

                deque.addLast(node.right);
            }else{
                res.add("null");
            }
        }
        return Arrays.toString(res.toArray());
    }
    public TreeNode deserialize(String data) {
        String[] sarr = data.substring(1, data.length()-1).replaceAll(" ","").split(",");
        if(sarr[0].isEmpty()) return null;
        LinkedList<TreeNode> deque = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.valueOf(sarr[0]));
        deque.addLast(root);
        int i = 1;
        while (!deque.isEmpty()){
            TreeNode node = deque.pop();
            if(node != null){
                if(!Objects.equals(sarr[i], "null")){
                    node.left = new TreeNode(Integer.valueOf(sarr[i]));
                }
                if(!Objects.equals(sarr[i+1], "null")){
                    node.right = new TreeNode(Integer.valueOf(sarr[i+1]));
                }
                i += 2;
                deque.addLast(node.left);
                deque.addLast(node.right);
            }
        }
        return root;
    }

    /**
     * 输出二叉树每层的数字集合
     */
    @Test
    public void TestlevelOrder(){
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);

        n1.left = n2;
        n1.right = n3;

        n3.left = n4;
        n3.right = n5;
//        System.out.println(levelOrder(n1));
        String s = serialize(n1);
        TreeNode deserialize = deserialize("[]");
        System.out.println("ok");
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

    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int i = 1; i <= nums.length; i++) {
            //获取固定长度的可能性
            count = count + getCountByLeng(nums,i, k);
        }
        return count;
    }
    public int getCountByLeng(int[] nums, int length , int k){
        int count = 0;
        for (int i = 0; i <= nums.length - length; i++) {
            if(getCountStartEnd(nums,i,i+length) ==k ){
                count++;
            }
        }
        System.out.println("长度为"+length+"的有"+count+"种可能");
        return count;
    }
    public int getCountStartEnd(int[] nums, int start, int end){
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if(i>= start && i<= end){
                sum += nums[i];
            }
        }
        return sum;
    }
    @Test
    public void testsubarraySum(){
        int[] nums = {1,2,3};
        System.out.println(subarraySum(nums,3));
    }

    public boolean validPalindrome(String s) {

        int head = 0;
        int tail = s.length()-1;
        while (head < tail){
            if(s.charAt(head) == s.charAt(tail)){
                head++;
                tail--;
            }else {
                boolean f1 =true;
                boolean f2 =true;
                for (int i = head+1,j = tail; i < j; i++,j--) {
                    if(s.charAt(i) != s.charAt(j)){
                        f1 = false;break;
                    }
                }
                for (int i = head,j = tail-1; i < j; i++,j--) {
                    if(s.charAt(i) != s.charAt(j)){
                        f2 = false;break;
                    }
                }
                return f1 || f2;

            }

        }
        return true;
    }

    /**
     * 是否回文字符串
     */
    @Test
    public void TestvalidPalindrome() {
        System.out.println(validPalindrome("eedede"));
    }

    public double myPow(double x, int n) {
        long N = n;
        double res = 1.0;
        if(x == 1.0){
            return x;
        }
        long count = N;
        double X = x;
        if(N<0){
            count = -N;
            X = 1/x;
        }
        for(long i=0; i<count; i++){
            res = res*X;
        }
        return res;
    }
    @Test
    public void TestmyPow(){
        System.out.println(myPow(2.000,-2147483648));
    }


    public int rob2(int[] nums) {
        int n = nums.length;
        if(n == 0){
            return 0;
        }
        if(n == 1){
            return nums[0];
        }

        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for(int i =2; i < n; i++){
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
        }
        return dp[n-1];
    }

    @Test
    public void testrob2(){
        rob2(new int[]{1,2,3,3});
    }


    public ListNode partition(ListNode head, int x) {
        ListNode before = new ListNode(-1);
        ListNode beforelast = before;
        ListNode after = new ListNode(-1);
        ListNode afterlast = after;

        ListNode pre = head;
        while(pre != null){
            if(pre.val < x){
                beforelast.next = pre;
                beforelast = beforelast.next;
                beforelast.next = null;
            }else{
                afterlast.next = pre;
                afterlast = afterlast.next;
                afterlast.next = null;
            }
            pre = pre.next;
        }

        beforelast.next = after.next;
        return before.next;
    }

    @Test
    public void testpartition(){
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(4);
        ListNode a3 = new ListNode(3);
        ListNode b1 = new ListNode(2);
        ListNode b2 = new ListNode(5);
        ListNode b3 = new ListNode(2);
        a1.next = a2;
        a2.next = a3;
        a3.next = b1;
        b1.next = b2;
        b2.next = b3;
        ListNode node = partition(a1,3);
        System.out.println(node);
    }

    public boolean isPalindrome(int x) {
//        String s = String.valueOf(x);
//
//        int i =0;
//        int j = s.length()-1;
//
//        while( i < j){
//            if(s.charAt(i) != s.charAt(j)) return false;
//            i++;
//            j--;
//        }
//        return true;
        if(x < 0 ) return false;

        int y=0;
        int cal = x;
        while(cal > 0){
            y = y*10 + cal%10;
            cal = cal/10;
        }
        return x == y;
    }

    @Test
    public void testisPalindrome(){
        isPalindrome(1221);
    }


    public void BrowserHistory(){
        Stack<String> history = new Stack();
        history.push("a");
        history.push("b");
        history.push("c");
    }

}
