import java.util.Arrays;

public class leetcode {
    //Leetcode 70. Climbing Stairs
    public int climbStairs(int n) {
        // return climbStairs_(0, n);
        // int[] dp = new int[n + 1];
        // int ans = climbStairs_Tab(0, n, dp);
        // for(int ele : dp)
        //     System.out.print(ele + " ");
        // return ans;
        return climbStairs_Btr(n);
    }
    private int climbStairs_(int st, int n) {
        if(st == n)
            return 1;
        
        int count = 0;
        for(int steps = 1; steps <= 2; steps++)
            if(st + steps <= n)
                count += climbStairs_(st + steps, n);
        return count;
    }
    private int climbStairs_Mem(int st, int n, int[] dp) {
        if(st == n)
            return dp[st] = 1;
        if(dp[st] != 0) return dp[st];
        
        int count = 0;
        for(int steps = 1; steps <= 2; steps++)
            if(st + steps <= n)
                count += climbStairs_Mem(st + steps, n, dp);
        return dp[st] = count;
    }
    private int climbStairs_Tab(int st, int n, int[] dp) {
        for(st = n; st >= 0; st--) {
        if(st == n) {
            dp[st] = 1;
            continue;
        }
        
        int count = 0;
        for(int steps = 1; steps <= 2; steps++)
            if(st + steps <= n)
                count += dp[st + steps];    //climbStairs_(st + steps, n);
        dp[st] = count;   
        }
        return dp[0];
    }
    private int climbStairs_Btr(int n) {
        if(n <= 1)  return 1;
        int a = 1, b = 1, sum = 0;
        for(int i = 2; i <= n; i++) {
            sum = a + b;
            a = b;  
            b = sum;
        }
        return sum;
    }

    //Leetcode 746. Min Cost Climbing Stairs    [Same Approach as Fib]
    private int minCostClimbingStairs(int n, int[] dp, int[] cost) {
        if(n <= 1)  return dp[n] = cost[n];
        if(dp[n] != 0)  return dp[n];

        int ans = Math.min(minCostClimbingStairs(n - 1, dp, cost), minCostClimbingStairs(n - 2, dp, cost));
        return dp[n] = ans + (n != cost.length ? cost[n] : 0);  //Managing cost[n] as 0
    }

    private int minCostClimbingStairsDP(int n, int[] dp, int[] cost) {
        int N = Int;
        for(n = 0; n <= N; n++) {
            if(n <= 1) {dp[n] = cost[n]; continue;}
    
            int ans = Math.min(dp[n - 1], dp[n - 2]);
            dp[n] = ans + (n != cost.length ? cost[n] : 0);  //Managing cost[n] as 0
        }
        return dp[N];
    }

    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;

        int[] dp = new int[n + 1];
        return minCostClimbingStairs(n, dp, cost);
    }
    
    //25 June
    //https://practice.geeksforgeeks.org/problems/friends-pairing-problem/0
    public static void friends_pairing_problem(int n) {
        // int[] dp = new int[n + 1];
        // System.out.println(friends_pairing_problemMEM(n, dp));
        // System.out.println(Arrays.toString(dp));
        // int[] dpT = new int[n + 1];
        // System.out.println(friends_pairing_problemTab(n, dpT));
        // System.out.println(Arrays.toString(dpT));
        System.out.println(friends_pairing_problemTwoPointer(84));
        long a = friends_pairing_problemTwoPointer(84) % 1000000007;
        System.out.println(a);
    }

    private static int friends_pairing_problemMEM(int n, int[] dp) {
        if(n <= 1)  
            return dp[n] = 1;
        int single = friends_pairing_problemMEM(n - 1, dp);
        int pairUp = (n - 1) * friends_pairing_problemMEM(n - 2, dp);   // ways in which remaining (n - 1) friends comes if 1 remains single
        return dp[n] = (single + pairUp);
    }

    private static int friends_pairing_problemTab(int n, int[] dp) {
        int N = n;
        for(n = 0; n <= N; n++) {
            if(n <= 1)  {
                dp[n] = 1;
                continue;
            }
            int single = dp[n-1];   //friends_pairing_problemMEM(n - 1, dp);
            int pairUp = (n - 1) * dp[n - 2]; //(n - 1) * friends_pairing_problemMEM(n - 2, dp);   // ways in which remaining (n - 1) friends comes if 1 remains single
            dp[n] = (single + pairUp);
        }
        return dp[N];
    }
    
    private static int friends_pairing_problemTwoPointer(int n) {
        if(n <= 1)  return n;
        int a = 1, b = 1, ans = 0;
        
        for(int i = 2; i <= n; i++) {
            ans = b + (a * (i - 1));
            a = b;
            b = ans;
        }
        return ans;
    }

    //Leetcode 64. Minimum Path Sum
    public int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        int val = minPathSum(0, 0, grid.length - 1, grid[0].length - 1, grid, dp);
        for(int[] ar : dp) {
            System.out.println(Arrays.toString(ar));
        }
        return val;
    }
    
    private int minPathSum(int sr, int sc, int er, int ec, int[][] grid, int[][] dp) {
        if(sr == er && sc == ec) {
            return dp[sr][sc] = grid[er][ec];
        }
        if(dp[sr][sc] != 0) return dp[sr][sc];
        
        int minCost = Integer.MAX_VALUE;
        
        if(sr + 1 <= er)
            minCost = Math.min(minCost, minPathSum(sr + 1, sc, er, ec, grid, dp));
         if(sc + 1 <= ec)
            minCost = Math.min(minCost, minPathSum(sr, sc + 1, er, ec, grid, dp));
        
        return dp[sr][sc] = minCost + grid[sr][sc];
    }
    
    private int minPathSumDP(int sr, int sc, int er, int ec, int[][] grid, int[][] dp) {
        for(sr = er; sr >= 0; sr--) {
            for(sc = ec; sc >= 0; sc--) {
                if(sr == er && sc == ec) {
                    dp[sr][sc] = grid[er][ec];
                    continue;
                }
                
                int minCost = Integer.MAX_VALUE;
                
                if(sr + 1 <= er)
                    minCost = Math.min(minCost, dp[sr + 1][sc]);   //Math.min(minCost, minPathSum(sr + 1, sc, er, ec, grid, dp));
                 if(sc + 1 <= ec)
                    minCost = Math.min(minCost, dp[sr][sc + 1]);   //Math.min(minCost, minPathSum(sr, sc + 1, er, ec, grid, dp));
                
                dp[sr][sc] = minCost + grid[sr][sc];
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        friends_pairing_problem(10);
    }
}