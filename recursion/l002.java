import java.util.ArrayList;
import java.util.Arrays;
public class l002 {

    public static int coinChangePermutation_INF(int[] arr, int tar, String ans) {
        if(tar == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for(int i = 0; i < arr.length; i++) {
            int coin = arr[i];
            if(tar - coin >= 0) {
                count += coinChangePermutation_INF(arr, tar - coin, ans + coin);
            }
        }

        return count;
    }

    public static int coinChangePermutation_ONE(int[] arr, int tar, String ans) {
        if(tar == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for(int i = 0; i < arr.length; i++) {
            int coin = arr[i];
            if(arr[i] > 0 && tar - coin >= 0) {
                arr[i] = -arr[i];
                count += coinChangePermutation_ONE(arr, tar - coin, ans + coin);
                arr[i] = -arr[i];
            }
        }

        return count;
    }

    public static int coinChangeComb_INF(int[] arr, int idx, int tar, String ans) {
        if(tar == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for(int i = idx; i < arr.length; i++) {
            int coin = arr[i];
            if(tar - coin >= 0) {
                count += coinChangeComb_INF(arr, i, tar - coin, ans + coin);
            }
        }
        return count;
    }

    public static int coinChangeComb_ONE(int[] arr, int idx, int tar, String ans) {
        if(tar == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for(int i = idx; i < arr.length; i++) {
            int coin = arr[i];
            if(tar - coin >= 0) {
                count += coinChangeComb_ONE(arr, i + 1, tar - coin, ans + coin);
            }
        }
        return count;
    }

    public static int queensCombination(int[] rooms, int room, int qpsf, int tq, String ans) {
        if(qpsf == tq) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for(int r = room; r < rooms.length; r++) {
            count += queensCombination(rooms, r + 1, qpsf + 1, tq, ans + "Q" + qpsf + "R" + r + " ");
        }

        return count;
    }
    public static int queensPermutation(int[] rooms, int room, int qpsf, int tq, String ans) {
        if(qpsf == tq) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for(int r = room; r < rooms.length; r++) {
            if(rooms[r] == 1) {
                rooms[r] = 0;
                count += queensPermutation(rooms, 0, qpsf + 1, tq, ans + "Q" + qpsf + "R" + r + " ");
                rooms[r] = 1;
            }
        }

        return count;
    }
//Queens2D.=================================================================
    public static int queensCombination2d(boolean[][] rooms, int room, int tnq, String ans) {
        if(tnq == 0){
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for(int r = room; r < rooms.length * rooms[0].length; r++) {
            int x = r / rooms[0].length;
            int y = r % rooms[0].length;
            count += queensCombination2d(rooms, r + 1, tnq - 1, ans + "(" + x + ", " + y + ") " );
        }

        return count;
    }

    public static int queensPermutaion2d(boolean[][] rooms, int tnq, String ans) {
        if(tnq == 0){
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for(int r = 0; r < rooms.length * rooms[0].length; r++) {
            int x = r / rooms[0].length;
            int y = r % rooms[0].length;
            if(!rooms[x][y]){
                rooms[x][y] = true;
                count += queensPermutaion2d(rooms, tnq - 1, ans + "(" + x + ", " + y + ") " );
                rooms[x][y] = false;
            }
        }

        return count;
    }
//nQueens.===============================================
    public static boolean isAValidMove(boolean[][] board, int r, int c) {
        int[][] dirA = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
        // int[][] dirA = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};   //for permutations
        for(int d = 0; d < dirA.length; d++) {
            for(int rad = 1; rad < board.length; rad++) {
                int x = r + rad * dirA[d][0];
                int y = c + rad * dirA[d][1];
                if(x >= 0 && y >= 0 && x < board.length && y < board[0].length){
                    if(board[x][y]) {
                        return false;
                    } 
                } else {
                    break;
                }
            }
        }
        return true;
    }

    public static int nQueens01(boolean[][] board, int room, int tnq, String ans) {
        if(tnq == 0) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for(int r = room; r < board.length * board[0].length; r++) {
            int x = r / board[0].length;
            int y = r % board[0].length;
            if(isAValidMove(board, x, y)) {   
                board[x][y] = true;
                count  += nQueens01(board, r + 1, tnq - 1, ans + "(" + x + ", " + y + ")");
                board[x][y] = false;
            }
        }
        return count;
    }

    //nqueens using subsequence method
    public static int nQueens02(boolean[][] board, int idx, int tnq, String ans) {
        if(tnq == 0 || idx == board.length * board[0].length) {
            if(tnq == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        int count = 0;
        int x = idx / board[0].length;
        int y = idx % board[0].length;
        if(isAValidMove(board, x, y)) {   
            board[x][y] = true;
            count  += nQueens02(board, idx + 1, tnq - 1, ans + "(" + x + ", " + y + ")");
            board[x][y] = false;
        }
        count  += nQueens02(board, idx + 1, tnq, ans);
        return count;
    }
    

    //permutation
    public static int nQueens03(boolean[][] board, int idx, int tnq, String ans) {
        if(tnq == 0 || idx == board.length * board[0].length) {
            if(tnq == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        int count = 0;
        int x = idx / board[0].length;
        int y = idx % board[0].length;
        if(!board[x][y] && isAValidMove(board, x, y)) {   //checking board pe queen pehle se to nahi padi
            board[x][y] = true;
            count  += nQueens03(board, 0, tnq - 1, ans + "(" + x + ", " + y + ")");
            board[x][y] = false;
        }
        count  += nQueens03(board, idx + 1, tnq, ans);
        return count;
    }

    //practise this one again..
    public static boolean nQueens04(boolean[][] rooms, int idx, int tnq, String ans) {
        if (tnq==0)
    {
        System.out.println(ans);
        return true;
    }

    int count = 0;
    boolean res=false;
    for (int r = idx; r < rooms.length*rooms[0].length; r++){
        int x = r / rooms[0].length;
        int y = r % rooms[0].length;

        if (!rooms[x][y] && isAValidMove(rooms,x,y))
        {
            rooms[x][y] = true;
            res=res || nQueens04(rooms,0,tnq-1,  ans + "(" + x + ", " + y + ") ");
            rooms[x][y] = false;
        }
    }
    return res;
    }


// knight problem
public static boolean nKnight(int[][] board, int r, int c, int move) {
    board[r][c] = move;
    if(move == 63) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {

                System.out.print(board[i][j] + "\t");
            }
            System.out.println();
        }
        return true;
    }
    int xMove[] = { 2, 1, -1, -2, -2, -1,  1,  2 }; 
    int yMove[] = { 1, 2,  2,  1, -1, -2, -2, -1 }; 
    boolean res = false;
    for(int d = 0; d < xMove.length; d++) {
        int x = r + xMove[d];
        int y = c + yMove[d];
        if(x >= 0 && y >= 0 && x < board.length && y < board[0].length && board[x][y] == -1){
            res = res || nKnight(board, x, y, move + 1);
        }
    }
    board[r][c] = -1;
    return res;
}


    //nQueens Optimized.==========================================================
    static boolean[] ROW;
    static boolean[] COL;
    static boolean[] DIAG;
    static boolean[] ADIAG;

    public static int nQueens05(int n, int m, int idx, int tnq, String ans) {
        if(tnq == 0) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;

        for(int i = idx; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            if(!ROW[r] && !COL[c] && !DIAG[r+c] && !ADIAG[r-c + m-1]){
                ROW[r] = true; COL[c] = true; DIAG[r+c] = true; ADIAG[r-c + m-1] = true; 
                count += nQueens05(n, m, i + 1, tnq - 1, ans + "(" + r + ", " + c + ") ");
                ROW[r] = false; COL[c] = false; DIAG[r+c] = false; ADIAG[r-c + m-1] = false; 
            }
        }
        
        return count;
    }


    //nQueens Optimized.==========================================================
    static int row = 0;
    static int col = 0;
    static int diag = 0;
    static int adiag = 0;

    public static int nQueens06(int n, int m, int idx, int tnq, String ans) {
        if(tnq == 0) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for (int r = idx; r < n*m; r++){
            int x = r / m;
            int y = r % m;
    
            if ((row & (1<<x))==0 && (col & (1<<y))==0 && (diag & (1<<(x+y)))==0 && (adiag & (1<<(x-y + m - 1)))==0)
            {
                row^=(1<<x);
                col^=(1<<y);
                diag^=(1<<(x+y));
                adiag^=(1<<(x-y+m-1));
    
                count+= nQueens06(n,m,r+1,tnq-1, ans + "(" + x + ", " + y + ") " );
               
                row^=(1<<x);
                col^=(1<<y);
                diag^=(1<<(x+y));
                adiag^=(1<<(x-y+m-1));
            }
        }
        return count;
    }

    //Sudoku.=======================
    //leetcode 37
    boolean isSafeToPlaceNumber(char[][] board, int x, int y, int num) {
        //row
        for(int i = 0; i < 9; i++){
            if(board[x][i] - '0' == num)    return false;
        }

        for(int j = 0; j < 9; j++) {
            if(board[j][y] - '0' == num)    return false;
        }

        x = (x / 3) * 3 ;  y = (y / 3) * 3;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(board[x + i][y + j] - '0' == num)    return false;
            }
        }

        return true;
    }

    public boolean solveSudoku(char[][] board, ArrayList<Integer> calls, int idx) {
        if(calls.size() == idx) return true;

        boolean res = false;
        int r = calls.get(idx) / 9;
        int c = calls.get(idx) % 9;
        for(int num = 1; num <= 9; num++) {
            if(isSafeToPlaceNumber(board, r, c, num)) {
                board[r][c] = (char)(num + '0');
                if(solveSudoku(board, calls, idx + 1)) 
                    return true;
                board[r][c] = '.';
            }
        }
        return res;
    }
    public boolean solveSudoku(char[][] board) {
        ArrayList<Integer> calls = new ArrayList<>();
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(board[i][j] == '.') {
                    calls.add(i * 9 + j);
                }
            }
        }
        return solveSudoku(board, calls, 0);
    }
    //leetcode 36 Valid Sudoku
    public boolean isValidSudoku(char[][] board) {
        int[] Srow = new int[9];
        int[] Scol = new int[9];
        int[][] Smat = new int[3][3];
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    if((Srow[i]&(1<<num)) != 0 || (Scol[j]&(1<<num)) != 0 || (Smat[i/3][j/3]&(1<<num)) != 0){
                        return false;}
                       else {
                           Srow[i] ^= (1<<num);
                           Scol[j] ^= (1<<num);
                           Smat[i/3][j/3] ^= (1<<num);
                       }
            }
        }
       }
     return true;
    }

    // sudoko using bits optimised
    // leetcode 37. Sudoku Solver
    static int[] Srow = new int[9];
    static int[] Scol = new int[9];
    static int[][] Smat = new int[3][3];

    boolean solveSudokuBits(char[][] board, ArrayList<Integer> calls, int idx) {
        if(calls.size() == idx) return true;
        int r = calls.get(idx) / 9;
        int c = calls.get(idx) % 9;
        for(int i = 1; i <= 9; i++) {
            int mask = (1 << i);
            if((Srow[r] & mask) == 0 && (Scol[c] & mask) == 0 && (Smat[r/3][c/3] & mask) == 0) {
                Srow[r] ^= mask;    Scol[c] ^= mask;    Smat[r/3][c/3] ^= mask; board[r][c] = (char)(i + '0');
                if(solveSudokuBits(board, calls, idx + 1) == true)  return true;
                Srow[r] ^= mask;    Scol[c] ^= mask;    Smat[r/3][c/3] ^= mask; board[r][c] = '.';
            }
        }
        return false;
    }

    public void solveSudokuBits(char[][] board) {
        ArrayList<Integer> calls = new ArrayList<Integer>();
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++) {
                if(board[i][j] == '.') {
                    calls.add(i * 9 + j);
                } else {
                    int num = board[i][j] - '0';
                    int mask = (1<<num);
                    Srow[i] ^= mask;    Scol[j] ^= mask; Smat[i/3][j/3] ^= mask;
                }
            }
        }

