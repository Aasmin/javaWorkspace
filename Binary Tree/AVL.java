public class AVL {
    public static class Node{
        int data;
        Node left = null;  // Node* left=null;
        Node right = null; // Node* right=null;
        
        int height = 0;
        int balance = 0;
        Node(int data)
        {
            this.data = data;
            this.height = 0;
            this.balance = 0;
        }
    
        Node()
        {
        }
    }
    
    //AVL Util.===========================================================================
    public static void updateHeightAndBalance(Node node) {
        int lh = -1;
        int rh = -1;

        if(node.left != null)   lh = node.left.height;
        if(node.right != null)   lh = node.right.height;
        
        node.height = Math.max(lh, rh) + 1;
        node.balance = lh - rh;
    }

    public static Node ll(Node A) {
        Node B = A.left;
        Node BkaRight = B.right;

        B.right = A;
        A.left = BkaRight;
        
        updateHeightAndBalance(A);
        updateHeightAndBalance(B);
        return B;
    }

    public static Node rr(Node A) {
        Node B = A.right;
        Node BkaLeft = B.left;

        B.left = A;
        A.right = BkaLeft;

        updateHeightAndBalance(A);
        updateHeightAndBalance(B);

        return B;
    }

    public static Node getRotation(Node node) {
        updateHeightAndBalance(node);
        if(node.balance == 2) {  //ll, lr
            if(node.left.balance == 1) {    //ll
                return ll(node);
            } else {    //lr
                node.left = rr(node.left);
                return ll(node.left);
            }
        } else if (node.balance == -2) { //rr, rl
            if(node.right.balance == -1) {    //rr
                return rr(node);
            } else {    //rl
                node.right = ll(node.right);
                return rr(node.right);
            }
        }
        return node;
    }

    //BST.==========================================
    public static Node constructBST(int[] arr, int si, int ei)        //O(n)
    {
        if(si > ei) return null;

        int mid = (si + ei) >> 1;

        Node node = new Node(arr[mid]);
        node.left = constructBST(arr, si, mid - 1);
        node.right = constructBST(arr, mid + 1, ei);

        updateHeightAndBalance(node);
        return node;
    }

    public static void display(Node node)
    {
        if (node == null)
            return;

        String str = "";
        str += ((node.left != null) ? (node.left.data) : ".");
        str += " <- " + node.data + " . ";
        str += ((node.right != null) ? (node.right.data) : ".");
        System.out.println(str + "\n");

        display(node.left);
        display(node.right);
    }

    public static Node addNode(Node node, int data) {
        if(node == null)     return new Node(data);
        
        if(data < node.data) 
            node.left = addNode(node.left, data);
        else
            node.right = addNode(node.right, data);   //agar equal hai data then it'll be added to right
        
        return getRotation(node);
    }


    int maximumEle(Node node) {    //logn
        if(node == null) return -1;
        while(node.right != null) {
            node = node.right;
        }
        return node.data;
    }


    Node removeData(Node node, int data) {
        if(node == null)     return null; //never found data
        
        if(data < node.data) 
            node.left = removeData(node.left, data);
        else if(data > node.data)
            node.right = removeData(node.right, data);   
        else {  //data found
            if(node.left == null || node.right == null)     //handling case if either one leaf or no leaf is present
                return node.left == null ? node.right : node.left;
             
            int maxInLeft = maximumEle(node.left); 
            node.data = maxInLeft;
            node.left = removeData(node.left, maxInLeft);
        }
        return getRotation(node);
    }
    
    public static void solve() {
    // int[] arr = {10, 20, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130};
    // Node root = constructBST(arr, 0, arr.size() - 1);




    }
    public static void main(String[] args) {
        solve();
    }
}