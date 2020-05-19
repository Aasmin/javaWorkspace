import java.util.Stack;
import java.util.ArrayDeque;
import java.util.Queue;
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
                idx++;top.state++;
                if(arr[idx] != null)  {
                    Node ln = new Node(arr[idx], null, null);
                    top.node.left = ln;
                    Pair lp = new Pair(ln, 1);
                    st.push(lp);
                } else {
                    top.node.left = null;
                }
            } else if(top.state == 2) {
                idx++;top.state++;
                if(arr[idx] != null) {
                    top.node.right = new Node(arr[idx], null, null);
                    Pair rp = new Pair(top.node.right, 1);
                    st.push(rp);
                } else {
                    top.node.right = null;
                }
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

    public static int size (Node node) {
        if(node == null)    return 0;
        int ln = size(node.left);
        int rn = size(node.right);
        int tn = ln + rn + 1;
        return tn;
    }

    public static int sum(Node node) {
        if(node == null)    return 0;
        int ls = sum(node.left);
        int rs = sum(node.right);
        int ts = ls + rs + node.data;
        return ts;
    }

    public static int height(Node node) {   
        if(node == null)    return -1;  //return -1 for edges and 0 for nodes
        int lh = height(node.left);
        int rh = height(node.right);
        int th = Math.max(lh, rh) + 1; 
        return th;
    }

    public static int max(Node node) {
        if(node == null)    return Integer.MIN_VALUE;
        int lm = max(node.left);
        int rm = max(node.right);
        int tm = Math.max(node.data, Math.max(lm, rm));
        return tm;
    }

    public static void traversals(Node node) {
        if(node == null) return;
        System.out.println(node.data + " in PRE order");     //Euler left
        traversals(node.left);
        System.out.println(node.data + " in IN order");      //Euler between
        traversals(node.right);
        System.out.println(node.data + " in POST order");    //Euler right
    }

    public static void levelOrder(Node node) {
        Queue<Node> mq = new ArrayDeque<>();
        mq.add(node);

        while(mq.size() != 0) {
            int count = mq.size();
            for(int i = 0; i < count; i++) {
                node = mq.remove();
                System.out.println(node.data);

                if(node.left != null)   mq.add(node.left);
                if(node.right != null)   mq.add(node.right);
            }
        }

    }
    public static void main(String[] args) {
        Integer[] arr = {50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null, null};
        Node root = construct(arr);
        // display(root);
        // System.out.println(size(root));
        // System.out.println(sum(root));
        // System.out.println(height(root));   
        // System.out.println(max(root));
        // traversals(root);
        levelOrder(root);
    }
}