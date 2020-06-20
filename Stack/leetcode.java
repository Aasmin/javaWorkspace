import java.util.Stack;
import java.util.Arrays;
import java.util.Scanner;

public class leetcode {
    //Leetcode 20. Valid Parentheses
    public boolean isValid(String s) {
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

    public static void main(String[] args) {
        // Scanner scn = new Scanner(System.in);
        // String str = scn.nextLine();

        // System.out.println(balancedBrackets(str));

        int arr[] = {2, 5, 9, 3, 1, 12, 6, 8, 7};
        System.out.println(Arrays.toString(arr));
        // System.out.println(Arrays.toString(nextGreaterElementToTheRight(arr)));
        // System.out.println(Arrays.toString(nextGreaterElementToTheRight02(arr)));
        // System.out.println(Arrays.toString(nextSmallerElementToTheRight(arr)));
        // System.out.println(Arrays.toString(nextSmallerElementToTheRight02(arr)));
        // System.out.println(Arrays.toString(nextGreaterElementToTheLeft(arr)));
        // System.out.println(Arrays.toString(nextSmallerElementToTheLeft(arr)));
        System.out.println(Arrays.toString(stockSpan(arr)));
        
    }
}