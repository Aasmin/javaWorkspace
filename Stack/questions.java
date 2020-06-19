import java.util.Stack;
import java.util.Arrays;
import java.util.Scanner;

public class questions {

    public static boolean duplicateBrackets(String str) {
        Stack<Character> st = new Stack<>();
    
        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(ch == ')') {
                if(st.peek() == '(')    return true;
                else {
                    while(st.peek() != '(')
                        st.pop();
                    st.pop();
                }
            } else 
                st.push(ch);
        }
        return false;
    }

    public static boolean balancedBrackets(String str) {
        Stack<Character> st = new Stack<>();
    
        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(ch == ')') {
                if(st.isEmpty() || st.peek() != '(')    return false;
                else st.pop();
            } else if(ch == '}') {
                if(st.isEmpty() || st.peek() != '{')    return false;
                else st.pop();
            } else if(ch == ']') {
                if(st.isEmpty() || st.peek() != '[')    return false;
                else st.pop();
            } else if(ch == '(' || ch == '{' || ch == '[')
                st.push(ch);
        }
        if(!st.empty()) return false;
        return true;
    }

    public static int[] nextGreaterElementToTheRight(int[] arr){    //O(2n) ~ O(n)  [RIGHT TO LEFT]
        int[] ans = new int[arr.length];
        Stack<Integer> st = new Stack<>();  //STACK WILL STORE THE ACTUAL VALUES.
        st.push(arr[arr.length - 1]);
        ans[arr.length - 1] = -1;
        for(int i = arr.length - 2; i >= 0; i--) {  
            //- a +
            while(!st.isEmpty() && arr[i] >= st.peek()) //complexity is O(n) as n elements hi push hue and n elements hi pop hue
                st.pop();
            
            if(st.isEmpty())    ans[i] = -1;
            else    ans[i] = st.peek();

            st.push(arr[i]);
        }
        return ans;
    } 

    public static int[] nextGreaterElementToTheRight02(int[] arr){    //O(2n) ~ O(n) [LEFT TO RIGHT]
        int[] ans = new int[arr.length];
        Stack<Integer> st = new Stack<>();  //STACK WILL STORE THE INDEXES.
        for(int i = 0; i < arr.length; i++) {
            while(!st.isEmpty() && arr[i] > arr[st.peek()]) { 
                int pos = st.peek();
                ans[pos] = arr[i];
                st.pop();
            }
            st.push(i); //Store the INDEX.
        }
        while(!st.isEmpty()){
            ans[st.peek()] = -1;
            st.pop();
        }
        
        return ans;
    } 
    
    //SAME AS ABOVE JUST CHANGED THE CONDITION FOR POP()
    public static int[] nextSmallerElementToTheRight(int[] arr){    //O(2n) ~ O(n)  [RIGHT TO LEFT]
        int[] ans = new int[arr.length];
        Stack<Integer> st = new Stack<>();  //STACK WILL STORE THE ACTUAL VALUES.
        st.push(arr[arr.length - 1]);
        ans[arr.length - 1] = -1;
        for(int i = arr.length - 2; i >= 0; i--) {  
            //- a +
            while(!st.isEmpty() && arr[i] < st.peek()) //complexity is O(n) as n elements hi push hue and n elements hi pop hue
                st.pop();
            
            if(st.isEmpty())    ans[i] = -1;
            else    ans[i] = st.peek();

            st.push(arr[i]);
        }
        return ans;
    } 

    public static int[] nextSmallerElementToTheRight02(int[] arr){    //O(2n) ~ O(n) [LEFT TO RIGHT]
        int[] ans = new int[arr.length];
        Stack<Integer> st = new Stack<>();  //STACK WILL STORE THE INDEXES.
        for(int i = 0; i < arr.length; i++) {
            while(!st.isEmpty() && arr[i] < arr[st.peek()]) { 
                int pos = st.peek();
                ans[pos] = arr[i];
                st.pop();
            }
            st.push(i); //Store the INDEX.
        }
        while(!st.isEmpty()){
            ans[st.peek()] = -1;
            st.pop();
        }
        
        return ans;
    } 
    

    //SAME AS nextGreaterElementToTheRight() JUST CHANGED THE FOR LOOP DIRECTION
    public static int[] nextGreaterElementToTheLeft(int[] arr){ 
        Stack<Integer> st = new Stack<>();
        st.push(arr[0]);
        int[] nge = new int[arr.length];
        nge[0] = -1;
        for(int i = 1; i < arr.length; i++) {
            while(!st.empty() && arr[i] > st.peek()) {
                st.pop();
            }
            if(st.empty()) nge[i] = -1;
            else    nge[i] = st.peek();

            st.push(arr[i]);
        }
        return nge;
    }
    
    public static int[] nextSmallerElementToTheLeft(int[] arr){ 
        Stack<Integer> st = new Stack<>();
        st.push(arr[0]);
        int[] nge = new int[arr.length];
        nge[0] = -1;
        for(int i = 1; i < arr.length; i++) {
            while(!st.empty() && arr[i] < st.peek()) {
                st.pop();
            }
            if(st.empty()) nge[i] = -1;
            else    nge[i] = st.peek();

            st.push(arr[i]);
        }
        return nge;
    }

    //APPROACH SAME AS nextGreaterElementToTheLeft
    public static int[] stockSpan(int[] arr){
        Stack<Integer> st = new Stack<>();  //STACK STORES INDEX HERE (CHANGE)
        st.push(0);
        int[] nge = new int[arr.length];
        nge[0] = 1;
        for(int i = 1; i < arr.length; i++) {
            while(!st.empty() && arr[i] > arr[st.peek()]) {
                st.pop();
            }
            if(st.empty()) nge[i] = i + 1;
            else    nge[i] = i - st.peek();

            st.push(i);
        }
        return nge;
    }

    //APPROACH SAME AS nextSmallerElementToTheLeft
    public static int largestAreaHistogram(int[] arr){
        
        int[] lb = new int[arr.length];     //next smaller element to the left
        Stack<Integer> st1 = new Stack<>();
        st1.push(0);
        lb[0] = -1;
        for(int i = 1; i < arr.length; i++) {
            while(!st1.empty() && arr[i] <= arr[st1.peek()])
                st1.pop();
            
            if(st1.empty()) {
                lb[i] = -1;
            } else  {
                lb[i] = st1.peek();
            }

            st1.push(i);
        }

        int[] rb = new int[arr.length];     //next smaller element to the right
        Stack<Integer> st = new Stack<>();
        st.push(arr.length - 1);
        rb[arr.length - 1] = arr.length;
        for(int i = arr.length - 2; i >= 0; i--) {
            while(!st.empty() && arr[i] <= arr[st.peek()])
                st.pop();
            
            if(st.empty()) {
                rb[i] = arr.length;
            } else  {
                rb[i] = st.peek();
            }

            st.push(i);
        }


        int maxArea = 0;
        for(int i = 0; i < arr.length; i++) {
            int width = rb[i] - lb[i] - 1;
            maxArea = Math.max(maxArea, width * arr[i]);
        }
        return maxArea;
    }

    public static void slidingWindowMaximum(int[] arr, int k) {   //Next Greater Right approach O(n)
        Stack<Integer> st = new Stack<>();  //Storing indexes
        int[] nge = new int[arr.length];
        st.push(arr.length - 1);
        nge[arr.length - 1] = arr.length;
        for(int i = arr.length - 2; i >= 0; i--) {
            while(!st.empty() && arr[i] >= arr[st.peek()])
                st.pop();
            
            if(st.empty())  nge[i] = arr.length;
            else nge[i] = st.peek();

            st.push(i);
        }

        int j = 0;
        for(int i = 0; i <= arr.length - k; i++) {  //loop till arr.length - k as window poori nahi parh rhi uske baad
            if(j < i)   j = i;  //j kabhi pichhe nahi jayega as if start j from 0 then while loop di extra iterations lgdi in some cases

            while(nge[j] < i + k)       //agar to nge of j window ke andar hai then jump
                j = nge[j];
            
            System.out.println(arr[j]);
        }
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