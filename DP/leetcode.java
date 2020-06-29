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
        int N = n;
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

    public static void main(String[] args) {
        
    }
}