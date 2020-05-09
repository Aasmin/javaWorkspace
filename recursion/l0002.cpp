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