        solveSudokuBits(board, calls, 0);
    }



    //========================

    void sudoku() {
    }

    public static void coinChange() {
        int[] arr = { 2, 3, 5, 7 };
        int tar = 10;

        System.out.println(coinChangePermutation_ONE(arr, tar, ""));
        // System.out.println(coinChangeComb_ONE(arr, 0, tar, ""));

    }
    
    public static void QueensChange() {
        // // int[] rooms = {1, 1, 1, 1, 1};
        // int[] rooms = new int[16];
        // int q = 4;
        // System.out.println(queensCombination(rooms, 0, 0, q, ""));
        // System.out.println(queensPermutation(rooms, 0, 0, q, ""));
    }

    public static void nQueens() {
        // boolean[][] rooms = new boolean[4][4];
        // int tnq = 4;
        // System.out.println(nQueens01(rooms, 0, tnq, ""));
        // System.out.println(nQueens02(rooms, 0, tnq, ""));
        // System.out.println(nQueens03(rooms, 0, tnq, ""));
        // System.out.println(nQueens04(rooms, 0, tnq, ""));

        // int[][] board = new int[8][8];
        // for(int i = 0; i < board.length; i++)   {
        //     Arrays.fill(board[i], -1);
        // }
        // System.out.println(nKnight(board, 0, 0, 0));
        
        int n = 4;
        ROW = new boolean[n];
        COL = new boolean[n];
        DIAG = new boolean[n + n - 1];
        ADIAG = new boolean[n + n - 1];
        // System.out.println(nQueens05(n, n, 0, n, ""));
        System.out.println(nQueens06(n, n, 0, n, ""));
    }


    public static void main(String[] args) {
        // coinChange();
        // nQueens();
    }
}
 