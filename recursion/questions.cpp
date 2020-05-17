#include <iostream>
#include <vector>
using namespace std;
//leetcode 39
vector<vector<int>> res;
void combSum(vector<int> &candidates, int target, int idx, vector<int> &ans) {
    if(target == 0) {
        res.push_back(ans);
        return;
    }
    for(int i = idx; i < candidates.size(); i++) {
        int coin = candidates[i];
        if(target - coin >= 0) {
            ans.push_back(coin);
            combSum(candidates, target - coin, i, ans);
            ans.pop_back();
        }
    }

}
vector<vector<int>> combinationSum(vector<int> &candidates, int target) {
        vector<int> ans;
        combSum(candidates, target, 0, ans);
        return res;
}

//leetcode 216
vector<vector<int>> res;
    vector<vector<int>> combinationSum3(int k, int n) {
        vector<int> ans;
        combinationSum3_(k, n, 1, ans);
        return res;
    }
    
    void combinationSum3_(int k, int target, int idx, vector<int> &ans) {
        if(target == 0 && k == 0) {
             res.push_back(ans);
             return;
        }
        for(int i = idx; i <= 9; i++) {
            int coin = i;
            if(target - coin >= 0) {
                ans.push_back(coin);
                combinationSum3_(k-1, target - coin, i + 1, ans);
                ans.pop_back();
            }
        }
    }

//lintcode 90

void combSum_2(vector<int> &coins, int idx, int k, int tar, vector<int> &ans)
{
    if (tar == 0)
    {
        if (ans.size() == k)
        {
            res.push_back(ans);
        }
        return;
    }

    for (int i = idx; i < coins.size(); i++)
    {
        if (tar - coins[i] >= 0)
        {
            ans.push_back(coins[i]);
            combSum_2(coins, i + 1, k, tar - coins[i], ans);
            ans.pop_back();
        }
    }
}

vector<vector<int>> kSumII(vector<int> &A, int k, int target)
{
    vector<int> ans;
    combSum_2(A, 0, k, target, ans);
    return res;
}

//leetcode 77
vector<vector<int>> res;
vector<vector<int>> combine(int n, int k) {
    vector<int> ans;
    combine_(n, k, 1, ans);
    return res;
}
    
void combine_(int n, int k, int idx, vector<int> &ans) {
    if(k == 0){
        res.push_back(ans);
        return;
    }
    
    for(int i = idx; i <= n; i++) {
        ans.push_back(i);
        combine_(n, k - 1, i + 1, ans);
        ans.pop_back();
    }
}

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
// int getMaximumGold(vector<vector<int>>& grid) {
//     int res = 0;
//     vector<vector<int>> dir = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
//     for(int i = 0; i < grid.size(); i++) {
//         for(int j = 0; j < grid[0].size(); j++) {
//             if(grid[i][j] > 0)
//                 res = max(res, getMaximumGold(i, j, grid, dir));
//         }
//     }
// }

// int getMaximumGold(int r, int c, vector<vector<int>>& grid, vector<vector<int>> &dir) {
//     int res = 0;
//     grid[r][c] = -grid[r][c];

//     for(int d = 0; d < 4; d++){
//         int x = r + dir[d][0];
//             int y = c + dir[d][1];
            
//             if(x >= 0 && y >= 0 && x < grid.size() && y < grid[0].size() && grid[x][y] > 0) {
//                 res = max(res, getMaximumGold(x, y, grid, dir));
//             }
//     }

//     grid[r][c] = -grid[r][c];
//     return res +  grid[r][c];
// }


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


// int uniquePathsIII(vector<vector<int>>& grid) {
//     vector<vector<int>> dir = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
//     int freeCells = 0;
//     int sr, sc, dr, dc;
//     for(int i = 0; i < grid.size(); i++) {
//         for(int j = 0; j < grid[0].size(); j++) {
//             if(grid[i][j] == 0) {
//                 freeCells++;
//             }
//             if(grid[i][j] == 1) {
//                 sr = i;     sc = j;
//             }
//             if(grid[i][j] == 2) {
//                 dr = i;     dc = j;
//             }
//         }
//     }
//     return uniquePathsIII(sr, sc, dr, dc, freeCells, grid, dir);
// }

// int uniquePathsIII(int sr, int sc, int dr, int dc, int freeCells, vector<vector<int>>& grid, vector<vector<int>> &dir) {
//    if(sr == dr && sc == dc && freeCells == 0){
//        return 1;
//    }
    
//     int count = 0;
//     grid[sr][sc] = -1;
//     for(int d = 0; d < 4; d++){
//         int x = sr + dir[d][0];
//         int y = sc + dir[d][1];
//         if(x >= 0 && y >= 0 && x < grid.size() && y < grid[0].size() && (grid[x][y] == 0 || grid[x][y] == 2)) {
//             count += uniquePathsIII(x, y, dr, dc, freeCells - 1, grid, dir);
//         }
//     }
//     grid[sr][sc] = 0;
//     return count;
//     }

int main() {
    // vector<vector<int>> grid = {{1,0,0,0},{0,0,0,0},{0,0,2,-1}};
    // cout << uniquePathsIII(grid);
    vector<int> can = {2,3,6,7};
    combinationSum(can, 7);
    for(vector<int> list : res){
        for(int ele : list) {
            cout << ele;
        }
        cout << endl;
    }
}