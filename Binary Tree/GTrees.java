import java.util.ArrayList;
import java.util.Stack;

public class GTrees {
   public static class Node{
    int data;  
    ArrayList<Node> childs = new ArrayList<>();

    Node(int data){
        this.data=data;
    } 
    
    Node(){
    }
}
    public static Node createGTree(int[] arr) {
        Stack<Node> st = new Stack<>();
        for(int i = 0; i < arr.length - 1; i++) {
            if(arr[i] != -1) {
            Node nn = new Node(arr[i]);
            st.push(nn);
            } else {
                Node child = st.pop();
                st.peek().childs.add(child);
            }
        }
        return st.pop();
    }

    public static void preorder(Node node) {
        String str = "";
        str += node.data + "\t->\t";
        for(Node child : node.childs){
            str += child.data + " ";
        } 

        System.out.println(str);

        for(Node child : node.childs){
            preorder(child);
        } 

    }

    public static void main(String[] args) {
        int[] arr = {10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 100, -1, 110, -1, -1, 90, -1, -1, 40, 120, 140, -1, 150, -1, -1, -1, -1};
        Node root = createGTree(arr);
        preorder(root);
    }
}