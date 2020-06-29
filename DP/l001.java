import java.util.LinkedList;

public class l001 {
    public static int fib(int n) {
        if(n <= 1)  return n;
        int ans = fib(n - 1) + fib(n - 2);
        return ans;
    }

    public static int fibM(int n, int[] fibM) {
        if(n <= 1)  return fibM[n] = n;
        if(fibM[n] != 0)    return fibM[n];

        int ans = fibM(n - 1, fibM) + fibM(n - 2, fibM);
        return fibM[n] = ans;
    }

    public static int fibT(int N, int[] fibT) {
        for(int n = 0; n <= N; n++) {
            if(n <= 1)  {fibT[n] = n;   continue;}
    
            int ans = fibT[n - 1] + fibT[n - 2];    //fib(n - 1) + fib(n - 2);
            fibT[n] = ans;
        }
        return fibT[N];
    }

    public static int fibBtr(int N) {   //as fib(n) only depends on last two elements; so we used only two int vars
        int a = 0, b = 1, sum = 0;  
        for(int n = 2; n <= N; n++) {
            sum = a + b;
            a = b;
            b = sum;
        }
        return sum;
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
    
    //Dice Problem.=================================================
    public static void display1d(int[]dp) {
        for(int ele : dp)
            System.out.print(ele + " ");
        System.out.println();
    }

    public static int boardPath(int sp, int ep) {
        if(sp == ep)    return 1;
        int count = 0;
        for(int d = 1; d <= 6 && sp + d <= ep; d++) {
            count += boardPath(sp + d, ep);
        }
        return count;
    }

    public static int boardPathMemo(int sp, int ep, int[] dp) {
        if(sp == ep)    return dp[sp] = 1;
        if(dp[sp] != 0)     return dp[sp];

        int count = 0;
        for(int d = 1; d <= 6 && sp + d <= ep; d++) {
            count += boardPathMemo(sp + d, ep, dp);
        }
        return dp[sp] = count;
    }
    
    public static int boardPathTab(int sp, int ep, int[] dp) {
        for(sp = ep; sp >= 0; sp--) {
            if(sp == ep){
                dp[sp] = 1; continue;
            }

            //if(dp[sp] != 0)     return dp[sp];    we don't need this anymore
        
            int count = 0;
            for(int d = 1; d <= 6 && sp + d <= ep; d++) {
                count += dp[sp + d]; //boardPathMemo(sp + d, ep, dp);
            }
            dp[sp] = count;
        }
        return dp[0];
    }
    // Dynamic Dice
    public static int boardPathTabDynamic(int sp, int ep, int[] dp, int[] diceArray) {
        for(sp = ep; sp >= 0; sp--) {
            if(sp == ep){
                dp[sp] = 1; continue;
            }

            //if(dp[sp] != 0)     return dp[sp];    we don't need this anymore
        
            int count = 0;
            for(int d = 0; d < diceArray.length && sp + diceArray[d] <= ep; d++) {
                count += dp[sp + diceArray[d]]; //boardPathMemo(sp + d, ep, dp);
            }
            dp[sp] = count;
        }
        return dp[0];
    }

    public static int boardPathBest(int sp, int ep) {   // Best as we are only using O(6) space [answer is dependent on the last 6 nums]
        LinkedList<Integer> l1 = new LinkedList<>();    // I came up with this solution as observing the dpT[] array in the output
        for(sp = ep; sp >= 0; sp--) {
            if(sp > ep - 2){
                l1.addFirst(1);
                continue;
            }
        
            if(l1.size() <= 6) 
                l1.addFirst(2 * l1.getFirst());
            else 
                l1.addFirst(2 * l1.getFirst() - l1.removeLast());
        }
        return l1.getFirst();
    }

    public static void diceSolve() {
        int sp = 0, ep = 10;
        // System.out.println(boardPath(sp, ep));
        // int[] dpM = new int[ep + 1];
        // System.out.println(boardPathMemo(sp, ep,dpM));
        // display1d(dpM);
        int[] dpT = new int[ep + 1];
        System.out.println(boardPathTab(sp, ep, dpT));
        display1d(dpT);
        System.out.println(boardPathBest(sp, ep));
        
        // int[] dpT = new int[ep + 1];
        // int[] newDice = {1, 2, 3, 4, 5, 6};
        // System.out.println(boardPathTabDynamic(sp, ep, dpT, newDice));
        // display1d(dpT);

    }

    public static void solve() {
        int n = 45;
        System.out.println(fib(n));

        int[] fibM1 = new int[n + 1];
        System.out.println(fibM(n, fibM1));
        display1d(fibM1);

        int[] fibT1 = new int[n + 1];
        System.out.println(fibT(n, fibT1));
        display1d(fibT1);

        System.out.println(fibBtr(n));
    }
    public static void main(String[] args) {
        // solve(); 
        // mazeSolve();
        diceSolve();
    }
}