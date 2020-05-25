import java.util.ArrayList;
public class btL001 {
    public static class Node {
        int data;   Node left = null;   Node right = null;
        Node(int data) {this.data = data;}
    }
    static int idx = 0;
    static Node constructTree(int[] arr) {
        if(arr.length <= idx || arr[idx] == -1) {
            idx++;
            return null;
        }
        Node node = new Node(arr[idx++]);
        node.left = constructTree(arr);
        node.right = constructTree(arr);
        return node;
    }
    static void display(Node node) {
        if(node == null)    return;
        String str = "";
        str += ((node.left != null) ? node.left.data : " .");
        str += " <- " + node.data + " -> ";
        str += ((node.right != null) ? node.right.data : ".");
        System.out.println(str);
        display(node.left);
        display(node.right);
    }

    //basic.============================================
    public static int size(Node node) {
        if(node == null)    return 0;
        return size(node.left) + size(node.right) + 1;
    }
    public static int height(Node node) {
        if(node == null)    return -1;
        return Math.max(height(node.left), height(node.right)) + 1;
    } 
    public static int max(Node node) {
        if(node == null) return (int)-1e8;
        return Math.max(Math.max(max(node.left), max(node.right)), node.data);
    } 
    public static int min(Node node) {
        if(node == null) return (int)1e8;
        return Math.min(Math.min(min(node.left), min(node.right)), node.data);
    }
    public static boolean find(Node node, int data) {   //best
        if(node == null)    return false;
        if(node.data == data)   return true;
        return find(node.left, data) || find(node.right, data);
    }
    
    //root to code path
    public static boolean rootToNode(Node node, int data, ArrayList<Node> path) {   
        if(node == null)    return false;
        if(node.data == data)   {path.add(node); return true;}
        if(rootToNode(node.left, data, path))   {path.add(node);    return true;}
        if(rootToNode(node.right, data, path))   {path.add(node);   return true;}
        return false;
    }
    public static boolean rootToNode_(Node node, int data, ArrayList<Node> path) {  //best and fast as we don't create and pass the addresses
        if(node == null)    return false;
        if(node.data == data) {
            path.add(node); return true;
        }

        boolean res = rootToNode_(node.left, data, path) || rootToNode_(node.right, data, path);
        if(res) path.add(node);
        return res;
    }
    public static ArrayList<Node> rootToNode02(Node node, int data) {
        if(node == null)    return new ArrayList<>();

        if(node.data == data)   {
            ArrayList<Node> base = new ArrayList<>();
            base.add(node);
            return base;
        }

        ArrayList<Node> left = rootToNode02(node.left, data);
        if(left.size() != 0) {
            left.add(node);
            return left;
        }

        ArrayList<Node> right = rootToNode02(node.right, data);
        if(right.size() != 0) {
            right.add(node);
            return right;
        }

        return new ArrayList<>();
    }
    public static ArrayList<Node> rootToNode02_(Node node, int data) {  //this is same as rootToNode02 only. NO CHANGE 
        ArrayList<Node> res;
        if(node == null)    return new ArrayList<>();

        if(node.data == data)   {
            ArrayList<Node> base = new ArrayList<>();
            base.add(node);
            return base;
        }

        res = rootToNode02_(node.left, data);
        if(res.size() != 0) {
            res.add(node);
            return res;
        }

        res = rootToNode02_(node.right, data);
        if(res.size() != 0) {
            res.add(node);
            return res;
        }

        return new ArrayList<>();
    }

    //lowest common ancestor
    public static Node lowestCommonAncestor(Node root, int p, int q) {
        ArrayList<Node> res1 = new ArrayList<>();
        ArrayList<Node> res2 = new ArrayList<>();
        rootToNode_(root, p, res1);
        rootToNode_(root, q, res2);
        Node LCA = null;
        int i = res1.size() - 1;
        int j = res2.size() - 1;
        while(i >= 0 || j >= 0) {
            if(i < 0 || j < 0 || res1.get(i) != res2.get(j))  break;
            LCA = res1.get(i);
            i--;    j--;
        }
        return LCA;
    }
    

    public static void main(String[] args) {
        int[] arr = {10, 20, 40, -1, -1, 50, 80, -1, -1, 90, -1, -1, 30, 60, 100, -1, -1, -1, 70, 110, -1, -1, 120, -1, -1};
        Node root = constructTree(arr);
        display(root);
        // System.out.println("size: " + size(root));
        // System.out.println("height: " + height(root));
        // System.out.println("max: " + max(root));
        // System.out.println("min: " + min(root));
        // System.out.println("find: " + find(root, 1110));
        // ArrayList<Node> res = new ArrayList<>();
        // System.out.println(rootToNode(root, 900, res) ? "Found" : "Not Found");
        // System.out.println(rootToNode_(root, 100, res) ? "Found" : "Not Found");
        // for(Node ele : res) System.out.print(ele.data + " ");
        // res = rootToNode02_(root, 90);
        // for(Node ele : res) System.out.print(ele.data + " ");
        int lca = lowestCommonAncestor(root, 50, 50).data;
        System.out.println(lca);
    }
}