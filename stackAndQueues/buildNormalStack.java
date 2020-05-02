package stackAndQueues;
/**
 *  @author Aasminpreet Singh Kainth
 *  Building a normal stack from scratch
 */
import java.io.*;
import java.util.*;

public class buildNormalStack {

  public static class CustomStack {
    int[] data;
    int tos;

    public CustomStack(int cap) {
      data = new int[cap];
      tos = -1;
    }

    int size() {
      // write ur code here
      return data.length;
    }

    void display() {
      // write ur code here
      for(int i = 0; i < tos + 1; i++) {
          System.out.print(data[i] + " ");
      }
      System.out.print('\n');
    }

    void push(int val) {
      // write ur code here
      if(this.tos + 1 > this.size()) {
          System.out.print("Stack overflow");
      } else {
          tos++;
          data[tos] = val;
      }
    }

    int pop() {
      // write ur code here
      if(this.tos == -1) {
          System.out.print("Stack underflow");
          return -1;
      } else {
          int val = data[tos];
          tos--;
          return val;
      }
    }

    int top() {
       // write ur code here
       if(this.tos == -1) {
          System.out.print("Stack underflow");
          return -1;
      } else {
          int val = data[tos];
          return val;
      }
    }
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    CustomStack st = new CustomStack(n);

    String str = br.readLine();
    while(str.equals("quit") == false){
      if(str.startsWith("push")){
        int val = Integer.parseInt(str.split(" ")[1]);
        st.push(val);
      } else if(str.startsWith("pop")){
        int val = st.pop();
        if(val != -1){
          System.out.println(val);
        }
      } else if(str.startsWith("top")){
        int val = st.top();
        if(val != -1){
          System.out.println(val);
        }
      } else if(str.startsWith("size")){
        System.out.println(st.size());
      } else if(str.startsWith("display")){
        st.display();
      }
      str = br.readLine();
    }
  }
}