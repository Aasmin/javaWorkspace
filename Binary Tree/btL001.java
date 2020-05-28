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
    
    static Node LCA = null;
    static boolean lowestCommonAncestor02(Node node, int p, int q) {    //leetcode 236 (LCA w/o storing paths)
        if(node == null)    return false;

        boolean selfDone = false;
        if(node.data == p || node.data == q) {
            selfDone = true;
        }

        boolean leftDone = lowestCommonAncestor02(node.left, p, q);
        if(LCA != null) return true;
        
        boolean rightDone = lowestCommonAncestor02(node.right, p, q);
        if(LCA != null) return true;

        if((leftDone && rightDone) || (selfDone && rightDone) || (selfDone && leftDone))    //work done at post area
            LCA = node;
        
        return selfDone || rightDone || leftDone;
        
    }

    public static void kDown(Node node, int K, Node block) {
        if(node == null || node == block)    return;
        if(K == 0)  {
            System.out.print(node.data + " ");  return;
        }
        kDown(node.left, K - 1, block);
        kDown(node.right, K - 1, block);
    }

    public static void distanceK(Node node, int target, int K) {
        ArrayList<Node> path = new ArrayList<>();
        rootToNode_(node, target, path);
        Node blockedNode = null;
        for(int i = 0; i < path.size(); i++) {
            kDown(path.get(i), K - i, blockedNode);
            blockedNode = path.get(i);
        }
    }

    //leetcode 863  O(2n)
    public static int kNodeAway(Node node, int target, int K) {
        if(node == null)    return -1;
        
        if(node.data == target) {
            kDown(node, K, null);
            return 1;
        }

        int leftDistance = kNodeAway(node.left, target, K);
        if(leftDistance != -1) {
            if(K - leftDistance >= 0)   kDown(node, K - leftDistance, node.left);
            return leftDistance + 1;
        }

        int rightDistance = kNodeAway(node.right, target, K);
        if(rightDistance != -1) {
            if(K - rightDistance >= 0)   kDown(node, K - rightDistance, node.right);
            return rightDistance + 1;
        }

        return -1;
    }

    public static int kNodeAway02(Node node, int target, int K) {
        if(node == null)    return -1;
        
        if(node.data == target) {
            kDown(node, K, null);
            return 1;
        }

        int leftDistance = kNodeAway02(node.left, target, K);
        if(leftDistance != -1) {
            if(K - leftDistance >= 0)   kDown(node, K - leftDistance, node.left);
            return leftDistance + 1;
        }

        int rightDistance = kNodeAway02(node.right, target, K);
        if(rightDistance != -1) {
            if(K - rightDistance >= 0)   kDown(node, K - rightDistance, node.right);
            return rightDistance + 1;
        }

        return -1;
    }

    static int kNodeAway03(Node node, int target, int K) {  //knodes aways without blocking 
        if(node == null)    return -1;

        if(node.data == target) {
            kDown(node, K, null);
            return 1;
        }

        int leftDistance = kNodeAway03(node.left, target, K);
        if(leftDistance != -1) {
            if(K - leftDistance == 0)
                System.out.print(node.data + " ");
            else 
                kDown(node.right, K - leftDistance - 1, null);  // here K value is important
            return leftDistance + 1;
        }

        int rightDistance = kNodeAway03(node.right, target, K);
        if(rightDistance != -1) {
            if(K - rightDistance == 0)
                System.out.print(node.data + " ");
            else 
                kDown(node.left, K - rightDistance - 1, null);
            return rightDistance + 1;
        }

        return -1;
    }
    
    //leetcode 543
    public static class DiaPair {
        int hei = 0;    int dia = 0;
        DiaPair(int hei, int dia) {this.hei = hei;  this.dia = dia;}
    }

    public static DiaPair diameter02(Node node) {
        if(node == null) {return new DiaPair(-1, 0);}   //height for leaf node is -1

        DiaPair lr = diameter02(node.left);
        DiaPair rr = diameter02(node.right);

        DiaPair self = new DiaPair(-1, 0);
        self.dia = Math.max(Math.max(lr.dia, rr.dia), lr.hei + rr. hei + 2);
        self.hei = Math.max(lr.hei, rr.hei) + 1;

        return self;
    }

    static int diameter = 0;    //this will store the diameter of the tree
    public static int diameter03(Node node) {   //we are returning height in this fuction
        if(node == null) return -1;
        
        int lh = diameter03(node.left);
        int rh = diameter03(node.right);

        diameter = Math.max(diameter, lh + rh + 2);
        
        return Math.max(lh, rh) + 1; //returning height here
    }

    public static boolean hasPathSum(Node root, int sum) {
        if(root == null) return false;

        if(root.left == null && root.right == null) {
            sum -= root.data;
            if(sum == 0)    return true;
            else return false;
        }

        boolean lres = hasPathSum(root.left, sum - root.data);
        if(lres)    return true;
        boolean rres = hasPathSum(root.right, sum - root.data);
        if(rres)    return true;

        return false;
    }

    //leaf to leaf max path sum
    //approach is similar to diameter
    public static int maxPathSum(Node root) {
        // your code here
        max_Sum = (int) -1e8;   //as two test cases were being tested in GFG so, when this is function is called again we set it deafult again
        maxPathSum_(root);
        return max_Sum;
    }
    static int max_Sum;
    public static int maxPathSum_(Node root) {
        if(root == null)    return 0;
        
        int leftNodeToLeafSum = maxPathSum_(root.left);  //gives max Sum from left child
        int rightNodeToLeafSum = maxPathSum_(root.right);
        
        if(root.left != null && root.right != null) {   //case: when root has both L and R leaf
            max_Sum = Math.max(max_Sum, leftNodeToLeafSum + rightNodeToLeafSum + root.data);    //compares with self Node leaf to leaf
            return  Math.max(leftNodeToLeafSum, rightNodeToLeafSum) + root.data;
        }
        
        return (root.left == null ? rightNodeToLeafSum: leftNodeToLeafSum) + root.data; //case: when either L or R is present [this case don't change the Max leaf to leaf sum, it only contributes as an node in the path]
    } 

    //leetcode 124
    static int max_nodeToNodeSum = (int) -1e8;
    public static int maxPathNodeToNodeSum(Node node) {
        if(node == null)    return (int) -1e8;

        int leftNodeToNodeSum = maxPathNodeToNodeSum(node.left);
        int rightNodeToNodeSum = maxPathNodeToNodeSum(node.right);

        int max_subtree = Math.max(leftNodeToNodeSum, rightNodeToNodeSum) + node.data;  // Handles faith. 
        max_nodeToNodeSum = Math.max(max_nodeToNodeSum, Math.max(node.data, Math.max(leftNodeToNodeSum + node.data + rightNodeToNodeSum, max_subtree)));
        
        return Math.max(max_subtree, node.data);
    }
    public static void main(String[] args) {
        int[] arr = {10, 20, 40, -1, -1, 50, 80, -1, -1, 90, -1, -1, 30, 60, 100, -1, -1, -1, 70, 110, -1, -1, 120, -1, -1};
        Node root = constructTree(arr);
        // display(root);
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
        // int lca = lowestCommonAncestor(root, 50, 50).data;
        // System.out.println(lca);
        // lowestCommonAncestor02(root, 50, 80);
        // System.out.println(LCA.data);
        // distanceK(root, 50, 4);
        // System.out.println();
        // kNodeAway(root, 50, 3);
        // System.out.println();
        // kNodeAway02(root, 50, 3);
        // System.out.println();
        // kNodeAway03(root, 50, 3);
        System.out.println(diameter02(root).dia);
        diameter03(root);
        System.out.println(diameter);
    }
}