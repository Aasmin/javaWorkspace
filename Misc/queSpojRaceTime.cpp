#include <iostream>
#include <vector>
#include <cmath>
#include <algorithm>

using namespace std;

int bs = 0;
vector<vector<pair<int, int>>> blocks;  //pair(int num, int index ) 

//[COMMON PRACTICE BY SOME]
// const int bs = 350; // as given N = 100,000. Therefore, sqrt(N) ~ 316 
// vector<pair<int, int>> blocks[bs];  //pair(int num, int index ) 

//[COMMON PRACTICE BY SOME IN JAVA] 
// ArrayList<int[]>[] blocks = new ArrayList<>[bs]; blocks[b].get(i)[0] == first; blocks[b].get(i)[1] == second
vector<int> arr;

int query(int li, int ri, int x) {  //x == value from the query
    int count = 0;
    while(li % bs != 0 && li <= ri)
        if(arr[li++] <= x)  count++;

    while(li + bs <= ri) {  //if duplicates are found, then iterator gives the last duplicate element
        int b = li /bs;
        count += lower_bound(blocks[b].begin(), blocks[b].end(), make_pair(x, (int)1e7)) - blocks[b].begin();
        li += bs;   //increase the index to other block
    }

    while(li <= ri) 
        if(arr[li++] <= x)  count++;
    
    return count;
}

void update(int idx, int val) { //O(logn).
    arr[idx] = val;
    int b = idx / bs; 
    for(int i = 0; i < blocks[b].size(); i++) {
        if(blocks[b][i].second == idx){
            blocks[b][i].first = val;   //first == pair ka first element i.e pair<first, second>
            sort(blocks[b].begin(), blocks[b].end());   //as values are changed so sort again 
            break;
        }
    }
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
            int l, r, x;
            cin >> l >> r >> x;
            l--;    r--;    //As humara algo index 0 se shuru hota hai, and in que it starts from 1
            cout << query(l , r, x) << endl;
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