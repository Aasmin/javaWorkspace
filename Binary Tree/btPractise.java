import java.util.Stack;
public class btPractise {
    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
    public static class Pair {
        Node node;
        int state;
        Pair(Node node, int state) {
            this.node = node;   this.state = state;
        }
    }
    public static Node construct(Integer[] arr) {
        Node root = new Node(arr[0], null, null);
        int idx = 0;
        Pair rtp = new Pair(root, 1);
        Stack<Pair> st = new Stack<>();
        st.push(rtp);
        while(st.size() > 0) {
            Pair top = st.peek();
            if(top.state == 1) {
                idx++;
                if(arr[idx] != null)  {
                    Node ln = new Node(arr[idx], null, null);
                    top.node.left = ln;
                    Pair lp = new Pair(ln, 1);
                    st.push(lp);
                } else {
                    top.node.left = null;
                }top.state++;
            } else if(top.state == 2) {
                idx++;
                
                if(arr[idx] != null) {
                    top.node.right = new Node(arr[idx], null, null);
                    Pair rp = new Pair(top.node.right, 1);
                    st.push(rp);
                } else {
                    top.node.right = null;
                }top.state++;
            } else { 
                st.pop();
            }
        }
        return root;
    }

    public static void display(Node node)  {
        if(node == null) return;
        String str = "";
        str += node.left == null ? "." : node.left.data;
        str += "\t<-\t" + node.data + "\t->\t";
        str += node.right == null ? "." : node.right.data;
        System.out.println(str);
        display(node.left);
        display(node.right);
    }
    public static void main(String[] args) {
        Integer[] arr = {50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null, null};
        Node root = construct(arr);
        display(root);
    }
}