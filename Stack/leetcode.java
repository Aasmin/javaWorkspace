import java.util.Stack;
import java.util.Arrays;
import java.util.Scanner;

public class leetcode {
    //Leetcode 20. Valid Parentheses
    public static boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(ch == ']' && !st.isEmpty() && st.peek() == '[')   st.pop();
            else if(ch == '}' && !st.isEmpty() && st.peek() == '{')   st.pop();
            else if(ch == ')' && !st.isEmpty() && st.peek() == '(')   st.pop();
            else if(ch == '(' || ch == '{' || ch == '[')    st.push(ch); 
            else return false;
        }
        if(!st.isEmpty())   return false;
        return true;
    }

    //Leetcode 1021. Remove Outermost Parentheses
    public static String removeOuterParentheses(String S) {
        Stack<Character> st = new Stack<>();
        String ans = "";
        for(int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            if(st.size() == 1 && ch == ')')    st.pop();
            else if(st.size() >= 1){  
                ans += ch;
                if(ch == '(')   st.push(ch);
                else st.pop();
            }
            else st.push(ch);
        }
        return ans;
    }

    //next Greater To The Right
    public static int[] ngtor(int[] nums) {
        Stack<Integer> st = new Stack<>();
        st.push(0);
        int[] ngr = new int[nums.length];
        Arrays.fill(ngr, -1);  

        for(int i = 0; i < nums.length; i++) {
            while(!st.isEmpty() && nums[i] >= nums[st.peek()])  //next smaller replace '>' to '<'
                ngr[st.pop()] = nums[i];
            st.push(i);
        }
        return ngr;
    }
    
    //next Greater To The Left [SAME AS ABOVE MINOR CHANGES ]
    public static int[] ngtol(int[] nums) {
        Stack<Integer> st = new Stack<>();
        st.push(nums.length - 1);   //CHANGED THE DIRECTION SO NEED TO INSERT FROM RIGHT
        int[] ngr = new int[nums.length];   
        Arrays.fill(ngr, -1);  

        for(int i = nums.length - 1; i > 0; i--) {     //CHANGED THE LOOP DIRECTION
            while(!st.isEmpty() && nums[i] >= nums[st.peek()])  
                ngr[st.pop()] = nums[i];
            st.push(i);
        }
        return ngr;
    }

    //Leetcode 503. Next Greater Element II //[SAME AS ngor MINOR CHANGES ]
    public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> st = new Stack<>();
        int[] ngr = new int[nums.length];
        Arrays.fill(ngr, -1);  
        int n = nums.length;
        for(int i = 0; i < n * 2; i++) {    //runs two times
            while(!st.isEmpty() && nums[i % n] > nums[st.peek()])  
                ngr[st.pop()] = nums[i % n];
            if(i < n) st.push(i % n);
        }
        return ngr;
    }

    //Leetcode 901. Online Stock Span
    class StockSpanner {
        Stack<int[]> stk = new Stack<>();
        int i;  //index
    
        public StockSpanner() {
            stk.push(new int[]{-1, -1});    //{index, price}
            i = 0;
        }
        
        public int next(int price) {    
            int width = 1;
            while(stk.peek()[0] != -1 && price >= stk.peek()[1])
                stk.pop();
            width = i - stk.peek()[0];
            stk.push(new int[]{i, price});
            i++;    //increase the index
            return width;
        }
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
        
    }

    //Leetcode 1171. Remove Zero Sum Consecutive Nodes from Linked List
    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode dummyH = new ListNode(0, head);
        ListNode curr = dummyH;
        while(curr != null) {
            int sum = 0;
            while(head != null) {
                sum += head.val;
                if(sum == 0)    curr.next = head.next;
                head = head.next;
            }
            curr = curr.next;
            if(curr != null)    head = curr.next;
        }
        return dummyH.next;
    }


    public static String removeOuterParentheses02(String   S) {   //without using stack
        String ans = "";
        int count = 0;
        for(int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            if(ch == '(' && count++ > 0)  ans += ch;
            if(ch == ')' && count-- > 1)  ans += ch;
        }
        return ans;
    }

    //Leetcode 921. Minimum Add to Make Parentheses Valid
    public int minAddToMakeValid(String S) {
        Stack<Character> st = new Stack<>();
        int count = 0;
        for(int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            if(ch == ')') {
                if(st.empty())  count ++;
                else if (st.peek() == '(') 
                    st.pop();
            } else {
                st.push(ch);
            }
        }
        count += st.size();
        return count;
    }

    public int minAddToMakeValid02(String S) {
        int openingBracketReq = 0;
        int closingBracketReq = 0;
        for(int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            if(ch == '(') closingBracketReq++;
            else if(closingBracketReq > 0)  closingBracketReq--;    // as opening bracket already found 
            else openingBracketReq++;   //No opening bracket

        }
        return openingBracketReq + closingBracketReq;
    }

    //Leetcode 1249. Minimum Remove to Make Valid Parentheses
    public String minRemoveToMakeValid(String s) {
        int length = s.length();
        boolean[] marked = new boolean[length];
        Stack<Integer> st = new Stack<>();  //Store the indexes

        for(int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if(ch == ')')
                if(st.empty())  marked[i] = false;
                else    
                    marked[i] = marked[st.pop()] = true;
            else if(ch == '(')
                st.push(i);
            else
                marked[i] = true;   //alphabets
        }

        String ans = "";
        for(int i = 0; i < length; i++) {
            if(marked[i])   ans += s.charAt(i);
        }
        return ans;
    }

    //Leetcode 32. Longest Valid Parentheses
    public int longestValidParentheses(String s) {
        Stack<Integer> st = new Stack<>();
        int ans = 0;
        st.push(-1);
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(st.peek() == -1 && ch == ')' && s.charAt(st.peek()) == ')') {
                st.pop();
                ans = Math.max(ans, i - st.peek());
            } else
                st.push(i);
        }

        for(int ele : st) {
             
        }
        return ans;
    }

    //Leetcode 735. Asteroid Collision  
    //TestCase: [5,10,-5]
    public int[] asteroidCollision(int[] asteroids) {
        int length = asteroids.length;
        boolean[] marked = new boolean[length];
        Arrays.fill(marked, true);
        int countFalse = 0;
        Stack<Integer> st = new Stack<>();  //Store the indexes
        for(int i = 0; i < length; i++) {
            int ele = asteroids[i];     //in-hand
            
            while(!st.empty() && ele < 0)  {
                countFalse++;
                if(-(ele) >= asteroids[st.peek()]) 
                    marked[st.pop()] = false;

                else {
                    marked[i] = false; 
                    break;  
                }
            } 

            if(ele > 0)   st.push(i);   //Store the indexes
        }

        int[] ans = new int[length - countFalse];
        int idx = 0;
        for(int i = 0; i < length; i++) {
            if(marked[i])   {
                ans[idx] = asteroids[i];
                idx++;
            }
        }
        return ans;
    }

    //Leetcode 84. Largest Rectangle in Histogram
    public int largestRectangleArea(int[] heights) {
        if(heights.length == 0) return 0;
        if(heights.length == 1) return heights[0];
        int[] lb = new int[heights.length];
        Stack<Integer> st1 = new Stack<>();
        st1.push(0);
        lb[0] = -1;
        for(int i = 0; i < heights.length; i++) {
            while(!st1.isEmpty() && heights[i] <= heights[st1.peek()])
                st1.pop();
            
            if(!st1.isEmpty())   lb[i] = st1.peek();
            else lb[i] = -1;

            st1.push(i);
        }
        
        
        int[] rb = new int[heights.length];     // next smaller right
        Stack<Integer> st = new Stack<>();
        st.push(heights.length - 1);
        rb[heights.length - 1] = heights.length;
        for(int i = heights.length - 2; i >= 0; i--) {
            while(!st.isEmpty() && heights[i] <= heights[st.peek()])
                st.pop();
            
            if(!st.isEmpty())   rb[i] = st.peek();
            else rb[i] = heights.length;

            st.push(i);
        }
        

        int maxArea = (int)-1e8;
        for(int i = 0; i < heights.length; i++) {
            int width = rb[i] - lb[i] - 1;
            maxArea = Math.max(maxArea, width * heights[i]);
        } 
        return maxArea;
    }


    public static void main(String[] args) {
        // System.out.println(removeOuterParentheses("(()())(())"));
        int[] arr = {2, -1, 8, 6, 9, 4, 3, 5};
        int[] arr1 = ngtor(arr);
        // for(int i : arr1)    System.out.print(i + " ");
        
        arr1 = ngtol(arr);
        for(int i : arr1)    System.out.print(i + " ");
    }
}