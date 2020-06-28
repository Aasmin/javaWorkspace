public class l001 {
    public static int fib(int n) {
        return 0;
    }

    public static void display2d(int[][] dp) {
        for(int[] ar : dp) {
            for(int ele : ar)
                System.out.print(ele + " ");
            System.out.println();
        }
    }

    //Maze Path Questions.================================
    public static int mazePathHVDNJumps(int sr, int sc, int er, int ec) {
        if(sr == er && sc == ec)    return 1;
        int count = 0;
        
        for(int k = 1; k <= Math.max(ec, er); k++) {
            if(sr + k <= er)
                count += mazePathHVDNJumps(sr + k, sc, er, ec);
            if(sc + k <= ec)
                count += mazePathHVDNJumps(sr, sc + k, er, ec);
            if(sr + k <= er && sc + k <= ec)
                count += mazePathHVDNJumps(sr + k, sc + k, er, ec);
        }
        return count;
    }

    public static int mazePathHVDNJumps_Memo(int sr, int sc, int er, int ec, int[][] dp) {
        if (sr == er && sc == ec)
        {
            return dp[sr][sc] = 1;
        }

        if (dp[sr][sc] != 0)
            return dp[sr][sc];

        int count = 0;
        for (int jump = 1; sr + jump <= er; jump++)
            count += mazePathHVDNJumps_Memo(sr + jump, sc, er, ec, dp);

        for (int jump = 1; sc + jump <= ec; jump++)
            count += mazePathHVDNJumps_Memo(sr, sc + jump, er, ec, dp);

        for (int jump = 1; sr + jump <= er && sc + jump <= ec; jump++)
            count += mazePathHVDNJumps_Memo(sr + jump, sc + jump, er, ec, dp);

        return dp[sr][sc] = count;
    }

    public static int mazePathHVDNJumps_Tab(int sr, int sc, int er, int ec, int[][] dp) {
        for (sr = er; sr >= 0; sr--)
        {
            for (sc = ec; sc >= 0; sc--)
            {
                if (sr == er && sc == ec)
                {
                    dp[sr][sc] = 1;
                    continue;
                }

                int count = 0;
                for (int jump = 1; sr + jump <= er; jump++)
                    count += dp[sr + jump][sc]; //mazePathHVDNJumps_Memo(sr + jump, sc, er, ec, dp);

                for (int jump = 1; sc + jump <= ec; jump++)
                    count += dp[sr][sc + jump]; //mazePathHVDNJumps_Memo(sr, sc + jump, er, ec, dp);

                for (int jump = 1; sr + jump <= er && sc + jump <= ec; jump++)
                    count += dp[sr + jump][sc + jump]; //mazePathHVDNJumps_Memo(sr + jump, sc + jump, er, ec, dp);

                dp[sr][sc] = count;
        }
    }

    return dp[0][0];
    }

    public static void mazeSolve() {
        System.out.println(mazePathHVDNJumps(0, 0, 2, 2));
        int[][] dpM = new int[3][3];
        System.out.println(mazePathHVDNJumps_Memo(0, 0, 2, 2, dpM));
        display2d(dpM);
        int[][] dpT = new int[3][3];
        System.out.println(mazePathHVDNJumps_Tab(0, 0, 2, 2, dpT));
        display2d(dpT);
    }

    public static void solve() {

    }
    public static void main(String[] args) {
        // solve(); 
        mazeSolve();
    }
}