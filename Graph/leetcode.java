public class leetcode {
    //JO REGION SURROUNDED HAI, usko 'X' mein convert krdo
    //Leetcode 130. Surrounded Regions    
    public void surroundedRegion(char[][] board) {
        if(board.length == 0) return;
        int n = board.length;
        int m = board[0].length;

        for(int i = 0; i < n; i++) {
            if(board[i][0] == 'O')
                surroundedRegionByDFS(i, 0, n, m, board);
            if(board[i][m - 1] == 'O')
                surroundedRegionByDFS(i, m - 1, n, m, board);
        }
        
        for(int j = 0; j < m; j++) {
            if(board[0][j] == 'O')
                surroundedRegionByDFS(0, j, n, m, board);
            if(board[n-1][j] == 'O')
                surroundedRegionByDFS(n - 1, j, n, m, board);
        }
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if (board[i][j] == '#') // not a surrounded region.
                    board[i][j] = 'O';
                else if (board[i][j] == 'O') // surrounded region.
                    board[i][j] = 'X';
            }
        }
    }
    
    public void surroundedRegionByDFS(int r, int c, int n, int m, char[][] board) {
        if(board[r][c] != 'O') return;
        
        board[r][c] = '#';
        if (r - 1 >= 0)
            surroundedRegionByDFS(r - 1, c, n, m, board);
        if (r + 1 < n)
            surroundedRegionByDFS(r + 1, c, n, m, board);
        if (c - 1 >= 0)
            surroundedRegionByDFS(r, c - 1, n, m, board);
        if (c + 1 < m)
            surroundedRegionByDFS(r, c + 1, n, m, board);
    }

    public static void main(String[] args) {

    }

}