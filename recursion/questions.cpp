#include <iostream>
#include <vector>
using namespace std;

// leetcode 91.=================================
int numDecodings(string &s, int idx)
{
    if (idx == s.length())
    {
        return 1;
    }

    char ch = s[idx];
    int count = 0;
    if (ch != '0')
    {
        // char ch_ = (char)((ch - '1') + 'a');
        count += numDecodings(s, idx + 1);
    }

    if (idx + 1 < s.length())
    {
        int num = (ch - '0') * 10 + (s[idx + 1] - '0');
        if (num >= 10 && num <= 26)
        {
            // char ch_ = (char)('a' + num - 1);
            count += numDecodings(s, idx + 2);
        }
    }

    return count;
}

int numDecodings(string s)
{
    if (s.length() == 0)
        return 0;

    return numDecodings(s, 0);
}

// leetcode 1219
int getMaximumGold(vector<vector<int>>& grid) {
    int res = 0;
    vector<vector<int>> dir = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    for(int i = 0; i < grid.size(); i++) {
        for(int j = 0; j < grid[0].size(); j++) {
            if(grid[i][j] > 0)
                res = max(res, getMaximumGold(i, j, grid, dir));
        }
    }
}

int getMaximumGold(int r, int c, vector<vector<int>>& grid, vector<vector<int>> &dir) {
    int res = 0;
    grid[r][c] = -grid[r][c];

    for(int d = 0; d < 4; d++){
        int x = r + dir[d][0];
            int y = c + dir[d][1];
            
            if(x >= 0 && y >= 0 && x < grid.size() && y < grid[0].size() && grid[x][y] > 0) {
                res = max(res, getMaximumGold(x, y, grid, dir));
            }
    }

    grid[r][c] = -grid[r][c];
    return res +  grid[r][c];
}


//leetcode 232
 bool isPowerOfTwo(int n) {
        return n > 0 && !(n&=(n-1)) ;
    }


//leetcode 338
vector<int> countBits(int num) {
    // vector<int> countBits(int num) {
    //     vector<int> ans(num + 1, 0);
    //     for(int i = 1; i <= num; i++)   ans[i] = ans[(i & (i-1))] + 1;
    //     return ans;
vector<int> out;    int val = 0;
    while(val <= num) {
        int count = 0;
        int temp = val;
        while(temp != 0) {
            count++;
            temp &= (temp - 1);
        }
        out.push_back(count);
        val++;
    }
    return out;
}

//leetcode 137
 int singleNumberii(vector<int>& nums) {
        int ans = 0;
        for(int i = 0; i < 32; i++) {
        int count = 0;
        int mask = (1 << i);
        for(int ele : nums)     
            if((ele & mask) != 0)
                count++;
        if(count % 3 != 0) {
            ans |= mask;
            }
        }
        return ans;
    }

//leetcode 260
    vector<int> singleNumberiii(vector<int>& nums) {
        int xorNum = 0;
        for(int ele : nums)
            xorNum ^= ele;
        
        int firstLSB = (xorNum & (-xorNum));
        int a = 0;
        int b = 0;
        for(int ele : nums) {
            if((firstLSB & ele) != 0) {
                a ^= ele;
            } else {
                b ^= ele;
            }
        }
        return {a, b};
    }


int uniquePathsIII(vector<vector<int>>& grid) {
    vector<vector<int>> dir = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    int freeCells = 0;
    int sr, sc, dr, dc;
    for(int i = 0; i < grid.size(); i++) {
        for(int j = 0; j < grid[0].size(); j++) {
            if(grid[i][j] == 0) {
                freeCells++;
            }
            if(grid[i][j] == 1) {
                sr = i;     sc = j;
            }
            if(grid[i][j] == 2) {
                dr = i;     dc = j;
            }
        }
    }
    return uniquePathsIII(sr, sc, dr, dc, freeCells, grid, dir);
}

int uniquePathsIII(int sr, int sc, int dr, int dc, int freeCells, vector<vector<int>>& grid, vector<vector<int>> &dir) {
   if(sr == dr && sc == dc && freeCells == 0){
       return 1;
   }
    
    int count = 0;
    grid[sr][sc] = -1;
    for(int d = 0; d < 4; d++){
        int x = sr + dir[d][0];
        int y = sc + dir[d][1];
        if(x >= 0 && y >= 0 && x < grid.size() && y < grid[0].size() && (grid[x][y] == 0 || grid[x][y] == 2)) {
            count += uniquePathsIII(x, y, dr, dc, freeCells - 1, grid, dir);
        }
    }
    grid[sr][sc] = 0;
    return count;
    }

int main() {
    vector<vector<int>> grid = {{1,0,0,0},{0,0,0,0},{0,0,2,-1}};
    cout << uniquePathsIII(grid);
}