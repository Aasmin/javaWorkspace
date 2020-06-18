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
    

    public static void main(String[] args) {
        // Scanner scn = new Scanner(System.in);
        // String str = scn.nextLine();

        // System.out.println(balancedBrackets(str));

        int arr[] = {2, 5, 9, 3, 1, 12, 6, 8, 7};
        System.out.println(Arrays.toString(arr));
        // System.out.println(Arrays.toString(nextGreaterElementToTheRight(arr)));
        // System.out.println(Arrays.toString(nextGreaterElementToTheRight02(arr)));
        System.out.println(Arrays.toString(nextSmallerElementToTheRight(arr)));
        System.out.println(Arrays.toString(nextSmallerElementToTheRight02(arr)));
        
    }
}