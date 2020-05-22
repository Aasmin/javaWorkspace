import java.util.Stack;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

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
        if(node == null)    return 0;  //return -1 for edges and 0 for nodes
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
                System.out.print(node.data + " ");
                if(node.left != null)   mq.add(node.left);
                if(node.right != null)   mq.add(node.right);
            }
            System.out.println();
        }
    }
    
    public static void IterativePrePostInTraversal(Node node) {
        Pair rtp = new Pair(node, 1);
        Stack<Pair> st = new Stack<>();
        st.push(rtp);

        String pre = "";
        String in = "";
        String post = "";

        while(st.size() > 0) {
            Pair top = st.peek();
            if(top.state == 1) {
                pre += top.node.data + " ";
                if(top.node.left != null)
                    st.push(new Pair(top.node.left, 1));
                top.state++;
            } else if(top.state == 2) {
                in += top.node.data + " ";
                if(top.node.right != null)
                    st.push(new Pair(top.node.right, 1));
                top.state++;
            } else {
                post += top.node.data + " ";
                st.pop();
            }
        }

        System.out.println("Pre: \t" + pre );
        System.out.println("IN: \t" + in );
        System.out.println("POST: \t" + post );
    }

    //find node and trace the path(divide in 5 parts and solve )
    public static boolean nodeToRootPath(Node node, int data, ArrayList<Node> path) {
        if(node == null)    return false;

        if(node.data == data) {
            path.add(node);
            return true;
        }

        //find in left child
        boolean filc = nodeToRootPath(node.left, data, path);
        if(filc) {
            path.add(node);
            return true;
        }

        //find in right child
        boolean filr = nodeToRootPath(node.right, data, path);
        if(filr) {
            path.add(node);
            return true;
        }

        return false;
    }

    public static void kLevelsDown(Node node, int k, Node block) {
        if(k < 0 || node == null || node == block)   return;
        if(k == 0)  System.out.println(node.data);
        kLevelsDown(node.left, k - 1, block);   
        kLevelsDown(node.right, k - 1, block);
    }

    public static void printkNodesFar(Node node, int data, int k) {
        ArrayList<Node> path = new ArrayList<>();
        nodeToRootPath(node, data, path);
        // for(Node ele : path)    System.out.print(ele.data + " ");
        // System.out.println();
        for(int i = 0; i < path.size(); i++) {
            kLevelsDown(path.get(i), k - i, i == 0 ? null : path.get(i - 1));
        }
    }

    public static void pathToLeaf(Node node, String path, int sum) {
        if(node == null) return;
        if(node.left == null && node.right == null) {
            System.out.print(path + node.data + " = " + (sum + node.data) + "\n");
            return;
        }
        pathToLeaf(node.left, path + node.data + " ", sum + node.data);
        pathToLeaf(node.right, path + node.data + " ", sum + node.data);
    }

    public static Node createLeftCloneTree(Node node) {
        if(node == null) return null;
        Node lcr = createLeftCloneTree(node.left);
        Node rcr = createLeftCloneTree(node.right);
        
        Node nn = new Node(node.data, lcr, null);
        node.left = nn;
        node.right = rcr;
        return node;
    }

    public static Node transformFromLeftClonedTree(Node node) {
        if(node == null)    return null;
        Node lnn = transformFromLeftClonedTree(node.left.left); //left normal node
        Node rnn = transformFromLeftClonedTree(node.right); //left normal node

        node.left = lnn;
        node.right = rnn;
        return node;
    }

    static class BPair{
        int num;    String str = "";
        BPair(int num, String b)  {this.num = num;     this.str = b;} 
    }

    public static void printNumInBinaryTillN(int n) {
        LinkedList<BPair> queue = new LinkedList<>();
        queue.add(new BPair(1, "1"));
        while(queue.size() > 0) {
            BPair rem = queue.removeFirst();
            System.out.println(rem.num + "\t->\t" + rem.str);
            //left add
            if((2 * rem.num) <= n) {
                queue.add(new BPair(2 * rem.num, rem.str + "0"));
            }
            //right add
            if((2 * rem.num + 1) <= n) {
                queue.add(new BPair(2 * rem.num + 1, rem.str + "1"));
            }
        }
    }

    public static void printSingleChild(Node node) {
        if(node == null) return ;
        printSingleChild(node.left);
        printSingleChild(node.right);
        if(node.left == null && node.right != null)
            System.out.println(node.right.data);
        if(node.left != null && node.right == null)
            System.out.println(node.left.data);
        return;
    }

    public static Node removeLeaves(Node node) {
        if(node == null) return null;
        if(node.left == null && node.right == null)   return null;
        node.left = removeLeaves(node.left);
        node.right = removeLeaves(node.right);
        return node;
    }

    private static void removeLeaves02(Node parent, Node child) {
        if(child == null) return;
        if(child.left == null && child.right == null) {
            if(parent.left == child)    parent.left = null;
            else    parent.right = null;
        }
        removeLeaves02(child, child.left);
        removeLeaves02(child, child.right);
    }

    public static void removeLeaves02(Node node) {
        if(node.left == null && node.right == null)    node = null;
        removeLeaves02(node, node.left);
        removeLeaves02(node, node.right);
    }

    //leetcode 543  // in terms of nodes
    // static int maxDia = Integer.MIN_VALUE;
    public static int diameter(Node node) {   //in terms of nodes
        if(node == null)    return 1;
        
        int ld = diameter(node.left);
        int rd = diameter(node.right);

        int lheight = height(node.left);
        int rheight = height(node.right);
        int selfD = lheight + rheight + 1;

        return Math.max(Math.max(ld, rd), selfD);
    }
    //O(n)  in terms of nodes
    static class DiaPair {
        int ht; int dia;
    }

    static DiaPair diameter02(Node node) {
        if(node == null) {
            DiaPair base = new DiaPair();
            base.dia = 0;
            base.ht = 0;
            return base;
        }

        DiaPair lPair = diameter02(node.left);
        DiaPair rPair = diameter02(node.right);

        DiaPair selfPair = new DiaPair();
        selfPair.ht = Math.max(lPair.ht, rPair.ht) + 1;
        selfPair.dia = Math.max(Math.max(lPair.dia, rPair.dia), lPair.ht + rPair.ht + 1);

        return selfPair;
    }

    //leetcode 105
    //BT constructor given Pre and In
    static Node construct1(int[] pre, int[] in, int preSt, int preEnd, int inSt, int inEnd) {
        if(preSt > preEnd || inSt > inEnd) {
            return null;
        }

        int idx = -1;
        Node node = new Node(pre[preSt], null, null);
        for(int i = inSt; i <= inEnd; i++) {
            if(in[i] == pre[preSt]) {
                idx = i;    break;
            }
        }
        int ildc = idx - inSt;
        node.left = construct1(pre, in, preSt + 1, preSt + ildc, inSt, idx - 1);
        node.right = construct1(pre, in, preSt + ildc + 1, preEnd, idx + 1, inEnd);
        return node;
    }

    static Node construct2(int[] post, int[] in, int postSt, int postEnd, int inSt, int inEnd) {
        if(postSt > postEnd || inSt > inEnd)    return null;
        int data = post[postEnd];
        Node nn = new Node(data, null, null);
        int idx = -1;
        for(int i = inSt; i <= inEnd; i++) {
            if(data == in[i]) {
                idx = i;    break;
            }
        }
        int ildc = idx - inSt - 1;
        nn.left = construct2(post, in, postSt, postSt + ildc, inSt, idx - 1);
        nn.right = construct2(post, in, postSt + ildc + 1, postEnd - 1, idx + 1, inEnd);
        return nn;
    }
    

    public static void main(String[] args) {
        Integer[] arr = {50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null, null};
        // Integer[] arr = {50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, 60, null, null, null, null};
        // Integer[] arr1 = {1, 2, 4, 8, 12, 18, null, null, null, null, null, 8, 10, 14, null, 21, 28, null, 31, null, null, null, 
        //      null, 11, 16, null, null, 17, null, null, 3, null, 4, 8, 12, 18, null, null, null, null, null, null};
        Node root = construct(arr);
        display(root);
        // System.out.println(size(root));
        // System.out.println(sum(root));
        // System.out.println(height(root));   
        // System.out.println(max(root));
        // traversals(root);
        // levelOrder(root);
        // IterativePrePostInTraversal(root);
        // ArrayList<Node> path = new ArrayList<>();
        // System.out.println(nodeToRootPath(root, 70 , path));
        // for(Node ele : path)    System.out.println(ele.data);
        // kLevelsDown(root, 0, null);
        // printkNodesFar(root, 25, 2);
        // pathToLeaf(root, "", 0);
        System.out.println("\n-----New Tree------");
        // Node node = createLeftCloneTree(root);
        // display(node);  
        // System.out.println("\n-----Org Tree------");
        // node = transformFromLeftClonedTree(root);
        // display(node);  
        // printNumInBinaryTillN(8);
        // printSingleChild(root); 
        // Node r  = removeLeaves(root);
        // display(r);
        // removeLeaves02(root);
        // display(root);
        // System.out.println(diameter(root));
        // System.out.println(diameter02(root).dia);

        int[] pre = {50, 25, 12, 20, 37, 30, 75, 62, 87};
        int[] in = {12, 20, 25, 30, 37, 50, 62, 75, 87};
        Node node = construct1(pre, in, 0, pre.length - 1, 0, in.length - 1);
        display(node);

        // int[] post = {12, 30, 37, 25, 70, 62, 87, 75, 50};
        // int[] in = {12, 25, 30, 37, 50, 62, 70, 75, 87};
        // Node node = construct2(post, in, 0, post.length - 1, 0, in.length - 1);
        // display(node);
    }
}