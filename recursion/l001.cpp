#include <iostream>
#include <vector>
using namespace std;

void printInc(int a, int b) {
    if(a == b + 1)      
        return;
    cout << a << ", ";
    printInc(a + 1, b);
}

void printDec(int a, int b) {
    if(a == b + 1)      
        return;
    printDec(a + 1, b);
    cout << a << ", ";
}

void printIncDec(int a, int b) {
    if(a == b + 1)      
        return;
    if(a % 2 == 0)
    cout << a << " hi" << endl;
    printIncDec(a + 1, b);
    if(a % 2 != 0)
    cout << a << " bye" << endl;
}



void recursionWithArray()
{
    // int n;
    // cin >> n;
    // vector<int> arr(n, 0);
    // for (int i = 0; i < n; i++)
    //     cin >> arr[i];

    // vector<int> arr{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
}

// int maximum(vector<int> &arr, int idx)
// {
//     if (idx == arr.size() - 1)
//         return arr[idx];

//     return max(arr[idx], maximum(arr, idx + 1));
// }


// questionSet()========================


vector<string> words = {
    ":;/",
    "abc",
    "def",
    "ghi",
    "jkl",
    "mno",
    "pqrs",
    "tuv",
    "wx",
    "yz",
    "&*%",
    "#@$",
};

vector<string> keypad(string str) {
    if(str.length() == 0) {
        vector<string> base;
        base.push_back("");
        return base;
    }

    char ch = str[0];
    string nstr = str.substr(1);
    int num = ch - '0';
    string word = words[num];
    
    vector<string>  smallAns = keypad(nstr);
    vector<string> myAns;

    for(string s : smallAns) {
        for(int i = 0; i < word.length(); i++){
            myAns.push_back(word[i] + s);
        }
    }
    return myAns;
}

int keypad(string str, string ans) {
    if(str.length() == 0)   {
        cout << ans << endl;
        return 1;
    }

    char ch = str[0];
    string nstr = str.substr(1);
    int key = ch - '0';
    string word = words[key];

    int count = 0;
    for(int i = 0; i < word.length(); i++) {
        count += keypad(nstr, ans + word[i]);
    }

    return count;
    
}

int keypad02(string str, int idx, string ans) {
    if(str.length() == idx)   {
        cout << ans << endl;
        return 1;
    }

    char ch = str[idx];
    int key = ch - '0';
    string word = words[key];

    int count = 0;
    for(int i = 0; i < word.length(); i++) {
        count += keypad02(str, idx + 1, ans + word[i]);
    }

    if(idx + 1 < str.length()) {    //checking if the string have atleast two digits 
        int num = (str[idx] - '0') * 10 + str[idx + 1] - '0';
        if(num == 10 || num == 11) {
            string word = words[num];
             for(int i = 0; i < word.length(); i++) {
                count += keypad02(str, idx + 2, ans + word[i]);
            }
        }
    }

    return count;
    
}

void questionSet() {
    // vector<string> ans = keypad("489");
    // // cout << keypad("489", "");
    // for(string s : ans){
    //     cout << s << " ";
    // }
    cout << keypad02("1011489", 0,  "");
}

void basics() {
    int a, b;
    cin >> a >> b;
    // printInc(a, b);
    // printDec(a, b);
    printIncDec(a, b);
}



//pathType Questions======================================
vector<string> mazepathMulti_HVD(int sr, int sc, int dr, int dc) {
    if(sr == dr && sc == dc) {
        vector<string> base;
        base.push_back("");
        return base;
    }
    
    vector<string> myAns;
    for(int jump = 1; sr + jump <= dr; jump++) {
        vector<string> rres = mazepathMulti_HVD(sr + jump, sc, dr, sc);
        for(string s : rres) {
            myAns.push_back('H' + to_string(jump) + s);
        }
    }

    for(int jump = 1; sr + jump <= dr; jump++) {
        vector<string> rres = mazepathMulti_HVD(sr, sc + jump, dr, sc);
        for(string s : rres) {
            myAns.push_back('V' + to_string(jump) + s);
        }
    }

    for(int jump = 1; sr + jump <= dr; jump++) {
        vector<string> rres = mazepathMulti_HVD(sr + jump, sc + 1, dr, sc);
        for(string s : rres) {
            myAns.push_back('D' + to_string(jump) + s);
        }
    }
} 


vector<string> mazePath_HVD(int sr, int sc, int dr, int dc) {
    if(sr == dr && sc == dc) {
        vector<string> base;
        base.push_back("");
        return base;
    }
    

    vector<string> myAns;
    if(sr + 1 <= dr) {
        vector<string> Verti = mazePath_HVD(sr + 1, sc, dr, sc);
        for(string s : Verti) {
            myAns.push_back('H' + s);
        }
    }
    if(sc + 1 <= dc) {
        vector<string> Hori = mazePath_HVD(sr, sc + 1, dr, sc);
        for(string s : Hori) {
            myAns.push_back('V' + s);
        }
    }

    if(sr + 1 <= dr && sc + 1 <= dc) {
        vector<string> Diag = mazePath_HVD(sr + 1, sc + 1, dr, sc);
        for(string s : Diag) {
            myAns.push_back('D' + s);
        }
    }
}

