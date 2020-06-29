package test;

import org.junit.Test;

import java.util.*;

/**
 * @author guht
 * @create 2020/6/2
 * @Description
 */
public class Offer {

    public int findRepeatNumber(int[] nums) {
        HashMap map = new HashMap();
        for(int i=0; i<nums.length; i++){
            if(!map.containsKey(nums[i])){
                map.put(nums[i],1);
            }else{
                return nums[i];
            }
        }
        return -1;
    }

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        for (int i = 0; i < matrix.length; i++) {
            int[] up = matrix[i];
            for (int j = 0; j < up.length; j++) {
                if(up[j] == target){
                    return true;
                }
            }
        }
        return false;
    }

    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if( ' ' == chars[i] ){
                sb.append("%20");
            }else{
                sb.append(chars[i]);
            }
        }
        return sb.toString();
    }
    @Test
    public void testReplace(){
        replaceSpace("We are happy.");
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
    public int[] reversePrint(ListNode head) {
        ListNode cur = head;

        int length=0;
        while(cur != null){
            length ++;
            cur = cur.next;
        }
        int[] re = new int[length];
        cur = head;

        for (int i = length -1; i >= 0 ; i--) {
            re[i] = cur.val;
            cur = cur.next;
        }
        return re;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    HashMap<Integer, Integer> dic = new HashMap<>();
    int[] po;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        po = preorder;
        for(int i = 0; i < inorder.length; i++)
            dic.put(inorder[i], i);
        return recur(0, 0, inorder.length - 1);
    }
    TreeNode recur(int pre_root, int in_left, int in_right) {
        if(in_left > in_right) return null;
        TreeNode root = new TreeNode(po[pre_root]);
        int i = dic.get(po[pre_root]);
        root.left = recur(pre_root + 1, in_left, i - 1);
        root.right = recur(pre_root + i - in_left + 1, i + 1, in_right);
        return root;
    }



    LinkedList<Integer> in = new LinkedList();
    LinkedList<Integer> out = new LinkedList();
    int size = 0;
    boolean flag = true;

    public void appendTail(int value) {
        in.push(value);
        size++;
    }

    public int deleteHead() {
        if(size == 0) return -1;

        if(out.isEmpty()){
            while (!in.isEmpty()){
                out.push(in.pop());
            }
        }
        size --;
        return out.pop();
    }

    @Test
    public void testCQueue(){
        System.out.println(deleteHead());
        appendTail(12);
        System.out.println(deleteHead());
        appendTail(10);
        appendTail(9);
        System.out.println(deleteHead());
        System.out.println(deleteHead());
        System.out.println(deleteHead());

    }

    public double myPow(double x, int n) {
        if(x == 0.0) return 0.0;

        if(n > 0){
            return x * myPow(x, n-1);
        }else if(n < 0){
            return 1/x * myPow(x, n+1);
        }else if(n == 0){
            return 1.0;
        }
        return 0.0;
    }

    public double myPow2(double x, int n) {
        if(x == 0) return 0;
        long b = n;
        double res = 1.0;
        if(b < 0) {
            x = 1 / x;
            b = -b;
        }
        while(b > 0) {
            if((b & 1) == 1) res *= x;
            x *= x;
            b >>= 1;
        }
        return res;

    }

    public ListNode deleteNode(ListNode head, int val) {
        if(head.val == val) return head.next;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        while(cur != null && cur.next != null){
            if(cur.next.val == val){
                cur.next = cur.next.next;
            }else{
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    public ListNode reverseList(ListNode head) {
        if(head.next == null) return head;

        ListNode cur = head;

        while (cur != null){
            if(cur.next != null){
                ListNode temp = cur.next;
                cur.next = cur.next.next;
                temp.next = cur;
                head = temp;
            }else{
                cur = cur.next;
            }
        }

        return head;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        while ( l1 != null && l2 != null){
            if(l1.val <= l2.val){
                cur.next = l1;
                l1 = l1.next;
            }else if (l1.val > l2.val){
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        if( l1 == null ) cur.next = l2;
        if( l2 == null ) cur.next = l1;

        return dummy.next;
    }

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack();

        int i=0;
        for(int j=0; j< pushed.length; j++){
            stack.push(pushed[j]);

            while(stack.size()>0 && stack.peek() == popped[i]){
                stack.pop();
                i++;
            }
        }


        return stack.size()==0;
    }

    public int countDigitOne(int n) {
        int res=0;
        String s;

        for(int i=1; i<=n; i++){
            s = String.valueOf(i);
            int temp = 0;
            for (char c : s.toCharArray()) {
                if(c == '1'){
                    temp++;
                }
            }

            res += temp;
        }

        return res;
    }

    @Test
    public void test(){
//        long sum1=0, sum2=0;
//        long i = 1, j = 1;
//        while(l1 != null){
//            sum1 += (i * l1.val);
//            i *= 10;
//            l1 = l1.next;
//        }
//        while(l2 != null){
//            sum2 += (j * l2.val);
//            j *= 10;
//            l2 = l2.next;
//        }
//        long total = sum1 + sum2;
//        ListNode res = new ListNode(0);
//        ListNode pre = res;
//        i = 10;
//        if(total != 0){
//            while(total > 0){
//                long a = total % i;
//                total = total / i;
//                pre.next = new ListNode((int)a);
//                pre = pre.next;
//            }
//        }else{
//            pre.next = new ListNode(0);
//        }
//        return res.next;
//        Stack<Integer> s1 = new Stack<Integer>();
//        Stack<Integer> s2 = new Stack<Integer>();
//
//        while(l1 != null){
//            s1.push(l1);
//            l1 = l1.next;
//        }
//        while(l2 != null){
//            s2.push(l2);
//            l2 = l2.next;
//        }
//        ListNode res = new ListNode(0);
//        ListNode pre = res;
//        while(!s1.empty() && !s2.empty()){
//            pre.next = new ListNode(s1.pop()+s2.pop());
//            pre = pre.next;
//        }
//        while(!s1.empty()){
//            pre.next = new ListNode(s1.pop());
//            pre = pre.next;
//        }
//        if(!s2.empty()){
//            pre.next = new ListNode(s2.pop());
//            pre = pre.next;
//        }
//        return res.next;
    }


    @Test
    public void testQuickSort(){
        int[] arr = {5,4,3,2,1};
        quickSort(arr, 0, arr.length-1);
        System.out.println(arr);
    }
    public void quickSort(int[] arr, int left, int right){
        if( left >= right) return;

        int par = arr[left];
        int l = left;
        int r = right;

        while (l < r){

            while (l<r && arr[r]>par){
                r--;
            }
            while (l<r && arr[l] <=par){
                l++;
            }

            if(l < r) swap(arr, l, r);
        }
        swap(arr, left, l);

        quickSort(arr, left, l-1);
        quickSort(arr, l+1, right);

    }

    public void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }




}
