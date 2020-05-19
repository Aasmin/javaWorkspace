#include <iostream>
#include <vector>
using namespace std;

int equiSet(vector<int> &arr, int idx, int set1, int set2, string set1S, string set2S)
{
    if (idx == arr.size())
    {
        if (set1 == set2)
        {
            cout << set1S + " = " + set2S << endl;
            return 1;
        }

        return 0;
    }

    int count = 0;
    count += equiSet(arr, idx + 1, set1 + arr[idx], set2, set1S + " " + to_string(arr[idx]), set2S);
    count += equiSet(arr, idx + 1, set1, set2 + arr[idx], set1S, set2S + " " + to_string(arr[idx]));

    return count;
}

// crossword
vector<vector<char>> board{
    {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
    {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
    {'+', '-', '-', '-', '-', '-', '-', '-', '+', '+'},
    {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
    {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
    {'+', '-', '-', '-', '-', '-', '-', '+', '+', '-'},
    {'+', '-', '+', '+', '+', '-', '+', '+', '+', '-'},
    {'+', '+', '+', '+', '+', '-', '+', '+', '+', '-'},
    {'+', '+', '+', '+', '+', '-', '+', '+', '+', '-'},
    {'+', '+', '+', '+', '+', '+', '+', '+', '+', '+'}};

vector<string> words{"ENGLAND", "GWALIOR", "NORWAY", "AGRA", "ASAM"};


void SetProblem()
{
    vector<int> arr = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
    cout << equiSet(arr, 1, 10, 0, "10 ", "") << endl;
    // cout << equiSet_02(arr, 0, 0, 0, "", "") << endl;
}

int main()
{
    // coinChange();
    // queenProblem();
    // crypto();
    SetProblem();
    // crossWord();
    return 0;
}