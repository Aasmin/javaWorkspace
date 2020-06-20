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
        System.out.println(removeOuterParentheses("(()())(())"));
    }
}