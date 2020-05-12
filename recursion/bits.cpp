#include <iostream>
#include <vector>
using namespace std;

int offToOn(int num, int k) {
    int mask = (1 << k);
    return (num | mask);
}
int onToOff01(int num, int k) {
    int mask = (~(1 << k));     //1s complement
    return (num & mask);
}

int onToOff02(int num, int k) {
    int mask = (1 << k);     
    if((num & mask) == 0) return num;
    else return (num ^ mask);
}

int countAllSetBits_02(unsigned num) {
    int count = 0;
    while(num != 0) {
        if((num & 1) == 1) count++;
        num = (num >> 1);
    }
    return count;
}



int main() {
    // cout << offToOn(10, 0);
    // cout << onToOff01(5, 2);
    // cout << onToOff02(5, 2);
    // cout << (onToOff01(5, 2) == onToOff02(5, 2));
    // cout << countAllSetBits_02(121223);
    return 0;
}