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



void recursionWithArray() {
    vector<int> arr{10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
    cout << maximum(arr, 0);
}

int maximum(vector<int>& arr, int idx) {
    if(idx == arr.size() - 1)   return arr[idx];
    int max1 = arr[idx];
    return max(max1, maximum(arr, idx + 1));
}

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
    if(str.length == 0) {
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
        for(int i = 0; i < word.length; i++){
            myAns.push_back(word[i] + s);
        }
    }
    return myAns;
}



void questionSet() {
    vector<string> ans = keypad("489");
    for(string s : ans){
        cout << s << " ";
    }
}

void basics() {
    int a, b;
    cin >> a >> b;
    // printInc(a, b);
    // printDec(a, b);
    printIncDec(a, b);
}


void solve() {
    // basics();
    // recursionWithArray();
    questionSet();
}

int main() {
    solve();
    return 0;
}