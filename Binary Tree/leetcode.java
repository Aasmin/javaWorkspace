import java.util.ArrayList;
import java.util.List;

public class leetcode {
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

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val; this.left = left; this.right = right;
    }
}
    //leetcode 863
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        ArrayList<TreeNode> path = new ArrayList<>();
        nodeToRootPath(root, target.val, path);
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i < path.size(); i++) {
            if(i == 0)
                kDown(path.get(i), K - i, null, ans);
            else
                kDown(path.get(i), K - i, path.get(i - 1), ans);
        }
        return ans;
    }
    
    public void kDown(TreeNode node, int K, TreeNode block, List<Integer> ans) {
        if(K < 0 || node == null || node == block)   return;
        if(K == 0)  ans.add(node.val);
        kDown(node.left, K - 1, block, ans);
        kDown(node.right, K - 1, block, ans);
    }
    
    public boolean nodeToRootPath(TreeNode node, int target, ArrayList<TreeNode> path) {
        if(node == null)    return false;
        
        if(node.val == target) {
            path.add(node);
            return true;
        }
        
        boolean filc = nodeToRootPath(node.left, target, path);
        if(filc){
            path.add(node);
            return true;
        }
        
        boolean firc = nodeToRootPath(node.right, target, path);
        if(firc){
            path.add(node);
            return true;
        }

        return false;
    }

    // way 2 (without blocking)

    public void kDown(TreeNode node, int K) {
        if(K < 0 || node == null)   return;
        if(K == 0) System.out.println(node.val);;
        kDown(node.left, K - 1);
        kDown(node.right, K - 1);
    }
    public int nodeToRootPath_03(TreeNode node, int target, int k) {
        if(node.val == target) {
            kDown(node, k);
            return 1;
        }

        int leftDistance = nodeToRootPath_03(node.left, target, k);
        if(leftDistance != -1) {
            if(k - leftDistance == 0) System.out.println(node.val + " ");
            else kDown(node.right, k - leftDistance - 1);
            return leftDistance + 1;
        }

        int rightDistance = nodeToRootPath_03(node.right, target, k);
        if(rightDistance != -1) {
            if(k - rightDistance == 0) System.out.println(node.val + " ");
            else kDown(node.left, k - rightDistance - 1);
            return leftDistance + 1;
        }

        return 0;
    }

    //leetcode 112
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null)    return false;
        if(root.left == null && root.right == null &&  sum - root.val == 0)
            return true;
        boolean res = false;
        res = res || hasPathSum(root.left, sum - root.val);
        res = res || hasPathSum(root.right, sum - root.val);
        return res;
    }

    //leetcode 113
    public void pathSum(TreeNode root, int sum, List<List<Integer>> res, List<Integer> smallAns) {
        if(root == null)    return;
        if(root.left == null && root.right == null && sum - root.val == 0) {
            List<Integer> base = new ArrayList<>(smallAns);
            base.add(root.val);
            res.add(base);
            return;
        }
        smallAns.add(root.val);
        pathSum(root.left, sum - root.val, res, smallAns);
        pathSum(root.right, sum - root.val, res, smallAns);
        smallAns.remove(smallAns.size() - 1);
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<Integer> smallAns = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        pathSum(root, sum, res, smallAns);
        return res;
    }

    //GFG: https://practice.geeksforgeeks.org/problems/maximum-path-sum/
    public static int max_leafToLeafSum = (int) -1e8;

    public static int leafToLeaf(Node node) {
        if(node == null)    return 0;

        int leftNodeToLeafSum = leafToLeaf(node.left);
        int rightNodeToLeafSum = leafToLeaf(node.right);

        if(node.left != null && node.right != null) {
            int selfNodeToLeafSum = leftNodeToLeafSum + rightNodeToLeafSum + node.data;
            max_leafToLeafSum = Math.max(max_leafToLeafSum, selfNodeToLeafSum);
            return max_leafToLeafSum;
        }
        
        return (node.left == null ? rightNodeToLeafSum : leftNodeToLeafSum) + node.data;
    }

    //leetcode 1123

    



   //Leetcode 124.====================================

   int max_nodeToNodeSum = (int)-1e8;
   public int maxPathSum(TreeNode root) {
       maxPathSum_(root);
       return max_nodeToNodeSum;
   }     
  public int maxPathSum_(TreeNode node){
   if(node==null) return 0;

   int leftNodeToNodeSum = maxPathSum_(node.left);
   int rightNodeToNodeSum = maxPathSum_(node.right);
   
   int max_=Math.max(leftNodeToNodeSum,rightNodeToNodeSum) + node.val;
   max_nodeToNodeSum=Math.max(Math.max(max_nodeToNodeSum,node.val),Math.max(leftNodeToNodeSum + node.val + rightNodeToNodeSum, max_));

   return Math.max(max_,node.val);
}


    public static void main(String[] args) {
        
    }
}