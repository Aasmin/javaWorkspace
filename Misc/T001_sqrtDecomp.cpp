#include <iostream>
#include <vector>
#include <cmath>

using namespace std;

int bs = 0;
vector<int> blocks;
vector<int> arr;

void solve()
{
    int n; //array size
    cin >> n;
    bs = (int)sqrt(n);
    blocks.resize(bs + 1, 0);

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
            cout << query(l , r);
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