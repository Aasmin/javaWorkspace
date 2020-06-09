#include <iostream>
#include <vector>
#include <cmath>
#include <algorithm>

using namespace std;

int bs = 0;
vector<vector<pair<int, int>>> blocks;  //pair(int num, int index )
vector<int> arr;

int query(int li, int ri) {

}

void update(int idx, int val) { //O(1).

}

void solve()
{
    int n; //array size
    cin >> n;
    bs = (int)sqrt(n);
    blocks.resize(bs + 1);
    arr.resize(n, 0);

    //input the numbers
    for (int i = 0; i < n; i++)
    {
        int a;
        cin >> a;
        arr[i] = a;
        blocks[i / bs].push_back({a, i});   //values are stored in blocks 
    }

    //now blocks are intended to be sort individually (blocks[i] is being sort)
    for(int i = 0; i < blocks.size(); i++)
        sort(blocks[i].begin(), blocks[i].end());   

    //input the queries
    char q;  cin >> q; 
    while(q--) {
        int c;  cin >> c;   //query type
        if(c == 'C') { // query
            int l, r;
            cin >> l >> r;
            l--;    r--;    //As humara algo index 0 se shuru hota hai, and in que it starts from 1
            cout << query(l , r) << endl;
        } else {    //update
            int idx, val;
            cin >> idx >> val;
            idx--;      //As humara algo index 0 se shuru hota hai, and in que it starts from 1
            update(idx, val);
        }
    }
}

int main()
{
    int t = 1; //test cases
    // cin >> t;
    while (t--)
        solve();
    return 0;
}