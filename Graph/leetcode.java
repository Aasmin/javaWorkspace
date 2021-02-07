import java.util.LinkedList;

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

    // Leetcode 200. Number of Islands
    public void numIslandsByDFS(int r, int c, int n, int m, char[][] grid) {
        if(grid[r][c] != '1') return;

        grid[r][c] = '0';
        if (r - 1 >= 0)
            numIslandsByDFS(r - 1, c, n, m, grid);
        if (r + 1 < n)
            numIslandsByDFS(r + 1, c, n, m, grid);
        if (c - 1 >= 0)
            numIslandsByDFS(r, c - 1, n, m, grid);
        if (c + 1 < m)
            numIslandsByDFS(r, c + 1, n, m, grid);
            
    }

    public int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length, count = 0;
        for(int r = 0; r < m; r++) {
            for(int c = 0; c < n; c++) {
                if(grid[r][c] == '1') {
                    count++;
                    numIslandsByDFS(r, c, n, m, grid);
                }
            }
        }
        return count;
    }

    //Leetcode 695. Max Area of Island
    public int maxAreaOfIslandbyDFS(int r, int c, int n, int m, int[][] grid) {
        if(grid[r][c] != 1) return 0;

        int count = 0;
        grid[r][c] = 0;
        if(r - 1 >= 0) count += maxAreaOfIslandbyDFS(r - 1, c, n, m, grid);
        if(r + 1 < n) count += maxAreaOfIslandbyDFS(r + 1, c, n, m, grid);
        if(c - 1 >= 0) count += maxAreaOfIslandbyDFS(r, c - 1, n, m, grid);
        if(c + 1 < m) count += maxAreaOfIslandbyDFS(r, c + 1, n, m, grid);
        return count + 1;
    }
    public int maxAreaOfIsland(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int maxArea = 0;
        for(int r = 0; r < n; r++) {
            for(int c = 0; c < m; c++) {
                if(grid[r][c] == 1) 
                    maxArea = Math.max(maxArea, maxAreaOfIslandbyDFS(r, c, n, m, grid));
            }
        }
         return maxArea;
    }

    public int islandPerimeter(int[][] grid) {
        int ones = 0, nbrs = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1){
                    ones++;
                    nbrs += countNbrs(i, j, grid);   
                }
            }
        }
        return (ones * 4 - nbrs * 2);
    }
    
    // Leetcode 463. Island Perimeter
    public int countNbrs(int i, int j, int[][]grid) {
        int num = 0;
        if(i + 1 < grid.length) {
            if(grid[i + 1][j] == 1) num += 1;
        }
        if(j + 1 < grid[0].length) {
            if(grid[i][j + 1] == 1) num += 1;
        }
        return num;
    }


    // ND - Count the number of distinct island: 
    // An island consider to be the same as another iff one island can be translated (and not rotated/reflected) to other island.
    

    // Leetcode 1091. Shortest Path in Binary Matrix
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (n == 0)
            return -1;
        int m = grid[0].length;
        if (m == 0)
            return -1;

        if (grid[0][0] == 1 || grid[n - 1][m - 1] == 1)
            return -1;

        int[][] dirA = {{-1, -1}, {-1, 0}, {0, -1}, {-1, 1}, {1, -1}, {0, 1}, {1, 0}, {1, 1}};
        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(0);
        grid[0][0] = 1;

        int level = 1;
        while (que.size() != 0)
        {
            int size = que.size();
            while (size-- > 0)
            {
                int rvtx = que.removeFirst();
                
                int r = rvtx / m;
                int c = rvtx % m;

                if (r == n - 1 && c == m - 1)
                    return level;

                for (int d = 0; d < 8; d++)
                {
                    int x = r + dirA[d][0];
                    int y = c + dirA[d][1];

                    if (x >= 0 && x < n && y >= 0 && y < m && grid[x][y] == 0)
                    {
                        que.addLast((x * m + y));
                        grid[x][y] = 1;
                    }
                }
            }
            level++;
        }

        return -1;
    }


    public static void main(String[] args) {

    }

}