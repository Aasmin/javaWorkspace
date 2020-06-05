public class AVL {
    public static class Node{
        int data;
        Node left = null;  // Node* left=nullptr;
        Node right = null; // Node* right=nullptr;
        
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

    public static Node constructBST(int[] arr, int si, int ei)        //O(n)
    {
        if(si > ei) return null;

        int mid = (si + ei) >> 1;

        Node node = new Node(arr[mid]);
        node.left = constructBST(arr, si, mid - 1);
        node.right = constructBST(arr, mid + 1, ei);

        return node;
    }

    public static void display(Node node)
    {
        if (node == null)
            return;

        String str = "";
        str += ((node.left != null) ? (node.left.data) : ".");
        str += " <- " + node.data + " -> ";
        str += ((node.right != null) ? (node.right.data) : ".");
        System.out.println(str + "\n");

        display(node.left);
        display(node.right);
    }
    public static void solve() {
    // int[] arr = {10, 20, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130};
    // Node root = constructBST(arr, 0, arr.size() - 1);




    }
    public static void main(String[] args) {
        solve();
    }
}