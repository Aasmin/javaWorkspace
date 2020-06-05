import java.util.ArrayList;
import java.util.Stack;
import java.util.LinkedList;

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
        System.out.print(node.data + " ");
        for(Node child : node.childs)
            preorder(child);
    }

    public static void display(Node node) {
        String str = "";
        str += node.data + "\t->\t";
        for(Node child : node.childs){
            str += child.data + " ";
        } 

        System.out.println(str);

        for(Node child : node.childs){
            display(child);
        } 
    }

    public static void levelOrder(Node node) {
        LinkedList<Node> que = new LinkedList<>(); //removeFirst, addLast
        que.addLast(node);
        while(que.size() != 0){
            int size = que.size();
            while(size-- > 0) {
                Node rn = que.removeFirst();
                System.out.print(rn.data + " ");
                for(Node child : rn.childs)
                    que.addLast(child);
            }
            System.out.println();
        }
    }

    public static int height(Node node) {
        int h = 0;
        for(Node child : node.childs)  
            h = Math.max(h, height(child));
        return h + 1;
    }

    public static int size(Node node) {
        int s = 0;
        for(Node child : node.childs)  
            s += size(child);
        return s + 1;
    }

    public static boolean find(Node node, int data) {
        if(node.data == data)   return true;
        boolean res = false;
        for(Node child : node.childs)  
            res = res || find(child, data);
        return res;
    }
     
    public static void main(String[] args) {
        int[] arr = {10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 100, -1, 110, -1, -1, 90, -1, -1, 40, 120, 140, -1, 150, -1, -1, -1, -1};
        Node root = createGTree(arr);
        display(root);
        // System.out.println("Preorder: ");
        // preorder(root);
        // System.out.println("Level Order: ");
        // levelOrder(root);
        System.out.println(find(root, 1430));
    }
}