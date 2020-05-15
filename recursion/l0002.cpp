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
bool isSafeToPlaceNumber(vector<vector<char>> &board, int num, int x, int y)
{
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


bool sudokuSolver_(vector<vector<char>> &board, vector<int> &calls, int idx) {
    if(calls.size() == idx) return true;

        int r = calls[idx] / 9;
        int c = calls[idx] % 9;
        for(int num = 1; num <= 9; num++) {
            if(isSafeToPlaceNumber(board, r, c, num)) {
                board[r][c] = (char)(num + '0');
                if(sudokuSolver_(board, calls, idx + 1)) 
                    return true;
                board[r][c] = '.';
            }
        }
        return false;
}

bool solveSudoku(vector<vector<char>> &board) {
        vector<int> calls;
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(board[i][j] == '.') {
                    calls.push_back(i * 9 + j);
                }
            }
        }
        return sudokuSolver_(board, calls, 0);
    }

void sudoku() {
    
}

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