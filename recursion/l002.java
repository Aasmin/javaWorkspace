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

    public static int nQueens02(boolean[][] board, int tnq, String ans) {
        if(tnq == 0) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for(int r = 0; r < board.length * board[0].length; r++) {
            int x = r / board[0].length;
            int y = r % board[0].length;
            if(isAValidMove(board, x, y)) {   
                board[x][y] = true;
                count  += nQueens02(board, tnq - 1, ans + "(" + x + ", " + y + ")");
                board[x][y] = false;
            }
        }
        return count;
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
        boolean[][] rooms = new boolean[4][4];
        int tnq = 4;
        // System.out.println(nQueens01(rooms, 0, tnq, ""));
        System.out.println(nQueens02(rooms, tnq, ""));
    }


    public static void main(String[] args) {
        // coinChange();
        nQueens();
    }
}
