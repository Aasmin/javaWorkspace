#include <iostream>
#include <vector>
#include <cmath>

using namespace std;

int bs = 0;
vector<int> blocks;
vector<int> arr;

int query(int li, int ri) {
    int sum = 0;
    while(li % bs != 0 && li <= ri)
        sum += arr[li++];

    while(li + bs <= ri) {
        sum += blocks[li/bs];
        li += bs;   //increase the index to other block
    }

    while(li <= ri) 
        sum += arr[li++];
    
    return sum;
}

void update(int idx, int val) { //O(1).
    blocks[idx/bs] = blocks[idx/bs] - arr[idx] + val;   //updating the blocks array
    arr[idx] = val;
}

void solve()
{
    int n; //array size
    cin >> n;
    bs = (int)sqrt(n);
    blocks.resize(bs + 1, 0);
    arr.resize(n, 0);

    //input the numbers
    for (int i = 0; i < n; i++)
    {
        int a;
        cin >> a;
        arr[i] = a;
        blocks[i / bs] += a;
    }

    //input the queries
    int q;  cin >> q;
    while(q--) {
        int c;  cin >> c;   //query type
        if(c == 1) { // query
            int l, r;
            cin >> l >> r;
            cout << query(l , r) << endl;
        } else {    //update
            int idx, val;
            cin >> idx >> val;
            update(idx, val);
        }
    }
}

int main()
{
    int t; //test cases
    cin >> t;
    while (t--)
        solve();
    return 0;
}