int mazepath_MultiHVD(int sr, int sc, int er, int ec, string ans)
{
    if (sr == er && sc == ec)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int jump = 1; sr + jump <= er; jump++)
        count += mazepath_MultiHVD(sr + jump, sc, er, ec, ans + "V" + to_string(jump));

    for (int jump = 1; sc + jump <= ec; jump++)
        count += mazepath_MultiHVD(sr, sc + jump, er, ec, ans + "H" + to_string(jump));

    for (int jump = 1; sr + jump <= er && sc + jump <= ec; jump++)
        count += mazepath_MultiHVD(sr + jump, sc + jump, er, ec, ans + "D" + to_string(jump));

    return count;
}

vector<vector<int>> dirA = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
vector<string> dirS = {"L", "N", "U", "E", "R", "S", "D", "W"};

int floodfill(int sr, int sc, int dr, int dc, vector<vector<int>> &board, string ans) { //0 free cell, 1 blocked
    if(sr == dr && sc == dc) {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    board[sr][sc] = 1;  //mark
    for(int i = 0; i < dirA.size(); i++) {
        int x = sr + dirA[i][0];
        int y = sc + dirA[i][1];

        if(x >= 0 && x < board.size() && y >= 0 && y < board[0].size() && board[x][y] == 0)
            count += floodfill(x, y, dr, dc, board, ans + dirS[i]);
    }
    board[sr][sc] = 0;
    return count;
}

class FFPair {
    public:
    string path;
    int len;
    FFPair(string path_, int len_) {
        path = path_;
        len = len_;
    }
};

FFPair longestHeight(int sr, int sc, int dr, int dc, vector<vector<int>> &board) { //0 free cell, 1 blocked
    if(sr == dr && sc == dc) {
        // cout << ans << endl;
        FFPair base("", 0);
        return base;
    }

    // int maxHeight = 0;
    FFPair myAns("", 0);
    board[sr][sc] = 1;  //mark
    for(int i = 0; i < dirA.size(); i++) {
        for(int jump = 0; jump < board.size(); jump ++) {
        int x = sr + jump * dirA[i][0];
        int y = sc + jump * dirA[i][1];

        if(x >= 0 && x < board.size() && y >= 0 && y < board[0].size() && board[x][y] == 0){
            FFPair smallAns = longestHeight(x, y, dr, dc, board);
            if(myAns.len < smallAns.len + 1) {
                myAns.len = smallAns.len + 1;
                myAns.path = dirS[i] + to_string(jump) + smallAns.path;
            }
        }
        }
    }
    board[sr][sc] = 0;
    return myAns;
}


FFPair shortestHeight(int sr, int sc, int dr, int dc, vector<vector<int>> &board) { //0 free cell, 1 blocked
    if(sr == dr && sc == dc) {
        // cout << ans << endl;
        FFPair base("", 0);
        return base;
    }

    // int maxHeight = 0;
    FFPair myAns("", board.size() * board[0].size());
    board[sr][sc] = 1;  //mark
    for(int i = 0; i < dirA.size(); i++) {
        for(int jump = 1; jump < 2; jump ++) {
        int x = sr + jump * dirA[i][0];
        int y = sc + jump * dirA[i][1];

        if(x >= 0 && x < board.size() && y >= 0 && y < board[0].size() && board[x][y] == 0){
            FFPair smallAns = shortestHeight(x, y, dr, dc, board);
            if(smallAns.len + 1 < myAns.len ) {
                myAns.len = smallAns.len + 1;
                myAns.path = dirS[i] + to_string(jump) + smallAns.path;
            }
        }
        }
    }
    board[sr][sc] = 0;
    return myAns;
}

// int shortestHeight(int sr, int sc, int dr, int dc, vector<vector<int>> &board) { //0 free cell, 1 blocked
//     if(sr == dr && sc == dc) {
//         // cout << ans << endl;
//         return 0;
//     }

//     int minHeight = board.size() * board[0].size();     
//     board[sr][sc] = 1;  //mark
//     for(int i = 1; i < dirA.size(); i++) {
//         // for(int jump = 0; jump < board.size(); jump ++) {
//             int x = sr +  dirA[i][0];
//             int y = sc + dirA[i][1];

//             if(x >= 0 && x < board.size() && y >= 0 && y < board[0].size() && board[x][y] == 0)
//                 minHeight = min(minHeight, shortestHeight(x, y, dr, dc, board));
//         }
//     // }
//     board[sr][sc] = 0;
//     return minHeight + 1;
// }


void pathType() {

    // int n = 3, m = 5;
    // cout << mazepath_MultiHVD(0, 0, n - 1, m - 1, "") << endl;
    // cout << mazepath_MultiHVD(0, 0, 3, 3, "");
    // for(string s : ans) {
    //     cout << s << endl;
    // }
    vector<vector<int>> board(3, vector<int>(3, 0)); 
    // FFPair ans =  longestHeight(0, 0, 2, 2, board);
    FFPair ans =  shortestHeight(0, 0, 2, 2, board);
    cout << ans.len << " " << ans.path << endl;
}

void solve() {
    // basics();
    // recursionWithArray();
    // questionSet();
    pathType();
}

int main() {
    solve();
    return 0;
}