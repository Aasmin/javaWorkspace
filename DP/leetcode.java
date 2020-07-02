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

    //GFG: https://www.geeksforgeeks.org/gold-mine-problem/
    public static void goldMine() {
        int[][] grid = { {1, 3, 1, 5},
                        {2, 2, 4, 1},
                        {5, 0, 2, 3},
                        {0, 6, 1, 2}};
        int[][] dp = new int[grid.length][grid[0].length];
        int maxVal = 0;
        for(int i = 0; i < grid.length; i++) {
            maxVal = Math.max(maxVal, goldMine(i, 0, grid, dp));
        }
        System.out.println(maxVal);
        for(int[] ar: dp)
            System.out.println(Arrays.toString(ar));
        
        //DP
        int[][] dpT = new int[grid.length][grid[0].length];
        System.out.println(goldMine_DP(grid, dpT));
        for(int[] ar: dpT)
            System.out.println(Arrays.toString(ar));
    }

    private static int goldMine(int sr, int sc, int[][] grid, int[][] dp) {
        if(sc == grid[0].length - 1) 
            return dp[sr][sc] = grid[sr][sc];
        if(dp[sr][sc] != 0)   return dp[sr][sc];

        int maxCost = 0;
        int[][] dir = {{-1, 1}, {0, 1}, {1, 1}};
        for(int d = 0; d < 3; d++) {
            int x = sr + dir[d][0];
            int y = sc + dir[d][1];

            if(x >= 0 && y >= 0 && x <= grid.length - 1 && y <= grid[0].length - 1)
                maxCost = Math.max(maxCost, goldMine(x, y, grid, dp));
        }
        return dp[sr][sc] = maxCost + grid[sr][sc];
    }

    private static int goldMine_DP(int[][] grid, int[][] dp) {
        for(int sc = grid[0].length - 1; sc >= 0; sc--) {
            for(int sr = grid.length - 1; sr >= 0; sr--) {
                if(sc == grid[0].length - 1) {
                    dp[sr][sc] = grid[sr][sc];
                    continue;
                }
        
                int maxCost = 0;
                int[][] dir = {{-1, 1}, {0, 1}, {1, 1}};
                for(int d = 0; d < 3; d++) {
                    int x = sr + dir[d][0];
                    int y = sc + dir[d][1];
        
                    if(x >= 0 && y >= 0 && x <= grid.length - 1 && y <= grid[0].length - 1)
                        maxCost = Math.max(maxCost, dp[x][y]);
                }
                dp[sr][sc] = maxCost + grid[sr][sc];
            }
        }
        int maxValDP = 0;
        for(int i = 0; i < grid.length; i++) {
            maxValDP = Math.max(maxValDP, dp[i][0]);
        }
        return maxValDP;
    }

    //GFG: https://www.geeksforgeeks.org/count-number-of-ways-to-partition-a-set-into-k-subsets/
    public static void count_of_waysMAIN() {
        int n = 7, k = 3;
        int[][] dpM = new int[k + 1][n + 1];    //TABLE: k -> rows, n -> cols
        System.out.println(count_of_ways(n, k));
        System.out.println(count_of_waysMEM(n, k, dpM));
        display2D(dpM);

        int[][] dpT = new int[k + 1][n + 1];    //TABLE: k -> rows, n -> cols
        System.out.println(count_of_waysTAB(n, k, dpT));
        display2D(dpT);
    }
    public static int count_of_ways(int n, int k) {
        if(n < k)   return 0;
        if(n == k || k == 1)    return 1;
        int newGroup = count_of_ways(n - 1, k - 1); //as n is coming alone so k - 1 groups can be made
        int existingGroup = count_of_ways(n - 1, k) * k; //based on tree observation
        return newGroup + existingGroup;
    }
    public static int count_of_waysMEM(int n, int k, int[][] dp) {
        if(n < k)   return 0;
        if(n == k || k == 1)    return dp[k][n] = 1;
        if(dp[k][n] != 0)   return dp[k][n];
        int newGroup = count_of_waysMEM(n - 1, k - 1, dp); //as n is coming alone so k - 1 groups can be made
        int existingGroup = count_of_waysMEM(n - 1, k, dp) * k; //based on tree observation
        return dp[k][n] = newGroup + existingGroup;
    }
    public static int count_of_waysTAB(int n, int k, int[][] dp) {
        int K = k, N = n;
        for(k = 1; k <= K; k++) {   // loop starts from k = 1 as dp[k-1] row can't be access if loop starts from 0
            for(n = 0; n <= N; n++) {
                if(n < k)   {continue;}
                if(n == k || k == 1)    {dp[k][n] = 1;  continue;}
                int newGroup = dp[k - 1][n - 1];    //count_of_waysTAB(n - 1, k - 1, dp); //as n is coming alone so k - 1 groups can be made
                int existingGroup = dp[k][n - 1] * k;   //count_of_waysTAB(n - 1, k, dp) * k; //based on tree observation
                dp[k][n] = newGroup + existingGroup;
            }
        }
        return dp[K][N];
    }

    // Practice Link: https://practice.geeksforgeeks.org/problems/mobile-numeric-keypad/0

    //28 June
    //Substring and Subsequence Series.==================================================
    public static boolean[][] isPlaindromeSubstring(String str) {
        int n = str.length();
        boolean[][] dp = new boolean[n][n];
        for(int gap = 0; gap < n; gap++) {
            for(int i = 0, j = gap; j < n; i++, j++) {
                if(gap == 0)    
                    dp[i][j] = true;    // (0, 0), (1, 1), ... (10, 10) - Single Letters case
                else if (gap == 1 && str.charAt(i) == str.charAt(j))
                    dp[i][j] = true;    // "AA", "BB" - Two letters
                else 
                    dp[i][j] = (str.charAt(i) == str.charAt(j) && dp[i + 1][j - 1]);
            }
        }
        return dp;
    }
    
    //Leetcode 5. Longest Palindromic Substring [SAME AS isPlaindromeSubstring()]
    public String longestPalindrome(String str) {
        int n = str.length();
        if(n == 0)  return "";  // Testcase: ""
        int[][] dp = new int[n][n];

        int maxLen = 0; int si = 0, ei = 0; 
        for(int gap = 0; gap < n; gap++) {
            for(int i = 0, j = gap; j < n; i++, j++) {
                if(gap == 0)    
                    dp[i][j] = 1;    // (0, 0), (1, 1), ... (10, 10) Single Letters case
                else if (gap == 1 && str.charAt(i) == str.charAt(j))
                    dp[i][j] = 2;
                else if (str.charAt(i) == str.charAt(j) && dp[i + 1][j - 1] != 0)
                    dp[i][j] = gap + 1;
                if(dp[i][j] > maxLen) {
                    maxLen = dp[i][j];
                    si = i; ei = j;
                }
            }
        }
        return str.substring(si, ei + 1);   //(ei + 1) as ei is included
    }

    //Leetcode 647. Palindromic Substrings  [SAME AS isPlaindromeSubstring()]
    public int countSubstrings(String str) {
        int n = str.length();
        if(n == 0)  return 0;  // Testcase: ""
        int[][] dp = new int[n][n];

        int count = 0;
        for(int gap = 0; gap < n; gap++) {
            for(int i = 0, j = gap; j < n; i++, j++) {
                if(gap == 0)    
                    dp[i][j] = 1;    // (0, 0), (1, 1), ... (10, 10) Single Letters case
                else if (gap == 1 && str.charAt(i) == str.charAt(j))
                    dp[i][j] = 2;
                else if (str.charAt(i) == str.charAt(j) && dp[i + 1][j - 1] != 0)
                    dp[i][j] = gap + 1;
                
                count += dp[i][j] != 0 ? 1 : 0;
            }
        }
        return count;   //(ei + 1) as ei is included
    }


    

    public static void stringSubstringSet()
    {
        // String str = "abccbefgpgf";
        String str = "geeksforgeeks";
        int n = str.length();
        int si = 0, ei = n - 1;
        boolean[][] isPlalindrome = isPlaindromeSubstring(str);
        // for(boolean[] bArr : dp) 
        //     System.out.println(Arrays.toString(bArr));
        int[][] dp = new int[n][n];
        System.out.println(longestPalindromeSubseq_Rec(str, si, ei, dp, isPlalindrome));
        display2D(dp);

        int[][] dpT = new int[n][n];
        System.out.println(longestPalindromeSubseq_DP(str, si, ei, dpT, isPlalindrome));
        display2D(dpT);
        
        // int si = 0, ei = n - 1;


        // boolean[][] isPlalindrome = isPlaindromeSubstring(str);
        // cout << longestPlaindromeSubstring("abcaacbefgpgf") << endl;

        // cout << longestPlaindromeSubseq_Rec(str, si, ei, dp, isPlalindrome) << endl;
        // cout << longestPlaindromeSubseq_DP(str, si, ei, dp, isPlalindrome) << endl;

        // display2D(dp);

        // numDistinct("geeksforgeeks", "gks");

        // cout << longestCommonSubsequence("abc", "aa") << endl;
    }
    static void display2D(int[][] array) {
        for(int[] ar : array)
            System.out.println(Arrays.toString(ar));
    }
    public static void main(String[] args) {
        // friends_pairing_problem(10);
        // goldMine();
        // count_of_waysMAIN();
        stringSubstringSet();
    }
}