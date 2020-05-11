#include <iostream>
#include <vector>
using namespace std;

int offToOn(int num, int k) {
    int mask = (1 << k);
    return (num | mask);
}
int onToOff(int num, int k) {
    int mask = (~(1 << k));     //1s complement
    return (num & mask);
}

int main() {
    cout << offToOn(10, 0);
    // cout << onToOff(5, 2);
    return 0;
}