import java.util.Arrays;

public class leetcode {
    // Leetcode 70. Climbing Stairs
    public int climbStairs(int n) {
        // return climbStairs_(0, n);
        // int[] dp = new int[n + 1];
        // int ans = climbStairs_Tab(0, n, dp);
        // for(int ele : dp)
        // System.out.print(ele + " ");
        // return ans;
        return climbStairs_Btr(n);
    }

    private int climbStairs_(int st, int n) {
        if (st == n)
            return 1;

        int count = 0;
        for (int steps = 1; steps <= 2; steps++)
            if (st + steps <= n)
                count += climbStairs_(st + steps, n);
        return count;
    }

    private int climbStairs_Mem(int st, int n, int[] dp) {
        if (st == n)
            return dp[st] = 1;
        if (dp[st] != 0)
            return dp[st];

        int count = 0;
        for (int steps = 1; steps <= 2; steps++)
            if (st + steps <= n)
                count += climbStairs_Mem(st + steps, n, dp);
        return dp[st] = count;
    }

    private int climbStairs_Tab(int st, int n, int[] dp) {
        for (st = n; st >= 0; st--) {
            if (st == n) {
                dp[st] = 1;
                continue;
            }

            int count = 0;
            for (int steps = 1; steps <= 2; steps++)
                if (st + steps <= n)
                    count += dp[st + steps]; // climbStairs_(st + steps, n);
            dp[st] = count;
        }
        return dp[0];
    }

    private int climbStairs_Btr(int n) {
        if (n <= 1)
            return 1;
        int a = 1, b = 1, sum = 0;
        for (int i = 2; i <= n; i++) {
            sum = a + b;
            a = b;
            b = sum;
        }
        return sum;
    }

    // Leetcode 746. Min Cost Climbing Stairs [Same Approach as Fib]
    private int minCostClimbingStairs(int n, int[] dp, int[] cost) {
        if (n <= 1)
            return dp[n] = cost[n];
        if (dp[n] != 0)
            return dp[n];

        int ans = Math.min(minCostClimbingStairs(n - 1, dp, cost), minCostClimbingStairs(n - 2, dp, cost));
        return dp[n] = ans + (n != cost.length ? cost[n] : 0); // Managing cost[n] as 0
    }

