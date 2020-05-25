public class btL001 {
    public static class Node {
        int data;   Node left = null;   Node right = null;
        Node(int data) {this.data = data;}
    }
    static int idx = 0;
    static Node constructTree(int[] arr) {
        if(arr.length == idx || arr[idx] == -1) {
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

    public static void main(String[] args) {
        int[] arr = {10, 20, 40, -1, -1, 50, 80, -1, -1, 90, -1, -1, 30, 60, 100, -1, -1, -1, 70, 110, -1, -1, 120, -1, -1};
        Node root = constructTree(arr);
        display(root);
        System.out.println("size: " + size(root));
        System.out.println("height: " + height(root));
        System.out.println("max: " + max(root));
        System.out.println("min: " + min(root));
    }
}