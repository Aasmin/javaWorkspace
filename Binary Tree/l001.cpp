#include <iostream>
#include <vector>

using namespace std;

class Node
{
public:
    int data;
    Node* left = nullptr;  // Node* left=nullptr;
    Node* right = nullptr; // Node* right=nullptr;

    Node(int data)
    {
        this->data = data;
    }

    Node()
    {
    }
};

int idx = 0;
Node *constructTree(vector<int> &arr, int si, int ei)
{
    if(si > ei) return nullptr;

    int mid = (si + ei) >> 1;

    Node *node = new Node(arr[mid]);
    node->left = constructTree(arr, si, mid - 1);
    node->right = constructTree(arr, mid + 1, ei);

    return node;
}

void display(Node *node)
{
    if (node == nullptr)
        return;

    string str = "";
    str += ((node->left != nullptr) ? to_string(node->left->data) : ".");
    str += " <- " + to_string(node->data) + " -> ";
    str += ((node->right != nullptr) ? to_string(node->right->data) : ".");
    cout << (str) << endl;

    display(node->left);
    display(node->right);
}

void solve()
{
    vector<int> arr = {10, 20, 40, -1, -1, 50, 80, -1, -1, 90, -1, -1, 30, 60, 100, -1, -1, -1, 70, 110, -1, -1, 120, -1, -1};
    Node *root = constructTree(arr);
    display(root);
}

int main(){
    solve();
    return 0;
}