import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Arrays;

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
    //HINT: approach is similar to diameter
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
    //node to node max path sum
    static int max_nodeToNodeSum = (int) -1e8;  //left and right subtree ka max sambhal rakha
    public static int maxPathNodeToNodeSum(Node node) {
        if(node == null)    return (int) -1e8;

        int leftNodeToNodeSum = maxPathNodeToNodeSum(node.left);
        int rightNodeToNodeSum = maxPathNodeToNodeSum(node.right);

        int max_subtree = Math.max(leftNodeToNodeSum, rightNodeToNodeSum) + node.data;  // Handles faith. 
        max_nodeToNodeSum = Math.max(max_nodeToNodeSum, Math.max(node.data, Math.max(leftNodeToNodeSum + node.data + rightNodeToNodeSum, max_subtree)));
        
        return Math.max(max_subtree, node.data);
    }

    //LevelOrder Series.==============================================
    public static void levelOrder01(Node node) {
        LinkedList<Node> pQue = new LinkedList<>(); //addLast and remove first
        LinkedList<Node> cQue = new LinkedList<>();

        pQue.addLast(node);
        while(pQue.size() != 0) {
            Node rn = pQue.removeFirst();
            System.out.print(rn.data + " ");
            if(rn.left != null) cQue.addLast(rn.left);
            if(rn.right != null) cQue.addLast(rn.right);

            if(pQue.size() == 0) {
                LinkedList<Node> temp = pQue;
                pQue = cQue;
                cQue = temp;

                System.out.println();  
            }
        }
    }

    public static void levelOrder02(Node node) {
        LinkedList<Node> Que = new LinkedList<>();
        Que.addLast(node);
        Que.addLast(null);
        int count = 0;
        System.out.print("Level " + count + ": ");
        while(Que.size() != 1) {    //NOTE: null is also present in the end. So queue size = 1.
            Node rn = Que.removeFirst();
            System.out.print(rn.data + " ");
            if(rn.left != null) Que.addLast(rn.left);
            if(rn.right != null) Que.addLast(rn.right);
            if(Que.getFirst() == null) {
                Que.removeFirst();
                Que.addLast(null);
                count++;
                if(Que.size() != 1) //to avoid printing of an empty level
                    System.out.print("\nLevel " + count + ": ");
            }
        }
    }

    public static void levelOrder03(Node node) {
        LinkedList<Node> Que = new LinkedList<>();
        Que.addLast(node);  //add parent
        int count = 0;
        while(Que.size() != 0) {
            System.out.print("\nLevel " + count++ + ": ");
            int size = Que.size();
            while(size-- > 0) {
                Node rn = Que.removeFirst();    //get and remove root
                System.out.print(rn.data + " ");
                if(rn.left != null) Que.addLast(rn.left);   //add children
                if(rn.right != null) Que.addLast(rn.right);
            }
        }
    }

    //View Category.===========================
    public static void leftView(Node node) {    // same as levelOrder03
        LinkedList<Node> Que = new LinkedList<>();
        Que.addLast(node);  //add parent
        while(Que.size() != 0) {
            int size = Que.size();
            System.out.print(Que.getFirst().data + " ");
            while(size-- > 0) {
                Node rn = Que.removeFirst();    //get and remove root
                if(rn.left != null) Que.addLast(rn.left);   //add children
                if(rn.right != null) Que.addLast(rn.right);
            }
        }
    }
    
    //right view can be achieved by calling the right child first
    public static void rightView(Node node) {    // same as levelOrder03
        LinkedList<Node> Que = new LinkedList<>();
        Que.addLast(node);  //add parent
        while(Que.size() != 0) {
            int size = Que.size();
            System.out.print(Que.getFirst().data + " ");
            while(size-- > 0) {
                Node rn = Que.removeFirst();    //get and remove root
                if(rn.right != null) Que.addLast(rn.right);
                if(rn.left != null) Que.addLast(rn.left);   //add children
            }
        }
    }

    //right view by storing the last element
    public static void rightView02(Node node) {    // same as levelOrder03
        LinkedList<Node> Que = new LinkedList<>();
        Que.addLast(node);  //add parent
        while(Que.size() != 0) {
            int size = Que.size();
            Node prev = null;
            while(size-- > 0) { // this while loop means ek level khatam hogya
                Node rn = Que.removeFirst();    //get and remove root
                if(rn.left != null) Que.addLast(rn.left);   //add children
                if(rn.right != null) Que.addLast(rn.right);
                prev = rn;
            }
            System.out.print(prev.data + " ");
        }
    }

    //vertical Order
    static int leftMinValue = 0;
    static int rightMaxValue = 0;
    static void width(Node node, int lev) {
        if(node == null)    return;
        
        leftMinValue = Math.min(leftMinValue, lev);
        rightMaxValue = Math.max(rightMaxValue, lev);
        
        width(node.left, lev - 1);
        width(node.right, lev + 1);
    }

    static class pairVO {
        Node node;  //actual node
        int val;    //vertical level
        pairVO(Node node, int val) {this.node = node;   this.val = val;}
    }

    static void verticalOrder(Node node) {
        width(node, 0);
        int width = rightMaxValue - leftMinValue + 1;   
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();  // Memory Diag. []
        for(int i = 0; i < width; i++) 
            ans.add(new ArrayList<>());     // Memory Diag.  [[], [], [], [], [], []]
        
        LinkedList<pairVO> Que = new LinkedList<>();
        Que.addLast(new pairVO(node, -leftMinValue));  //add parent

        while(Que.size() != 0) {
            int size = Que.size();
            while(size-- > 0) {
                pairVO rn = Que.removeFirst();    
                ans.get(rn.val).add(rn.node.data);  //accessing the virtual level in the arrayList then, adding the value
                if(rn.node.left != null) Que.addLast(new pairVO(rn.node.left, rn.val - 1));   //add children
                if(rn.node.right != null) Que.addLast(new pairVO(rn.node.right, rn.val + 1));
            }
        }
        for(ArrayList<Integer> arr: ans)
            System.out.println(arr);
    }

    //vertical Order Sum (same as vertical Order only one line change)
    static void verticalOrderSum(Node node) {
        width(node, 0);
        int width = rightMaxValue - leftMinValue + 1;   
        int[] ans = new int[width];  
        
        LinkedList<pairVO> Que = new LinkedList<>();
        Que.addLast(new pairVO(node, -leftMinValue));  

        while(Que.size() != 0) {
            int size = Que.size();
            while(size-- > 0) {
                pairVO rn = Que.removeFirst();    
                ans[rn.val] += rn.node.data;  // only change added
                if(rn.node.left != null) Que.addLast(new pairVO(rn.node.left, rn.val - 1));   
                if(rn.node.right != null) Que.addLast(new pairVO(rn.node.right, rn.val + 1));
            }
        }
        for(int arr: ans)
            System.out.println(arr);
    }

    // 
    public static class PairVO_ implements Comparable<PairVO_> {
        Node node;  //actual node
        int vl; //virtual level
        PairVO_(Node node, int vl) {this.node = node;   this.vl = vl;}

        @Override
        public int compareTo(PairVO_ o) {
            if(this.vl == o.vl) return this.node.data - o.node.data;    //if levels are same, less value is prioritized
            return this.vl - o.vl;  //default behavious of queue (levels are different)
        }
    }
    static List<List<Integer>> verticalOrderLC(Node node) {
        List<List<Integer>> ans = new ArrayList<>();
        if(node == null) return ans;
        
        width(node, 0);
        int width = rightMaxValue - leftMinValue + 1;
        for(int i = 0; i < width; i++)
            ans.add(new ArrayList<>());
        

        PriorityQueue<PairVO_> pque =new PriorityQueue<>();
        PriorityQueue<PairVO_> cque =new PriorityQueue<>();

        pque.add(new PairVO_(node, -leftMinValue));

        while(pque.size() != 0) {
            int size = pque.size();
            while(size-- > 0) {
                PairVO_ rpair = pque.poll();    //remove first and get
                ans.get(rpair.vl).add(rpair.node.data);
    
                if(rpair.node.left!=null) cque.add(new PairVO_(rpair.node.left,rpair.vl-1));
                if(rpair.node.right!=null) cque.add(new PairVO_(rpair.node.right,rpair.vl+1));
            }
            
            PriorityQueue<PairVO_> temp = pque;
            pque=cque;
            cque=temp;
        } 
        
        return ans;
    }

    //same as vertical order
    static void BottomView(Node node) {
        width(node, 0);
        int width = rightMaxValue - leftMinValue + 1;   
        int[] ans = new int[width];  
        
        LinkedList<pairVO> Que = new LinkedList<>();
        Que.addLast(new pairVO(node, -leftMinValue));  

        while(Que.size() != 0) {
            int size = Que.size();
            while(size-- > 0) {
                pairVO rn = Que.removeFirst();    
                ans[rn.val] = rn.node.data;  // only change added
                if(rn.node.left != null) Que.addLast(new pairVO(rn.node.left, rn.val - 1));   
                if(rn.node.right != null) Que.addLast(new pairVO(rn.node.right, rn.val + 1));
            }
        }
        for(int arr: ans)
            System.out.println(arr);
    }

    //same as vertical order
    static void TopView(Node node) {
        width(node, 0);
        int width = rightMaxValue - leftMinValue + 1;   
        int[] ans = new int[width];  
        Arrays.fill(ans, (int)-1e8);    //initiated with an identification value
        
        LinkedList<pairVO> Que = new LinkedList<>();
        Que.addLast(new pairVO(node, -leftMinValue));  

        while(Que.size() != 0) {
            int size = Que.size();
            while(size-- > 0) {
                pairVO rn = Que.removeFirst();    
                if(ans[rn.val] == (int)-1e8)    //  to get the first element. i.e the place will be updated only once
                    ans[rn.val] = rn.node.data;  // only change added
                if(rn.node.left != null) Que.addLast(new pairVO(rn.node.left, rn.val - 1));   
                if(rn.node.right != null) Que.addLast(new pairVO(rn.node.right, rn.val + 1));
            }
        }
        for(int arr: ans)
            System.out.println(arr);
    }

    //same as Vertical order
        static void diagonalView(Node node) {
        width(node, 0);
        int width = -leftMinValue + 1;   // change the width 
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>(); 
        for(int i = 0; i < width; i++) 
            ans.add(new ArrayList<>());     
        
        LinkedList<pairVO> Que = new LinkedList<>();
        Que.addLast(new pairVO(node, -leftMinValue));  

        while(Que.size() != 0) {
            int size = Que.size();
            while(size-- > 0) {
                pairVO rn = Que.removeFirst();    
                ans.get(rn.val).add(rn.node.data);  
                if(rn.node.left != null) Que.addLast(new pairVO(rn.node.left, rn.val - 1));   
                if(rn.node.right != null) Que.addLast(new pairVO(rn.node.right, rn.val + 0));   //only change here
            }
        }
        for(ArrayList<Integer> arr: ans)
            System.out.println(arr);
    }

    //same as bottom view
    static void diagonalViewSum(Node node) {
        width(node, 0);
        int width = -leftMinValue + 1;   
        int[] ans = new int[width];  
        
        LinkedList<pairVO> Que = new LinkedList<>();
        Que.addLast(new pairVO(node, -leftMinValue));  

        while(Que.size() != 0) {
            int size = Que.size();
            while(size-- > 0) {
                pairVO rn = Que.removeFirst();    
                ans[rn.val] += rn.node.data;  // only change added
                if(rn.node.left != null) Que.addLast(new pairVO(rn.node.left, rn.val - 1));   
                if(rn.node.right != null) Que.addLast(new pairVO(rn.node.right, rn.val + 0));   //and here
            }
        }
        for(int arr: ans)
            System.out.println(arr);
    }

    //diagonal View right to left
    static void diagonalViewRtoL(Node node) {
        width(node, 0);
        int width = rightMaxValue + 1;   // change the width 
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>(); 
        for(int i = 0; i < width; i++) 
            ans.add(new ArrayList<>());     
        
        LinkedList<pairVO> Que = new LinkedList<>();
        Que.addLast(new pairVO(node, 0));   //added first ele from here

        while(Que.size() != 0) {
            int size = Que.size();
            while(size-- > 0) {
                pairVO rn = Que.removeFirst();    
                ans.get(rn.val).add(rn.node.data);  
                if(rn.node.left != null) Que.addLast(new pairVO(rn.node.left, rn.val + 0));   // change here
                if(rn.node.right != null) Que.addLast(new pairVO(rn.node.right, rn.val + 1));   // change here
            }
        }
        for(ArrayList<Integer> arr: ans)
            System.out.println(arr);
    }

    static Node DLLhead = null;
    static Node DLLprev = null; //prev node
    public static void DLL(Node node) {
        if(node == null)    return;

        DLL(node.left);

        if(DLLhead == null) DLLhead = node; //this runs once. to set the head
        else {              //work happening at each node to set left right of the current node
            DLLprev.right = node;
            node.left = DLLprev;
        }
        DLLprev = node;     //after work done, prev point to the curr node.

        DLL(node.right);
    }

    static Node LLHead = null;
    static Node LLroot = null;
    public static void lineraize(Node node) {

        if(LLHead == null)  {
            LLroot = node; 
        }
        else {
            LLHead.right = node;
        }
        LLHead = node;
        
        if(node.left != null) lineraize(node.left);
        if(node.right != null) lineraize(node.right);
    }


    //leetcode 98
    static long prev = (long) -1e12;    
    static boolean isBST(Node node) {
       if(node == null)    return true;
       
       if(!isBST(node.left))   return false;
       
       if(prev >= node.data)    return false;
        prev = node.data;

       if(!isBST(node.right))   return false;

       return true;
   }

    public static void levelOrder(Node node) {
        // levelOrder01(node);
        // levelOrder02(node);
        // levelOrder03(node);
        // leftView(node);
        // rightView(node);
        // rightView02(node);
        // verticalOrder(node);
        // System.out.println();
        // verticalOrderSum(node);
        // List<List<Integer>> ans = verticalOrderLC(node);
        // for(List<Integer> arr : ans)    System.out.println(arr);
        // BottomView(node);
        // System.out.println();
        // TopView(node);
        // diagonalView(node);
        // System.out.println();
        // diagonalViewSum(node);
        // diagonalViewRtoL(node);
    }

    public static void set1(Node node) {
        //  BT to DLL 
        // DLL(node);
        // while(DLLhead != null) {
        //     System.out.print(DLLhead.data + " ");
        //     DLLhead = DLLhead.right;
        // }

        //linearize
        lineraize(node);
        while(LLroot != null) {
            System.out.println(LLroot.data);
            LLroot = LLroot.right;
        }
    }

    public static void main(String[] args) {
        // int[] arr = {10, 20, 40, -1, -1, 50, 80, -1, -1, 90, -1, -1, 30, 60, 100, -1, -1, -1, 70, 110, -1, -1, 120, -1, -1};
        // int[] arr = {11, 6, 4, -1, 5, -1, -1, 8, -1, 10, -1, -1, 19, 17, -1, -1, 43, 31, -1, -1, 49, -1, -1};
        int[] arr = {11, 6, 4, -1, 5, -1, -1, 8, -1, 10, -1, -1, 19, 17, -1, -1, 43, 31, -1, -1, 49, -1, -1};
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
        // System.out.println(diameter02(root).dia);
        // diameter03(root);
        // System.out.println(diameter);

        // levelOrder(root);
        set1(root);
    }
}