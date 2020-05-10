#include <iostream>
#include <vector>
using namespace std;

int coinChangePermutation_INF(vector<int> &arr, int lidx, int tar, string ans)
{
    if(tar == 0 || lidx == arr.size()){
        if(tar == 0)   {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    int coin = arr[lidx];
    if(tar - coin >= 0)
        count += coinChangePermutation_INF(arr, 0, tar - coin, ans + to_string(coin) + " ");
    count += coinChangePermutation_INF(arr, lidx + 1, tar, ans);
    
    return count;
}

int coinChangePermutation(vector<int> &arr, int lidx, int tar, string ans)
{
    if(tar == 0 || lidx == arr.size()){
        if(tar == 0)   {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    int coin = arr[lidx];
    if(arr[lidx] > 0 && tar - coin >= 0) {
        arr[lidx] = -arr[lidx];
        count += coinChangePermutation(arr, 0, tar - coin, ans + to_string(coin) + " ");
        arr[lidx] = -arr[lidx];
    }
    count += coinChangePermutation(arr, lidx + 1, tar, ans);

    return count;
}

int coinChangeCombination_INF(vector<int> &arr, int lidx, int tar, string ans)
{
    if(tar == 0 || lidx == arr.size()){
        if(tar == 0)   {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    int coin = arr[lidx];
    if(tar - coin >= 0)
        count += coinChangeCombination_INF(arr, lidx, tar - coin, ans + to_string(coin) + " ");
    count += coinChangeCombination_INF(arr, lidx + 1, tar, ans);

    return count;
}

int coinChangeCombination(vector<int> &arr, int lidx, int tar, string ans)
{
    if(tar == 0 || lidx == arr.size()){
        if(tar == 0)   {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    int coin = arr[lidx];
    if(tar - coin >= 0)
        count += coinChangeCombination(arr, lidx + 1, tar - coin, ans + to_string(coin) + " ");
    count += coinChangeCombination(arr, lidx + 1, tar, ans);

    return count;
}
//Sudoku.=========================================================
// bool isSafeToPlaceNumber(vector<vector<int>> &board, int num, int x, int y)
// {
//     //row
//     for (int c = 0; c < 9; c++)
//     {
//         if (board[x][c] == num)
//             return false;
//     }

//     //col
//     for (int r = 0; r < 9; r++)
//     {
//         if (board[r][y] == num)
//             return false;
//     }

//     //matrix
//     int r = (x / 3) * 3;
//     int c = (y / 3) * 3;
//     for (int i = 0; i < 3; i++)
//     {
//         for (int j = 0; j < 3; j++)
//         {
//             if (board[r + i][c + j] == num)
//                 return false;
//         }
//     }

//     return true;
// }

// bool sudokuSolver_(vector<vector<char>> &board, vector<char> &calls, int idx) {
//     if (idx == calls.size())
//     {
//         return true;
//     }

//     int i = calls[idx] / 9;
//     int j = calls[idx] % 9;
//     bool res = false;

//     for (int num = 0; num < 9; num++)
//     {
//         if (isSafeToPlaceNumber(board, num, i, j))
//         {
//             board[i][j] = num;
//             res = res || sudokuSolver_(board, calls, idx + 1);
//             if (res)
//                 return res;
//             board[i][j] = 0;
//         }
//     }
//     return true;
// }

// void sudoku() {
    
// }

void coinChange()
{
    vector<int> arr{2, 3, 5, 7};
    int tar = 10;
    cout << coinChangeCombination_INF(arr, 0, tar, "") << endl;
}

int main()
{
    coinChange();
    // queenProblem();
    return 0;
}