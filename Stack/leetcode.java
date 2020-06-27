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

    //next Greater To The Right
    public static int[] ngtor(int[] nums) {
        Stack<Integer> st = new Stack<>();
        st.push(0);
        int[] ngr = new int[nums.length];
        Arrays.fill(ngr, -1);  

        for(int i = 0; i < nums.length; i++) {
            while(!st.isEmpty() && nums[i] >= nums[st.peek()])  //next smaller replace '>' to '<'
                ngr[st.pop()] = i;
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
                ngr[st.pop()] = i;
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
    public int minAddToMakeValid_(String S) {
        Stack<Integer> st = new Stack<>();  //indexes
        st.push(-1);
        for(int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            if(st.peek() != -1 && ch == ')' && S.charAt(st.peek()) == '(')    st.pop();
            else st.push(i);
        }
        return st.size() - 1;
    }

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

    public int minAddToMakeValid02(String S) {  //BEST
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
        Stack<Integer> st = new Stack<>();  //Store the indexes (only use stack for storing opening braces)

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

    //Leetcode 1190. Reverse Substrings Between Each Pair of Parentheses
    public static String reverseParentheses(String s) {
        Stack<Character> st = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(!st.isEmpty() && ch == ')') {
                String temp = "";
                while(st.peek() != '(') {
                    temp += st.pop();
                }
                st.pop();   //removing '('
                for(int iC = 0; iC < temp.length(); iC++) 
                    st.push(temp.charAt(iC));
            } else 
                st.push(ch);
        }
        String temp = "";
        for(char sTemp : st)
            temp += sTemp;
        return temp;
    }

    //Leetcode 32. Longest Valid Parentheses    //[IMPORTANT] this approach can solve above two questions 1249 and 921
    public int longestValidParentheses(String s) {
        Stack<Integer> st = new Stack<>();
        int ans = 0;
        st.push(-1);
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(st.peek() != -1 && ch == ')' && s.charAt(st.peek()) == '(') {
                 st.pop();
                ans = Math.max(ans, i - st.peek());
            } else
                st.push(i);
        }
        return ans;
    }  

    //Leetcode 735. Asteroid Collision  
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> st = new Stack<>();
        for(int a: asteroids) {
            if(a > 0)   st.push(a);
            else {
                while(!st.isEmpty() && st.peek() > 0 && st.peek() < -a)       
                    st.pop();
                if((!st.isEmpty() && st.peek() < 0) || st.isEmpty())  st.push(a);
                else if(!st.isEmpty() && st.peek() == -a)   st.pop();
            }
        }

        int[] arr = new int[st.size()];
        for(int i = 0; i < arr.length; i++)
            arr[i] = st.elementAt(i);
        return arr;
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

    public int largestRectangleArea_better(int[] heights) {
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        int N = heights.length;
        int max = 0;
        for(int i = 0; i < N; i++) {
            while(st.peek() != -1 && heights[st.peek()] >= heights[i]) {    //wadda ele on the stack then pop and calculate
                int idx = st.pop();
                int width = i - st.peek() - 1;
                int area = width * heights[idx];
                max = Math.max(max, area);
            }
            st.push(i);
        }
        //Stack is not empty yet. THINK OF ALL INCREASING HEIGHTS i.e, {1, 2, 3, 4, 5}
        while(st.peek() != -1) {    //same as above while loop
                int idx = st.pop();
                int width = N - st.peek() - 1;  //change i to N
                int area = width * heights[idx];
                max = Math.max(max, area);
        }
        return max;
    }

    //Leetcode 85. Maximal Rectangle
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0)   return 0; //handling case [] and [[]]
        int[] tempHeights = new int[matrix[0].length];  //we are updating this array. NOT MAKING IT NEW EACH TIME. 
                                //i.e, if 0 comes the value at that idx becomes 0 and if 1 then the calue gets added.
        int maxArea_ = 0;
        for(int r = 0; r < matrix.length; r++) {
            for(int c = 0; c < matrix[0].length; c++) {
                int x = matrix[r][c] - '0';
                if(x == 1) {
                    tempHeights[c] += 1;
                } else {
                    tempHeights[c] = 0;
                }
            }
            int area = largestRectangleArea_better(tempHeights);
            maxArea_ = Math.max(maxArea_, area);
        }
        return maxArea_;
    }

    //Leetcode 42. Trapping Rain Water
    public int trap(int[] height) { //O(n)
        //Find greatest on left so far
          int[] greatestOnLeft = new int[height.length];
          int prev = -1;
          for(int i = 0; i < height.length; i++) {
              greatestOnLeft[i] = Math.max(prev, height[i]);
              prev = greatestOnLeft[i];
          }
          
          //Find greatest on right so far
          int[] greatestOnRight = new int[height.length];
          prev = -1;
          for(int i = height.length - 1; i >= 0; i--) {
              greatestOnRight[i] = Math.max(prev, height[i]);
              prev = greatestOnRight[i];
          }
          
          //Calculate the water  
          int totalWater = 0;
          for(int i = 0; i < height.length; i++)
              totalWater += (Math.min(greatestOnRight[i], greatestOnLeft[i]) - height[i]);
          
          return totalWater;
      }

      public int trap_btr(int[] height) {
        int water = 0;  //total water stored
        Stack<Integer> st = new Stack<>();   //storing the indexes
        for(int i = 0; i < height.length; i++) {
            while(!st.isEmpty() && height[st.peek()] <= height[i]) {
                int htIdx = st.pop();
                if(st.isEmpty())    break;
                
                int potentialHeight = Math.min(height[i], height[st.peek()]) - height[htIdx];
                int potentialWidth = i - st.peek() - 1;
                water += potentialHeight * potentialWidth;
            }
            st.push(i);
        }
        return water;
    }

    public int trap_03(int[] height) {  //Two pointer approach
        int li = 0;
        int ri = height.length - 1;
        int lmaxBH = 0;   int rmaxBH = 0;   //BH: building height
        int water = 0;
        while(li <= ri) {
            lmaxBH = Math.max(lmaxBH, height[li]);
            rmaxBH = Math.max(rmaxBH, height[ri]);
            if(lmaxBH <= rmaxBH)
                water += (lmaxBH - height[li++]);
            else
                water += (rmaxBH - height[ri--]);
        }   
        return water;
    }

    //Leetcode 155. Min Stack 
    class MinStack {
        Stack<Long> st = new Stack<>(); //making this long as i * 2 can give overflow error
        //Therefore, use Long class
        long minSF = 0; //primitive datatype
    /** initialize your data structure here. */
    public MinStack() {
        minSF = 0;
    }
    
    public void push(int val) {
        long x = (long) val;
        if(st.isEmpty()) {
            st.push(x);
            minSF = x; return;}
         if(x <  minSF) {
            long encVal = 2 * x - minSF;
            st.push(encVal);
            minSF = x;
        } else st.push(x);
    }
    
    public void pop() {
        if(st.peek() <  minSF){ //encoded value present
            long oldMin = 2 * minSF - st.peek();
            minSF = oldMin;
        }
        st.pop();
    }
    
    public int top() {
        if(st.peek() <  minSF)  //encoded value present
            return (int) minSF;
        else
            return (int) ((long) st.peek());    //As Long can't be converted to int BUT long can.
    }
    
    public int getMin() {
        return (int) minSF;
    }
    }
     
    public static void main(String[] args) {
        // System.out.println(removeOuterParentheses("(()())(())"));
        // int[] arr = {2, -1, 8, 6, 9, 4, 3, 5};
        // int[] arr1 = ngtor(arr);
        // // for(int i : arr1)    System.out.print(i + " ");
        
        // arr1 = ngtol(arr);
        // for(int i : arr1)    System.out.print(i + " ");
        // minRemoveToMakeValid();
        // System.out.println(reverseParentheses("(ed(et(oc))el)"));
        // System.out.println(longestValidParentheses("()"));
        int[] arr = {1};
        // int[] res = nslor(arr);
        // int[] res2 = nslol(arr);
        // System.out.println(Arrays.toString(res));
        // System.out.println(Arrays.toString(res2));

        // System.out.println((largestRectangleArea(arr)));
    }
}