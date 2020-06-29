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
    public static void main(String[] args) {
        
    }
}