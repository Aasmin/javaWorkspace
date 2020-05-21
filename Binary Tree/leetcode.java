import java.util.ArrayList;
import java.util.List;

public class leetcode {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
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
    
    public static void main(String[] args) {
        
    }
}