    private int minCostClimbingStairsDP(int n, int[] dp, int[] cost) {
        int N = n;
        for (n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = cost[n];
                continue;
            }

            int ans = Math.min(dp[n - 1], dp[n - 2]);
            dp[n] = ans + (n != cost.length ? cost[n] : 0); // Managing cost[n] as 0
        }
        return dp[N];
    }

    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;

        int[] dp = new int[n + 1];
        return minCostClimbingStairs(n, dp, cost);
    }

    // 25 June
    // https://practice.geeksforgeeks.org/problems/friends-pairing-problem/0
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
        if (n <= 1)
            return dp[n] = 1;
        int single = friends_pairing_problemMEM(n - 1, dp);
        int pairUp = (n - 1) * friends_pairing_problemMEM(n - 2, dp); // ways in which remaining (n - 1) friends comes
                                                                      // if 1 remains single
        return dp[n] = (single + pairUp);
    }

    private static int friends_pairing_problemTab(int n, int[] dp) {
        int N = n;
        for (n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = 1;
                continue;
            }
            int single = dp[n - 1]; // friends_pairing_problemMEM(n - 1, dp);
            int pairUp = (n - 1) * dp[n - 2]; // (n - 1) * friends_pairing_problemMEM(n - 2, dp); // ways in which
                                              // remaining (n - 1) friends comes if 1 remains single
            dp[n] = (single + pairUp);
        }
        return dp[N];
    }

    private static int friends_pairing_problemTwoPointer(int n) {
        if (n <= 1)
            return n;
        int a = 1, b = 1, ans = 0;

        for (int i = 2; i <= n; i++) {
            ans = b + (a * (i - 1));
            a = b;
            b = ans;
        }
        return ans;
    }

    // Leetcode 64. Minimum Path Sum
    public int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        int val = minPathSum(0, 0, grid.length - 1, grid[0].length - 1, grid, dp);
        for (int[] ar : dp) {
            System.out.println(Arrays.toString(ar));
        }
        return val;
    }

    private int minPathSum(int sr, int sc, int er, int ec, int[][] grid, int[][] dp) {
        if (sr == er && sc == ec) {
            return dp[sr][sc] = grid[er][ec];
        }
        if (dp[sr][sc] != 0)
            return dp[sr][sc];

        int minCost = Integer.MAX_VALUE;

        if (sr + 1 <= er)
            minCost = Math.min(minCost, minPathSum(sr + 1, sc, er, ec, grid, dp));
        if (sc + 1 <= ec)
            minCost = Math.min(minCost, minPathSum(sr, sc + 1, er, ec, grid, dp));

        return dp[sr][sc] = minCost + grid[sr][sc];
    }

    private int minPathSumDP(int sr, int sc, int er, int ec, int[][] grid, int[][] dp) {
        for (sr = er; sr >= 0; sr--) {
            for (sc = ec; sc >= 0; sc--) {
                if (sr == er && sc == ec) {
                    dp[sr][sc] = grid[er][ec];
                    continue;
                }

                int minCost = Integer.MAX_VALUE;

                if (sr + 1 <= er)
                    minCost = Math.min(minCost, dp[sr + 1][sc]); // Math.min(minCost, minPathSum(sr + 1, sc, er, ec,
                                                                 // grid, dp));
                if (sc + 1 <= ec)
                    minCost = Math.min(minCost, dp[sr][sc + 1]); // Math.min(minCost, minPathSum(sr, sc + 1, er, ec,
                                                                 // grid, dp));

                dp[sr][sc] = minCost + grid[sr][sc];
            }
        }
        return dp[0][0];
    }

    // GFG: https://www.geeksforgeeks.org/gold-mine-problem/
    public static void goldMine() {
        int[][] grid = { { 1, 3, 1, 5 }, { 2, 2, 4, 1 }, { 5, 0, 2, 3 }, { 0, 6, 1, 2 } };
        int[][] dp = new int[grid.length][grid[0].length];
        int maxVal = 0;
        for (int i = 0; i < grid.length; i++) {
            maxVal = Math.max(maxVal, goldMine(i, 0, grid, dp));
        }
        System.out.println(maxVal);
        for (int[] ar : dp)
            System.out.println(Arrays.toString(ar));

        // DP
        int[][] dpT = new int[grid.length][grid[0].length];
        System.out.println(goldMine_DP(grid, dpT));
        for (int[] ar : dpT)
            System.out.println(Arrays.toString(ar));
    }

    private static int goldMine(int sr, int sc, int[][] grid, int[][] dp) {
        if (sc == grid[0].length - 1)
            return dp[sr][sc] = grid[sr][sc];
        if (dp[sr][sc] != 0)
            return dp[sr][sc];

        int maxCost = 0;
        int[][] dir = { { -1, 1 }, { 0, 1 }, { 1, 1 } };
        for (int d = 0; d < 3; d++) {
            int x = sr + dir[d][0];
            int y = sc + dir[d][1];

            if (x >= 0 && y >= 0 && x <= grid.length - 1 && y <= grid[0].length - 1)
                maxCost = Math.max(maxCost, goldMine(x, y, grid, dp));
        }
        return dp[sr][sc] = maxCost + grid[sr][sc];
    }

    private static int goldMine_DP(int[][] grid, int[][] dp) {
        for (int sc = grid[0].length - 1; sc >= 0; sc--) {
            for (int sr = grid.length - 1; sr >= 0; sr--) {
                if (sc == grid[0].length - 1) {
                    dp[sr][sc] = grid[sr][sc];
                    continue;
                }

                int maxCost = 0;
                int[][] dir = { { -1, 1 }, { 0, 1 }, { 1, 1 } };
                for (int d = 0; d < 3; d++) {
                    int x = sr + dir[d][0];
                    int y = sc + dir[d][1];

                    if (x >= 0 && y >= 0 && x <= grid.length - 1 && y <= grid[0].length - 1)
                        maxCost = Math.max(maxCost, dp[x][y]);
                }
                dp[sr][sc] = maxCost + grid[sr][sc];
            }
        }
        int maxValDP = 0;
        for (int i = 0; i < grid.length; i++) {
            maxValDP = Math.max(maxValDP, dp[i][0]);
        }
        return maxValDP;
    }

    // GFG:
    // https://www.geeksforgeeks.org/count-number-of-ways-to-partition-a-set-into-k-subsets/
    public static void count_of_waysMAIN() {
        int n = 7, k = 3;
        int[][] dpM = new int[k + 1][n + 1]; // TABLE: k -> rows, n -> cols
        System.out.println(count_of_ways(n, k));
        System.out.println(count_of_waysMEM(n, k, dpM));
        display2D(dpM);

        int[][] dpT = new int[k + 1][n + 1]; // TABLE: k -> rows, n -> cols
        System.out.println(count_of_waysTAB(n, k, dpT));
        display2D(dpT);
    }

    public static int count_of_ways(int n, int k) {
        if (n < k)
            return 0;
        if (n == k || k == 1)
            return 1;
        int newGroup = count_of_ways(n - 1, k - 1); // as n is coming alone so k - 1 groups can be made
        int existingGroup = count_of_ways(n - 1, k) * k; // based on tree observation
        return newGroup + existingGroup;
    }

    public static int count_of_waysMEM(int n, int k, int[][] dp) {
        if (n < k)
            return 0;
        if (n == k || k == 1)
            return dp[k][n] = 1;
        if (dp[k][n] != 0)
            return dp[k][n];
        int newGroup = count_of_waysMEM(n - 1, k - 1, dp); // as n is coming alone so k - 1 groups can be made
        int existingGroup = count_of_waysMEM(n - 1, k, dp) * k; // based on tree observation
        return dp[k][n] = newGroup + existingGroup;
    }

    public static int count_of_waysTAB(int n, int k, int[][] dp) {
        int K = k, N = n;
        for (k = 1; k <= K; k++) { // loop starts from k = 1 as dp[k-1] row can't be access if loop starts from 0
            for (n = 0; n <= N; n++) {
                if (n < k) {
                    continue;
                }
                if (n == k || k == 1) {
                    dp[k][n] = 1;
                    continue;
                }
                int newGroup = dp[k - 1][n - 1]; // count_of_waysTAB(n - 1, k - 1, dp); //as n is coming alone so k - 1
                                                 // groups can be made
                int existingGroup = dp[k][n - 1] * k; // count_of_waysTAB(n - 1, k, dp) * k; //based on tree observation
                dp[k][n] = newGroup + existingGroup;
            }
        }
        return dp[K][N];
    }

    // Practice Link:
    // https://practice.geeksforgeeks.org/problems/mobile-numeric-keypad/0

    // 28 June
    // Substring and Subsequence
    // Series.==================================================
    public static boolean[][] isPlaindromeSubstring(String str) {
        int n = str.length();
        boolean[][] dp = new boolean[n][n];
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (gap == 0)
                    dp[i][j] = true; // (0, 0), (1, 1), ... (10, 10) - Single Letters case
                else if (gap == 1 && str.charAt(i) == str.charAt(j))
                    dp[i][j] = true; // "AA", "BB" - Two letters
                else
                    dp[i][j] = (str.charAt(i) == str.charAt(j) && dp[i + 1][j - 1]);
            }
        }
        return dp;
    }

    // Leetcode 5. Longest Palindromic Substring [SAME AS isPlaindromeSubstring()]
    public String longestPalindrome(String str) {
        int n = str.length();
        if (n == 0)
            return ""; // Testcase: ""
        int[][] dp = new int[n][n];

        int maxLen = 0;
        int si = 0, ei = 0;
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (gap == 0)
                    dp[i][j] = 1; // (0, 0), (1, 1), ... (10, 10) Single Letters case
                else if (gap == 1 && str.charAt(i) == str.charAt(j))
                    dp[i][j] = 2;
                else if (str.charAt(i) == str.charAt(j) && dp[i + 1][j - 1] != 0)
                    dp[i][j] = gap + 1;
                if (dp[i][j] > maxLen) {
                    maxLen = dp[i][j];
                    si = i;
                    ei = j;
                }
            }
        }
        return str.substring(si, ei + 1); // (ei + 1) as ei is included
    }

    // Leetcode 647. Palindromic Substrings [SAME AS isPlaindromeSubstring()]
    public int countSubstrings(String str) {
        int n = str.length();
        if (n == 0)
            return 0; // Testcase: ""
        int[][] dp = new int[n][n];

        int count = 0;
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (gap == 0)
                    dp[i][j] = 1; // (0, 0), (1, 1), ... (10, 10) Single Letters case
                else if (gap == 1 && str.charAt(i) == str.charAt(j))
                    dp[i][j] = 2;
                else if (str.charAt(i) == str.charAt(j) && dp[i + 1][j - 1] != 0)
                    dp[i][j] = gap + 1;

                count += dp[i][j] != 0 ? 1 : 0;
            }
        }
        return count; // (ei + 1) as ei is included
    }

    // Leetcode 516. Longest Palindromic Subsequence
    public static int longestPalindromeSubseq_Rec(String str, int si, int ei, int[][] dp, boolean[][] isPallin) {
        if (isPallin[si][ei])
            return dp[si][ei] = ei - si + 1;
        if (dp[si][ei] != 0)
            return dp[si][ei];

        int len = 0;
        if (str.charAt(si) == str.charAt(ei))
            len = longestPalindromeSubseq_Rec(str, si + 1, ei - 1, dp, isPallin) + 2;
        else
            len = Math.max(longestPalindromeSubseq_Rec(str, si + 1, ei, dp, isPallin),
                    longestPalindromeSubseq_Rec(str, si, ei - 1, dp, isPallin));

        return dp[si][ei] = len;
    }

    public static int longestPalindromeSubseq_DP(String str, int si, int ei, int[][] dp, boolean[][] isPallin) {
        for (int gap = 0; gap < str.length(); gap++) {
            for (si = 0, ei = gap; ei < str.length(); si++, ei++) {
                if (isPallin[si][ei]) {
                    dp[si][ei] = ei - si + 1;
                    continue;
                }

                int len = 0;
                if (str.charAt(si) == str.charAt(ei))
                    len = dp[si + 1][ei - 1] + 2; // longestPalindromeSubseq_Rec(str, si + 1, ei - 1, dp, isPallin) + 2;
                else
                    len = Math.max(dp[si + 1][ei], dp[si][ei - 1]); // Math.max(longestPalindromeSubseq_Rec(str, si + 1,
                                                                    // ei, dp, isPallin),
                                                                    // longestPalindromeSubseq_Rec(str, si, ei - 1, dp,
                                                                    // isPallin));

                dp[si][ei] = len;
            }
        }
        return dp[0][str.length() - 1];
    }

    // Leetcode 115. Distinct Subsequences [code via length, dryrun thru index]
    public static int numDistinct(String s, String t, int n, int m) { // n -> len of Main str i.e s
        if (m == 0)
            return 1;
        if (m > n)
            return 0;
        int count = 0;
        if (s.charAt(n - 1) == t.charAt(m - 1))
            count += numDistinct(s, t, n - 1, m - 1) + numDistinct(s, t, n - 1, m);
        else
            count += numDistinct(s, t, n - 1, m);
        return count;
    }

    // in output of numDistinct_Mem() -1 tells that wahan pe kabhi geya hai ni
    public static int numDistinct_Mem(String s, String t, int n, int m, int[][] dp) { // n -> len of Main str i.e s
        if (m == 0)
            return dp[n][m] = 1;
        if (m > n)
            return dp[n][m] = 0;
        if (dp[n][m] != -1)
            return dp[n][m];
        int count = 0;
        if (s.charAt(n - 1) == t.charAt(m - 1))
            count += numDistinct_Mem(s, t, n - 1, m - 1, dp) + numDistinct_Mem(s, t, n - 1, m, dp);
        else
            count += numDistinct_Mem(s, t, n - 1, m, dp);
        return dp[n][m] = count;
    }

    public static int numDistinct_DP(String s, String t, int n, int m, int[][] dp) { // n -> len of Main str i.e s
        int N = n, M = m;
        for (n = 0; n <= N; n++) {
            for (m = 0; m <= M; m++) {
                if (m == 0) {
                    dp[n][m] = 1;
                    continue;
                }
                if (m > n) {
                    dp[n][m] = 0;
                    continue;
                }

                int count = 0;
                if (s.charAt(n - 1) == t.charAt(m - 1))
                    count += dp[n - 1][m - 1] + dp[n - 1][m]; // numDistinct_Mem(s, t, n - 1, m - 1, dp) +
                                                              // numDistinct_Mem(s, t, n - 1, m, dp);
                else
                    count += dp[n - 1][m]; // numDistinct_Mem(s, t, n - 1, m, dp);
                dp[n][m] = count;

            }
        }
        return dp[N][M];
    }

    // GFG:
    // https://practice.geeksforgeeks.org/problems/count-palindromic-subsequences/1
    int countPS(String str, int i, int j, int[][] dp) {
        if (i > j)
            return 0;
        if (i == j)
            return dp[i][j] = 1;
        if (dp[i][j] != 0)
            return dp[i][j];
        int middleString = countPS(str, i + 1, j - 1, dp);
        int excludingFirst = countPS(str, i + 1, j, dp);
        int excludingLast = countPS(str, i, j - 1, dp);
        int ans = excludingFirst + excludingLast;
        return dp[i][j] = (ans + (str.charAt(i) == str.charAt(j) ? 1 : -middleString));
    }

    int countPSTab(String str, int i, int j, int[][] dp) {
        int n = str.length();
        for (int gap = 0; gap < n; gap++) {
            for (i = 0, j = gap; j < n; i++, j++) {
                if (i > j)
                    return 0;
                if (i == j) {
                    dp[i][j] = 1;
                    continue;
                }
                int middleString = dp[i + 1][j - 1]; // countPS(str, i + 1, j - 1, dp);
                int excludingFirst = dp[i + 1][j]; // countPS(str, i + 1, j, dp);
                int excludingLast = dp[i][j - 1]; // countPS(str, i, j - 1, dp);
                int ans = excludingFirst + excludingLast;
                dp[i][j] = (ans + (str.charAt(i) == str.charAt(j) ? 1 : -middleString));
            }
        }
        return dp[0][n - 1];
    }

    // 30 June
    // Leetcode 1143. Longest Common Subsequence
    public static int longestCommonSubsequence(String text1, String text2, int i, int j, int[][] dp) {
        if (i == text1.length() || j == text2.length())
            return 0;
        if (dp[i][j] != 0)
            return dp[i][j];
        int ans = 0;
        if (text1.charAt(i) == text2.charAt(j))
            ans = longestCommonSubsequence(text1, text2, i + 1, j + 1, dp) + 1;
        else
            ans = Math.max(longestCommonSubsequence(text1, text2, i + 1, j, dp),
                    longestCommonSubsequence(text1, text2, i, j + 1, dp));
        return dp[i][j] = ans;
    }

    public static int longestCommonSubsequence_Tab(String text1, String text2, int i, int j, int[][] dp) {
        // as base case hits at length() so we start loop from there
        for (i = text1.length(); i >= 0; i--) {
            for (j = text2.length(); j >= 0; j--) {
                if (i == text1.length() || j == text2.length())
                    continue;
                int ans = 0;
                if (text1.charAt(i) == text2.charAt(j))
                    ans = dp[i + 1][j + 1] + 1;
                else
                    ans = Math.max(dp[i + 1][j], dp[i][j + 1]);
                dp[i][j] = ans;
            }
        }
        return dp[0][0];
    }

    static int max_ = 0;

    public static int longestCommonSubstring(String text1, String text2, int i, int j, int[][] dp) {
        if (i == text1.length() || j == text2.length())
            return 0;
        if (dp[i][j] != 0)
            return dp[i][j];
        longestCommonSubstring(text1, text2, i + 1, j, dp); // these calls are written above if block as in line 501 pgm
                                                            // returns
        longestCommonSubstring(text1, text2, i, j + 1, dp);
        if (text1.charAt(i) == text2.charAt(j)) {
            int a = longestCommonSubstring(text1, text2, i + 1, j + 1, dp) + 1;
            max_ = Math.max(max_, a);
            return dp[i][j] = a;
        }
        return 0;
    }

    public static int longestCommonSubstring_DP(String text1, String text2, int i, int j, int[][] dp) {
        int max_ = 0;
        for (i = text1.length(); i >= 0; i--) {
            for (j = text2.length(); j >= 0; j--) {
                if (i == text1.length() || j == text2.length())
                    continue;

                // lowestCommonSubstring(text1, text2, i + 1, j, dp); These calls aren't
                // required here
                // lowestCommonSubstring(text1, text2, i, j + 1, dp);
                if (text1.charAt(i) == text2.charAt(j)) {
                    int a = dp[i + 1][j + 1] + 1; // lowestCommonSubstring(text1, text2, i + 1, j + 1, dp) + 1;
                    max_ = Math.max(max_, a);
                    dp[i][j] = a;
                }
            }
        }
        return max_;
    }

    // Leetcode 1035. Uncrossed Lines
    public int maxUncrossedLines(int[] A, int[] B) {
        int n = A.length, m = B.length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = n; i >= 0; i--) {
            for (int j = m; j >= 0; j--) {
                // for(int i = n - 1; i >= 0; i--) { //this also works and faster as we are
                // jumping the base case and don't require it anymore
                // for(int j = m - 1; j >= 0; j--) {
                if (i == n || j == m)
                    continue;
                int ans = 0;
                if (A[i] == B[j]) {
                    ans = dp[i + 1][j + 1] + 1;
                } else {
                    ans = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
                dp[i][j] = ans;
            }
        }
        return dp[0][0];
    }

    // Leetcode 1458. Max Dot Product of Two Subsequences
    public int maxDotProduct(int[] nums1, int[] nums2) { // SAME AS LCS [3 calls sol]
        int n = nums1.length, m = nums2.length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = n; i >= 0; i--) {
            for (int j = m; j >= 0; j--) {
                if (i == n || j == m) {
                    dp[i][j] = (int) -1e8;
                    continue;
                }
                int val = nums1[i] * nums2[j]; // if alone the multi of two digits is max
                int a = dp[i + 1][j + 1] + val; // i == j
                int b = dp[i + 1][j];
                int c = dp[i][j + 1];
                dp[i][j] = Math.max(Math.max(val, a), Math.max(b, c));
            }
        }
        return dp[0][0];
    }

    public static void longestCommonSubsequence(String text1, String text2) {
        int n = text1.length(), m = text2.length();
        int[][] dp = new int[n + 1][m + 1];
        // System.out.println(longestCommonSubsequence(text1, text2, 0, 0, dp));
        // display2D(dp);
        // int[][] dpT = new int[n + 1][m + 1];
        // System.out.println(longestCommonSubsequence_Tab(text1, text2, 0, 0, dpT));
        // display2D(dpT);
        longestCommonSubstring(text1, text2, 0, 0, dp);
        System.out.println(max_);
        display2D(dp);
        int[][] dpT = new int[n + 1][m + 1];
        System.out.println(longestCommonSubstring_DP(text1, text2, 0, 0, dpT));
        display2D(dpT);
    }

    public static void stringSubstringSet() {
        // String str = "abccbefgpgf";
        String str = "geeksforgeeks";
        int n = str.length();
        // int si = 0, ei = n - 1;
        // boolean[][] isPlalindrome = isPlaindromeSubstring(str);
        // // for(boolean[] bArr : dp)
        // // System.out.println(Arrays.toString(bArr));
        // int[][] dp = new int[n][n];
        // System.out.println(longestPalindromeSubseq_Rec(str, si, ei, dp,
        // isPlalindrome));
        // display2D(dp);

        // int[][] dpT = new int[n][n];
        // System.out.println(longestPalindromeSubseq_DP(str, si, ei, dpT,
        // isPlalindrome));
        // display2D(dpT);

        String t = "gks";
        int m = t.length();
        System.out.println(numDistinct(str, t, n, m));
        int[][] dp = new int[n + 1][m + 1]; // new int[n + 1][m + 1]; as if charAt(m - 1) can't be negative
        for (int[] arr : dp) // IMPORTANT
            Arrays.fill(arr, -1);
        System.out.println(numDistinct_Mem(str, t, n, m, dp));
        display2D(dp);

        int[][] dpT = new int[n + 1][m + 1];
        for (int[] arr : dpT) // IMPORTANT
            Arrays.fill(arr, -1);
        System.out.println(numDistinct_DP(str, t, n, m, dpT));
        display2D(dpT);
    }

    // Coin_Change/Target_Type.===================================================================================
    static int coinChangePermutation(int[] arr, int tar, int[] dp) {
        if (tar == 0)
            return dp[tar] = 1;
        if (dp[tar] != 0)
            return dp[tar];
        int count = 0;
        for (int ele : arr)
            if (tar - ele >= 0)
                count += coinChangePermutation(arr, tar - ele, dp);
        return dp[tar] = count;
    }

    static int coinChangePermutation_DP(int[] arr, int tar, int[] dp) {
        dp[0] = 1;
        int TAR = tar;
        for (tar = 1; tar <= TAR; tar++) {
            int count = 0;
            for (int ele : arr)
                if (tar - ele >= 0)
                    count += dp[tar - ele]; // coinChangePermutation(arr, tar - ele, dp);
            dp[tar] = count;
        }
        return dp[TAR];
    }

    static int coinChangeCombination_DP(int[] arr, int tar, int[] dp) { // SAME AS coinChangePermutation_DP() with swap
                                                                        // of for loops
        dp[0] = 1;
        int TAR = tar;
        for (int ele : arr)
            for (tar = ele; tar <= TAR; tar++) // observation tells us tar = ele for efficiency
                dp[tar] += dp[tar - ele]; // coinChangePermutation(arr, tar - ele, dp);

        return dp[TAR];
    }

    // GFG:
    // https://www.geeksforgeeks.org/find-number-of-solutions-of-a-linear-equation-of-n-variables/
    static int linearEquation_DP(int[] coeff, int rhs) { // SAME AS coinChangeCombination_DP()
        int[] dp = new int[rhs + 1];
        dp[0] = 1;
        int TAR = rhs;
        for (int ele : coeff)
            for (rhs = ele; rhs <= TAR; rhs++)
                dp[rhs] += dp[rhs - ele];

        return dp[TAR];
    }

    // 2nd July
    // Leetcode 322. Coin Change
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        int ans = coinChange_(coins, amount, dp);
        System.out.println(Arrays.toString(dp));
        return ans != (int) 1e8 ? ans : 0;
    }

    public static int coinChange_(int[] coins, int tar, int[] dp) {
        if (tar == 0)
            return 0;

        if (dp[tar] != 0)
            return dp[tar];
        int minHeight = (int) 1e8;
        for (int ele : coins) {
            if (tar - ele >= 0) {
                int rMinHeight = coinChange_(coins, tar - ele, dp);
                if (rMinHeight != (int) 1e8 && rMinHeight + 1 < minHeight)
                    minHeight = rMinHeight + 1;
            }
        }
        return dp[tar] = minHeight;
    }

    // one coin at a time
    // Using subsequence method
    static int targetSum(int[] coins, int idx, int tar, int[][] dp, String ans) {
        if (tar == 0 || idx == coins.length) {
            if (tar == 0)
                System.out.println(ans);
            if (tar == 0)
                return dp[idx][tar] = 1;
            return 0;
        }
        if (dp[idx][tar] != 0)
            return dp[idx][tar];
        int count = 0;
        if (tar - coins[idx] >= 0)
            count += targetSum(coins, idx + 1, tar - coins[idx], dp, ans + coins[idx] + " ");
        count += targetSum(coins, idx + 1, tar, dp, ans);
        return dp[idx][tar] = count;
    }

    static int targetSum02(int[] coins, int idx, int tar, int[][] dp) {
        if (tar == 0 || idx == 0) {
            if (tar == 0)
                return dp[idx][tar] = 1;
            return 0;
        }
        if (dp[idx][tar] != 0)
            return dp[idx][tar];
        int count = 0;
        if (tar - coins[idx - 1] >= 0)
            count += targetSum02(coins, idx - 1, tar - coins[idx - 1], dp);
        count += targetSum02(coins, idx - 1, tar, dp);
        return dp[idx][tar] = count;
    }

    static void targetSum_02DP(int[] coins, int tar) {
        int TAR = tar;
        boolean[][] dp = new boolean[coins.length + 1][tar + 1];
        for (int idx = 0; idx <= coins.length; idx++) {
            for (tar = 0; tar <= TAR; tar++) {
                if (tar == 0 || idx == 0) {
                    if (tar == 0)
                        dp[idx][tar] = true;
                    continue;
                }
                if (tar - coins[idx - 1] >= 0)
                    dp[idx][tar] = dp[idx - 1][tar - coins[idx - 1]]; // targetSum02(coins, idx - 1, tar - coins[idx],
                                                                      // dp);
                dp[idx][tar] = dp[idx][tar] || dp[idx - 1][tar];
            }
        }
        for (boolean[] ar : dp) {
            for (boolean a : ar)
                System.out.print(a + "\t");
            System.out.println();
        }
        System.out.println(printPathOfTargetSum(coins, coins.length, TAR, "", dp));
    }

    static int printPathOfTargetSum(int[] coins, int idx, int tar, String ans, boolean[][] dp) // using targetSum_02DP
                                                                                               // trace the dp with
                                                                                               // jumping only to trues
                                                                                               // to fetch the answer
    {
        if (tar == 0 || idx == 0) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        // including the coin
        if (tar - coins[idx - 1] >= 0 && dp[idx - 1][tar - coins[idx - 1]]) // call only if dp[idx - 1][tar - coins[idx
                                                                            // - 1]] is true
            count += printPathOfTargetSum(coins, idx - 1, tar - coins[idx - 1], ans + coins[idx - 1] + " ", dp);

        if (dp[idx - 1][tar]) // excluding the coin
            count += printPathOfTargetSum(coins, idx - 1, tar, ans, dp);

        return count;
    }

    static void coinChange() {
        // int[] arr = {2, 3, 5, 7};
        // int tar = 10;
        // int[] dp = new int[tar + 1];
        // System.out.println(coinChangePermutation(arr, tar, dp));
        // System.out.println(Arrays.toString(dp));
        // int[] dpT = new int[tar + 1];
        // System.out.println(coinChangePermutation_DP(arr, tar, dpT));
        // System.out.println(Arrays.toString(dpT));
        // int[] dpTC = new int[tar + 1];
        // System.out.println(coinChangeCombination_DP(arr, tar, dpTC));
        // System.out.println(Arrays.toString(dpTC));
        // int[] coeff = {2, 2, 3};
        // int rhs = 4;
        // System.out.println(linearEquation_DP(coeff, rhs));
        // int[] arr = {1,2,5};
        // System.out.println(coinChange(arr, 11));
        int[] coins = { 2, 3, 5, 7 };
        int tar = 10;
        // int[][] dp = new int[coins.length + 1][tar + 1];
        // String ans = "";
        // // System.out.println(targetSum(coins, 0, tar, dp, ans));
        // System.out.println(targetSum02(coins, coins.length, tar, dp));
        // display2D(dp);
        // System.out.println(ans);
        targetSum_02DP(coins, tar);
    }

    // knapsack
    static int knapsack01(int[] weights, int[] price, int weight, int n, int[][] dp) {
        if (n == 0 || weight == 0) {
            return 0;
        }
        if (dp[n][weight] != 0)
            return dp[n][weight];
        int maxWeight = (int) -1e8;
        if (weight - weights[n - 1] >= 0)
            maxWeight = Math.max(maxWeight,
                    knapsack01(weights, price, weight - weights[n - 1], n - 1, dp) + price[n - 1]);
        maxWeight = Math.max(maxWeight, knapsack01(weights, price, weight, n - 1, dp));
        return dp[n][weight] = maxWeight;
    }

    static int unbounded(int[] weights, int[] price, int weight) {
        int[] dp = new int[weight + 1];
        Arrays.fill(dp, (int) -1e8);
        dp[0] = 0; // handling base case
        for (int i = 0; i < weights.length; i++)
            for (int tar = weights[i]; tar <= weight; tar++) {
                dp[tar] = Math.max(dp[tar], dp[tar - weights[i]] + price[i]);
            }
        System.out.println(Arrays.toString(dp));
        return dp[weight];
    }

    // Leetcode 416. Partition Equal Subset Sum [SAME AS TARGET SUM]
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int ele : nums)
            sum += ele;
        if (sum % 2 != 0) // odd case handled
            return false;
        sum /= 2; // finding the positive subset availability i.e finding one subset whose sum
                  // will be half
        int[][] dp = new int[nums.length + 1][sum + 1];
        for (int[] arr : dp)
            Arrays.fill(arr, -1);
        if (canPartition(nums, nums.length, sum, dp) == 1)
            return true;
        return false;
    }

    public int canPartition(int[] nums, int idx, int tar, int[][] dp) {
        if (idx == 0 || tar == 0) {
            if (tar == 0)
                return dp[idx][tar] = 1;
            return dp[idx][tar] = 0;
        }
        if (dp[idx][tar] != -1)
            return dp[idx][tar];
        boolean res = false;
        if (tar - nums[idx - 1] >= 0)
            res = res || (canPartition(nums, idx - 1, tar - nums[idx - 1], dp) == 1);
        res = res || (canPartition(nums, idx - 1, tar, dp) == 1);
        return dp[idx][tar] = res ? 1 : 0;
    }

    // Leetcode 494. Target Sum
    public int findTargetSumWays(int[] nums, int S) {
        // return findTargetSumWays_rec(nums, nums.length, 0, S);
        int sum = 0;
        for (int num : nums)
            sum += num;
        Integer[][] cache = new Integer[nums.length + 1][2 * sum + 1];

        return dfs(nums, sum, S + sum, 0, cache);
    }

    public int findTargetSumWays_rec(int[] nums, int n, int Sum, int tar) {
        if (n == 0)
            return (tar == Sum) ? 1 : 0;
        int include = findTargetSumWays_rec(nums, n - 1, Sum + nums[n - 1], tar);
        int exclude = findTargetSumWays_rec(nums, n - 1, Sum - nums[n - 1], tar);
        return include + exclude;
    }

    private int dfs(int[] nums, int cur, int target, int idx, Integer[][] cache) { // working
        if (idx == nums.length) {
            return cur == target ? 1 : 0;
        }
        if (cache[idx][cur] != null) {
            return cache[idx][cur];
        }

        int res = 0;
        res = dfs(nums, cur + nums[idx], target, idx + 1, cache) + dfs(nums, cur - nums[idx], target, idx + 1, cache);

        cache[idx][cur] = res;
        return res;
    }

    public int findTargetSumWays_Mem(int[] nums, int n, int Sum, int tar, int[][] dp) { // not complete
        if (n == 0)
            return dp[n][Sum] = (tar == Sum) ? 1 : 0;
        if (dp[n][Sum] != 0)
            return dp[n][Sum];
        int include = findTargetSumWays_Mem(nums, n - 1, Sum + nums[n - 1], tar, dp);
        int exclude = findTargetSumWays_Mem(nums, n - 1, Sum - nums[n - 1], tar, dp);
        return dp[n][Sum] = include + exclude;
    }

    public int findTargetSumWays_DP(int[] nums, int Sum, int[][] dp) {// not complete
        dp[0][Sum] = 1;
        for (int i = 1; i <= nums.length; i++) {
            for (int k = 0; k < 2 * Sum; k++) {
                if (dp[i - 1][k] != 0) {
                    dp[i][k + nums[i - 1]] += dp[i - 1][k];
                    dp[i][k - nums[i - 1]] += dp[i - 1][k];
                }
            }
        }
        return dp[nums.length][Sum];
    }

    // Leetcode 72. Edit Distance
    public int minDistance(String word1, String word2, int n, int m, int[][] dp) {
        if (n == 0 || m == 0)
            return dp[n][m] = (n == 0 ? m : n);
        if (dp[n][m] != 0)
            return dp[n][m];
        if (word1.charAt(n - 1) == word2.charAt(m - 1))
            return dp[n][m] = minDistance(word1, word2, n - 1, m - 1, dp);
        int insert = minDistance(word1, word2, n, m - 1, dp) + 1;
        int replace = minDistance(word1, word2, n - 1, m - 1, dp) + 1;
        int delete = minDistance(word1, word2, n - 1, m, dp) + 1;
        return dp[n][m] = Math.min(Math.min(insert, delete), replace);
    }

    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        return minDistance(word1, word2, word1.length(), word2.length(), dp);
    }

    static void knapsack() {
        // int[] p = {100, 280, 120};
        // int[] w = {10, 40, 20};
        // int weight = 60;
        // int n = w.length;
        // int[][] dp = new int[n + 1][weight + 1];
        // System.out.println(knapsack01(w, p, weight, n, dp));
        int[] p = { 100, 280, 120 };
        int[] w = { 2, 4, 1 };
        int weight = 7;
        System.out.println(unbounded(w, p, weight));
    }

    // Leetcode 300. Longest Increasing Subsequence
    // LIS type - longest increasing subsequence
    // Faith: mere pe khatam hone wala sabse bada LIS kaunsa hai when trvaersing L
    // to R
    static int LIS_leftToRight(int[] arr, int[] dp) {
        int n = arr.length;
        int omax = 0; // overallMax
        for (int i = 0; i < n; i++) {
            dp[i] = 1; // each element has atleast 1 length [REMEMBER THIS LINE]
            for (int j = i - 1; j >= 0; j--) { // starting from back
                if (arr[j] < arr[i]) // change this condition to make it LDS from L to R
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
            }
            omax = Math.max(omax, dp[i]);
        }
        return omax;
    }

    // LDS - longest decreasing subsequence [SAME AS LIS only 1 change]
    static int LIS_rightToLeft(int[] arr, int[] dp) {
        int n = arr.length;
        int omax = 0;
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = 1;
            for (int j = i + 1; j < n; j++)
                if (arr[j] < arr[i]) // CHANGE THE SIGN
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            omax = Math.max(omax, dp[i]);
        }
        return omax;
    }

    // https://www.geeksforgeeks.org/longest-bitonic-subsequence-dp-15/
    // Longest Bitonic Subsequence
    static int lbs(int[] arr) {
        int n = arr.length;
        int[] LIS = new int[n];
        int[] LDS = new int[n];

        LIS_leftToRight(arr, LIS);
        LIS_rightToLeft(arr, LDS);

        int maxLen = 0;
        for (int i = 0; i < n; i++)
            maxLen = Math.max(maxLen, LIS[i] + LDS[i] - 1); // subtracting 1 coz of same digit to be counted twice

        return maxLen;
    }

    // GFG:
    // https://practice.geeksforgeeks.org/problems/maximum-sum-increasing-subsequence/0
    static int maximumIncreasingSumSubsequence(int[] arr, int[] dp) { // [SAME AS LIS]
        int n = arr.length;
        int omax = 0; // overallMax
        for (int i = 0; i < n; i++) {
            dp[i] = arr[i]; // each element has atleast 1 length [REMEMBER THIS LINE]
            for (int j = i - 1; j >= 0; j--) { // starting from back
                if (arr[j] < arr[i]) // change this condition to make it LDS from L to R
                    dp[i] = Math.max(dp[j] + arr[i], dp[i]);
            }
            omax = Math.max(omax, dp[i]);
        }
        return omax;
    }

    // https://practice.geeksforgeeks.org/problems/minimum-number-of-deletions-to-make-a-sorted-sequence/0
    // minimum no of deletion to make array in sorted order in increasing order.
    static int minDeletions(int[] arr) { // [SAME AS LIS]
        int n = arr.length;
        int[] dp = new int[n];
        int overallMax = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--)
                if (arr[j] <= arr[i]) // INCLUDED THE EQUAL TO CONDITION HERE
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            overallMax = Math.max(overallMax, dp[i]);
        }
        return n - overallMax;
    }

    // Leetcode 354. Russian Doll Envelopes
    public int maxEnvelopes(int[][] arr) {
        Arrays.sort(arr, (int[] a, int[] b) -> {
            if (a[0] == b[0]) // if widhts are same then we are choosing the taller envolpe
                return b[1] - a[1]; // other - this
            return a[0] - b[0]; // this - other. default
        });
        int n = arr.length;
        int[] dp = new int[n];
        int oMax = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--)
                if (arr[j][1] < arr[i][1]) // working with widths
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            oMax = Math.max(oMax, dp[i]);
        }
        return oMax;
    }

    // ---------------> // question undone :
    // //https://practice.geeksforgeeks.org/problems/maximum-sum-bitonic-subsequence/0

    // -------------> // for you : 1027, 1235 [HAVENT DONE]

    // Leetcode 1027. Longest Arithmetic Sequence [TRY AGAIN]
    public int longestArithSeqLength(int[] A) {
        if (A == null)
            return 0;
        if (A.length == 1)
            return 1;
        int hash[][] = new int[A.length][20000];
        int max = 0;
        for (int i = 1; i < A.length; i++)
            for (int j = i - 1; j >= 0; j--) {
                int diff = A[i] - A[j] + 10000;
                int counttillnow = hash[j][diff];
                if (hash[i][diff] > counttillnow)
                    continue;
                else {
                    hash[i][diff] = counttillnow + 1;
                    max = Math.max(max, counttillnow + 1);
                }
            }
        return max + 1;
    }

    // Leetcode 673. Number of Longest Increasing Subsequence
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int[] count = new int[n];
        int maxLength = 0;
        int maxCount = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            count[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    if (dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    } else if (dp[i] == dp[j] + 1) {
                        count[i] += count[j];
                    }
                }
            }
            if (maxLength < dp[i]) {
                maxLength = dp[i];
                maxCount = count[i];
            } else if (maxLength == dp[i]) {
                maxCount += count[i];
            }
        }
        return maxCount;
    }

    static void LIS_type() {
        int[] arr = { 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15, 8 };
        // int[] arr = {1, 11, 2, 10, 4, 5, 2, 1};
        // int[] dp = new int[arr.length];
        // System.out.println(LIS_leftToRight(arr, dp));
        // System.out.println(Arrays.toString(dp));

        // int[] dp2 = new int[arr.length];
        // System.out.println(LIS_rightToLeft(arr, dp2));
        // System.out.println(Arrays.toString(dp2));

        int[] dp3 = new int[arr.length];
        System.out.println(LIS_rightToLeft(arr, dp3));
        System.out.println(Arrays.toString(dp3));
    }

    // 7 July
    // cut type
    // FINDING THE MINIMUM COST OF MATRIX MULTIPLICATION
    static int MCM_rec(int[] arr, int si, int ei, int[][] dp) {
        if (si + 1 == ei)
            return dp[si][ei] = 0;
        if (dp[si][ei] != -1)
            return dp[si][ei];
        int ans = (int) 1e8;
        for (int cut = si + 1; cut < ei; cut++) { // pehla cut jahan se bhi marna hai
            int leftTree = MCM_rec(arr, si, cut, dp);
            int rightTree = MCM_rec(arr, cut, ei, dp);
            int myCost = leftTree + (arr[si] * arr[cut] * arr[ei]) + rightTree;
            if (ans > myCost)
                ans = myCost;
        }
        return dp[si][ei] = ans;
    }

    static int MCM_DP(int[] arr, int si, int ei, int[][] dp) {
        int n = arr.length;
        for (int gap = 1; gap < n; gap++) {
            for (si = 0, ei = gap; ei < n; si++, ei++) {
                if (si + 1 == ei) {
                    dp[si][ei] = 0;
                    continue;
                }

                int ans = (int) 1e8;
                for (int cut = si + 1; cut < ei; cut++) { // pehla cut jahan se bhi marna hai
                    int leftTree = dp[si][cut]; // MCM_rec(arr, si, cut, dp);
                    int rightTree = dp[cut][ei]; // MCM_rec(arr, cut, ei, dp);
                    int myCost = leftTree + (arr[si] * arr[cut] * arr[ei]) + rightTree;
                    if (ans > myCost)
                        ans = myCost;
                }
                dp[si][ei] = ans;
            }
        }
        return dp[0][n - 1];
    }

    static void MCM_DP_Ans(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        for (int[] ele : dp)
            Arrays.fill(ele, -1);
        String[][] sdp = new String[n][n];
        for (String[] ele : sdp)
            Arrays.fill(ele, "");
        for (int gap = 1; gap < n; gap++) {
            for (int si = 0, ei = gap; ei < n; si++, ei++) {
                if (si + 1 == ei) {
                    dp[si][ei] = 0;
                    sdp[si][ei] = (char) (si + 'A') + "";
                    continue;
                }

                int ans = (int) 1e8;
                String sans = "";
                for (int cut = si + 1; cut < ei; cut++) { // pehla cut jahan se bhi marna hai
                    int leftTree = dp[si][cut]; // MCM_rec(arr, si, cut, dp);
                    int rightTree = dp[cut][ei]; // MCM_rec(arr, cut, ei, dp);
                    int myCost = leftTree + (arr[si] * arr[cut] * arr[ei]) + rightTree;
                    if (ans > myCost) {
                        ans = myCost;
                        sans = "(" + sdp[si][cut] + sdp[cut][ei] + ")";
                    }
                }
                dp[si][ei] = ans;
                sdp[si][ei] = sans;
            }
        }
        System.out.println(dp[0][n - 1]);
        System.out.println(sdp[0][n - 1]);
        for (String[] ele : sdp)
            System.out.println(Arrays.toString(ele));
    }

    // https://www.geeksforgeeks.org/optimal-binary-search-tree-dp-24/
    // Optimal Binary Search Tree [CUT TYPE APPROACH]
    static int costOfSearching(int[] freq, int si, int ei) {
        int sum = 0;
        for (int i = si; i <= ei; i++)
            sum += freq[i];
        return sum;
    }

    static int OBST_REC(int[] freq, int si, int ei, int[][] dp) { // O(n^n * n)
        if (dp[si][ei] != 0)
            return dp[si][ei];
        int ans = (int) 1e8;
        for (int cut = si; cut <= ei; cut++) {
            int leftTree = (si == cut) ? 0 : OBST_REC(freq, si, cut - 1, dp);
            int rightTree = (ei == cut) ? 0 : OBST_REC(freq, cut + 1, ei, dp);

            int myCost = leftTree + costOfSearching(freq, si, ei) + rightTree;
            if (ans > myCost) {
                ans = myCost;
            }
        }
        return dp[si][ei] = ans;
    }

    static int OBST_DP(int[] freq) { // O(n^2)
        int n = freq.length;
        int[][] dp = new int[n][n];
        int[] prefixSum = new int[n + 1];
        for (int i = 1; i <= n; i++)
            prefixSum[i] = prefixSum[i - 1] + freq[i - 1];

        for (int gap = 0; gap < n; gap++) {
            for (int si = 0, ei = gap; ei < n; si++, ei++) {

                int ans = (int) 1e8;
                for (int cut = si; cut <= ei; cut++) {
                    int leftTree = (si == cut) ? 0 : dp[si][cut - 1]; // OBST_REC(freq, si, cut - 1, dp);
                    int rightTree = (ei == cut) ? 0 : dp[cut + 1][ei]; // OBST_REC(freq, cut + 1, ei, dp);

                    // int myCost = leftTree + costOfSearching(freq, si, ei) + rightTree;
                    int myCost = leftTree + (prefixSum[ei + 1] - prefixSum[si]) + rightTree;
                    if (ans > myCost) {
                        ans = myCost;
                    }
                }
                dp[si][ei] = ans;
            }
        }
        return dp[0][n - 1];
    }

    // Leetcode 312. Burst Balloons
    public int maxCoins(int[] nums, int si, int ei, int[][] dp) {
        if (dp[si][ei] != 0)
            return dp[si][ei];

        int n = nums.length;

        int lVal = (si == 0) ? 1 : nums[si - 1];
        int rVal = (ei == n - 1) ? 1 : nums[ei + 1];

        int ans = (int) -1e8;
        for (int cut = si; cut <= ei; cut++) {
            int leftTree = (cut == si) ? 0 : maxCoins(nums, si, cut - 1, dp);
            int rightTree = (cut == ei) ? 0 : maxCoins(nums, cut + 1, ei, dp);
            int myCost = leftTree + (lVal * nums[cut] * rVal) + rightTree;

            if (myCost > ans)
                ans = myCost;
        }
        return dp[si][ei] = ans;
    }

    static void optimalBinarySearchTree() {
        int[] keys = { 10, 12, 20 };
        int[] freq = { 34, 8, 50 };
        int n = freq.length;
        int[][] dp = new int[n][n];

        System.out.println(OBST_REC(freq, 0, n - 1, dp));
        display2D(dp);
        System.out.println(OBST_DP(freq));
    }

    static void cutType() {
        // int[] arr = {2, 3, 4, 5, 6, 7};
        // int n = arr.length;
        // int[][] dp = new int[n][n];
        // for(int[] ele: dp) Arrays.fill(ele, -1);
        // System.out.println(MCM_rec(arr, 0, n - 1, dp));
        // display2D(dp);

        // int[][] dp1 = new int[n][n];
        // for(int[] ele: dp1) Arrays.fill(ele, -1);
        // System.out.println(MCM_DP(arr, 0, n - 1, dp1));
        // display2D(dp1);
        // int[] arr = {3, 7, 2, 6, 5, 4};
        // MCM_DP_Ans(arr);

        optimalBinarySearchTree();
    }

    // 9 july
    // Leetcode 91. Decode Ways
    public static int numDecodings(String s, int vidx, int[] dp) {
        if (vidx == s.length())
            return 1;
        if (dp[vidx] != 0)
            return dp[vidx];
        char ch = s.charAt(vidx);
        if (ch == '0')
            return dp[vidx] = 0;
        int count = 0;
        count += numDecodings(s, vidx + 1, dp);
        if (vidx + 1 < s.length()) {
            int num = (ch - '0') * 10 + (s.charAt(vidx + 1) - '0');
            if (num <= 26)
                count += numDecodings(s, vidx + 2, dp);
        }
        return dp[vidx] = count;
    }

    public static int numDecodings_DP(String s) {
        int a = 0, b = 1;
        int ans = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            ans = 0;
            if (ch != '0') {
                ans = b;
                if (i + 1 < s.length()) {
                    int num = (ch - '0') * 10 + (s.charAt(i + 1) - '0');
                    if (num <= 26)
                        ans += a;
                }
            }
            a = b;
            b = ans;
        }
        return ans;
    }

    static int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        int ans = numDecodings(s, 0, dp);

        System.out.println(Arrays.toString(dp));
        return ans;
    }

    // GFG: Number of subsequences of the form a^i b^j c^k
    static int AiBjCk(String str) {
        int acount = 0, bcount = 0, ccount = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == 'a') {
                acount = acount + (1 + acount);
            } else if (ch == 'b') {
                bcount = bcount + (acount + bcount);
            } else {
                ccount = ccount + (bcount + ccount);
            }
        }
        return ccount;
    }

    // Modular arithmetic:
    // https://www.hackerearth.com/practice/math/number-theory/basic-number-theory-1/tutorial/

    // (a+b)%c = (a%c + b%c)%c
    // (a-b)%c = (a%c - b%c + c)%c
    // (a*b)%c = (a%c * b%c )%c
    // Leetcode 940. Distinct Subsequences II
    public int distinctSubseqII(String str) {
        int mod = (int) 1e9 + 7;
        str = '$' + str;
        int n = str.length();
        long[] dp = new long[n];
        int[] lastOccu = new int[26];
        Arrays.fill(lastOccu, -1);

        for (int i = 0; i < n; i++) {
            if (i == 0) // empty String.
            {
                dp[i] = 1;
                continue;
            }

            char ch = str.charAt(i);
            dp[i] = (dp[i - 1] % mod * 2) % mod;
            if (lastOccu[ch - 'a'] != -1)// duplicate wale ke last occurance ke ek pehle wale ko minus
                dp[i] = dp[i] % mod - dp[lastOccu[ch - 'a'] - 1] % mod + mod;

            lastOccu[ch - 'a'] = i;
        }
        return (int) dp[n - 1] % mod - 1;
    }

    // Leetcode 639. Decode Ways II
    static int m = (int) 1e9 + 7;

    public static long decodeWaysII(String s, int idx, long[] dp) {
        if (idx == s.length())
            return dp[idx] = 1;

        if (dp[idx] != 0)
            return dp[idx];

        long count = 0;
        char ch = s.charAt(idx);
        if (ch == '*') {
            count = (count + 9 * decodeWaysII(s, idx + 1, dp)) % m;// case 0
            if (idx + 1 < s.length()) {
                char ch2 = s.charAt(idx + 1);
                if (ch2 >= '0' && ch2 <= '6')
                    count = (count + 2 * decodeWaysII(s, idx + 2, dp)) % m; // case 3
                if (ch2 >= '7' && ch2 <= '9')
                    count = (count + decodeWaysII(s, idx + 2, dp)) % m;
                if (ch2 == '*')
                    count = (count + 15 * decodeWaysII(s, idx + 2, dp)) % m;// case 4
            }

        } else if (ch != '0') {
            count = (count + decodeWaysII(s, idx + 1, dp)) % m; // case 0

            if (idx + 1 < s.length()) {
                char ch2 = s.charAt(idx + 1);
                if (ch2 == '*') {
                    if (ch == '1')
                        count = (count + 9 * decodeWaysII(s, idx + 2, dp)) % m;// case 2
                    else if (ch == '2')
                        count = (count + 6 * decodeWaysII(s, idx + 2, dp)) % m;// case 2
                } else {
                    int val = (ch - '0') * 10 + (ch2 - '0'); // case 1
                    if (val <= 26)
                        count = (count + decodeWaysII(s, idx + 2, dp)) % m;
                }
            }
        }
        return dp[idx] = count;
    }

    public static long decodeWaysII_DP(String s, int idx, long[] dp) {
        int n = s.length();
        for (int i = n; i >= 0; i--) {
            if (idx == s.length()) {
                dp[idx] = 1;
                continue;
            }

            long count = 0;
            char ch = s.charAt(idx);
            if (ch == '*') {
                count = (count + 9 * dp[idx + 1]) % m; // decodeWaysII(s, idx + 1, dp)) % m;// case 0
                if (idx + 1 < s.length()) {
                    char ch2 = s.charAt(idx + 1);
                    if (ch2 >= '0' && ch2 <= '6')
                        count = (count + 2 * dp[idx + 2]) % m; // decodeWaysII(s, idx + 2, dp)) % m; // case 3
                    if (ch2 >= '7' && ch2 <= '9')
                        count = (count + dp[idx + 2]) % m; // decodeWaysII(s, idx + 2, dp)) % m;
                    if (ch2 == '*')
                        count = (count + 15 * dp[idx + 2]) % m; // decodeWaysII(s, idx + 2, dp)) % m;// case 4
                }

            } else if (ch != '0') {
                count = (count + dp[idx + 1]) % m; // decodeWaysII(s, idx + 1, dp)) % m; // case 0

                if (idx + 1 < s.length()) {
                    char ch2 = s.charAt(idx + 1);
                    if (ch2 == '*') {
                        if (ch == '1')
                            count = (count + 9 * dp[idx + 2]) % m; // decodeWaysII(s, idx + 2, dp)) % m;// case 2
                        else if (ch == '2')
                            count = (count + 6 * dp[idx + 2]) % m; // decodeWaysII(s, idx + 2, dp)) % m;// case 2
                    } else {
                        int val = (ch - '0') * 10 + (ch2 - '0'); // case 1
                        if (val <= 26)
                            count = (count + dp[idx + 2]) % m; // decodeWaysII(s, idx + 2, dp)) % m;
                    }
                }
            }
            dp[idx] = count;
        }
        return dp[0];
    }

    public long decodeWaysII_DP(String s, long[] dp) {  //FASTEST
        int n = s.length();
        long a = 0, b = 1, count = 0;  //change a : idx + 2; b : idx + 1
        for (int idx = n - 1; idx >= 0; idx--) {    //change the start point
            
            count = 0;
            char ch = s.charAt(idx);
            if (ch == '*') {
                count = (count + 9 * b) % m; // decodeWaysII(s, idx + 1, dp)) % m;// case 0
                if (idx + 1 < s.length()) {
                    char ch2 = s.charAt(idx + 1);
                    if (ch2 >= '0' && ch2 <= '6')
                        count = (count + 2 * a) % m; // decodeWaysII(s, idx + 2, dp)) % m; // case 3
                    if (ch2 >= '7' && ch2 <= '9')
                        count = (count + a) % m; // decodeWaysII(s, idx + 2, dp)) % m;
                    if (ch2 == '*')
                        count = (count + 15 * a) % m; // decodeWaysII(s, idx + 2, dp)) % m;// case 4
                }

            } else if (ch != '0') {
                count = (count + b) % m; // decodeWaysII(s, idx + 1, dp)) % m; // case 0

                if (idx + 1 < s.length()) {
                    char ch2 = s.charAt(idx + 1);
                    if (ch2 == '*') {
                        if (ch == '1')
                            count = (count + 9 * a) % m; // decodeWaysII(s, idx + 2, dp)) % m;// case 2
                        else if (ch == '2')
                            count = (count + 6 * a) % m; // decodeWaysII(s, idx + 2, dp)) % m;// case 2
                    } else {
                        int val = (ch - '0') * 10 + (ch2 - '0'); // case 1
                        if (val <= 26)
                            count = (count + a) % m; // decodeWaysII(s, idx + 2, dp)) % m;
                    }
                }
            }
            a = b;
            b = count;
        }
        return count;
    }

    // DP toDo :
    // 1. 132
    // 2. 044
    // 3. https://www.geeksforgeeks.org/boolean-parenthesization-problem-dp-37/
    // 4. 096
    // 5. 095
    //Leetcode 44. Wildcard Matching    [TO-DO]
    public boolean isMatch(String s, String p) {
        p=removeStar(p);
		int[][] dp=new int[s.length()+1][p.length()+1];
		for(int i=0;i<s.length();i++) for(int j=0;j<p.length();j++) dp[i][j]=-1;
		return  wildCard_rec(s,p,0,0,dp)==1;
    }
    public int wildCard_rec(String s,String p,int i,int j,int[][] dp){
		if(i==s.length() && j==p.length()) return dp[i][j]=1;
		if(i==s.length() || j==p.length()){
			if( i!=s.length() ) return  dp[i][j]=0;
			
			return dp[i][j]= (p.charAt(j)=='*' && p.length() - j == 1)?1:0;
		}

		if(dp[i][j]!=-1) return dp[i][j];

		char ch1=s.charAt(i);
		char ch2=p.charAt(j);
		boolean res=false;
		if(ch1 == ch2 || ch2 == '?') res = wildCard_rec(s,p,i+1,j+1,dp)==1;
		else if(ch2=='*'){
			res = res || wildCard_rec(s,p,i,j+1,dp)==1;  // as a empty string mapping ('*' treated as a "").
			res = res || wildCard_rec(s,p,i+1,j,dp)==1;  // sequence mapping.("*" treated as a substring).
		}

		return dp[i][j]=res?1:0;
	}

	public String removeStar(String str){
	  StringBuilder sb=new StringBuilder();
	  boolean firstStar=false;
	  for(int i=0;i<str.length();i++){
		char ch=str.charAt(i);  
		if(ch=='*'){
			if(!firstStar) sb.append(ch);
			firstStar=true;
		}else{
			sb.append(ch);
			firstStar=false;
		}
	  }

	 return sb.toString();
	}

    //Leetcode 132.===========================================================
	//Time: O(n3)
	public static int minCut_(int st,int end,int[][] dp,boolean[][] isPalindrome){
		if(st==end || isPalindrome[st][end]) return dp[st][end]=0; 
		
		if(dp[st][end]!=-1) return dp[st][end];

        int min_=(int) 1e8;
		for(int cut=st;cut<end;cut++){
			int leftMinCut=isPalindrome[st][cut]?0:minCut_(st,cut,dp,isPalindrome);
			int rightMinCut=isPalindrome[cut+1][end]?0:minCut_(cut+1,end,dp,isPalindrome);

			int myCost=leftMinCut +  1   + rightMinCut;
            min_=Math.min(min_,myCost);
		}

		return dp[st][end]=min_;
	}

    //Time: O(n2)
	public static int minCut_02(int st,int end,int[] dp,boolean[][] isPalindrome){
		if(st>end) return -1;
		if(dp[st]!=-1) return dp[st];

        int min_=(int) 1e8;
		for(int cut = st;cut <=end;cut++){
			if(isPalindrome[st][cut]){
				int cuts_ = minCut_02(cut+1,end,dp,isPalindrome)+1;
				min_=Math.min(min_,cuts_);
			}
		}

		return dp[st]=min_;
	}

	public static int minCut_02_DP(int st,int end,int[] dp,boolean[][] isPalindrome){
		for( st=end;st>=0;st--){
			int min_=(int) 1e8;
			for(int cut = st;cut <=end;cut++){
				if(isPalindrome[st][cut]){
					int cuts_ = (( cut + 1==end+1 )? -1 : dp[cut+1]) + 1;
					min_=Math.min(min_, cuts_);
				}
			}
	
			dp[st]=min_;
		}
		return dp[0];
	}


	public static int minCut(String str) {
		int n=str.length();
		int[] dp=new int[n];
		boolean[][] isPalindrome=new boolean[n][n];

		for(int i=0;i<n;i++) dp[i]=-1;

		for (int gap = 0; gap < n; gap++) {
			for (int si = 0, ei = gap; ei < n; si++, ei++) {
				if (gap == 0) isPalindrome[si][ei] = true;
				else if (str.charAt(si) == str.charAt(ei) && gap == 1) isPalindrome[si][ei] = true;
				else isPalindrome[si][ei] = str.charAt(si) == str.charAt(ei) && isPalindrome[si + 1][ei - 1];
			}
		}

		return minCut_02(0,n-1,dp,isPalindrome);
	}
    static void questionSet() {
        System.out.println(numDecodings("1423101112"));
    }

    static void display2D(int[][] array) {
        for (int[] ar : array)
            System.out.println(Arrays.toString(ar));
    }

    public static void main(String[] args) {
        // friends_pairing_problem(10);
        // goldMine();
        // count_of_waysMAIN();
        // stringSubstringSet();
        // longestCommonSubsequence("ABCDGH", "AEDFHR");
        // longestCommonSubsequence("aabcd", "abcd"); //input for
        // lowestCommonSubstring()
        // coinChange();
        // knapsack();
        // LIS_type();
        // cutType();
        // System.out.println((char)('A' + 2));
        questionSet();
    }
}