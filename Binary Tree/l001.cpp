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
Node *constructBST(vector<int> &arr, int si, int ei)        //O(n)
{
    if(si > ei) return nullptr;

    int mid = (si + ei) >> 1;

    Node *node = new Node(arr[mid]);
    node->left = constructBST(arr, si, mid - 1);
    node->right = constructBST(arr, mid + 1, ei);

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

//basics.========================================================================

int height(Node *node) {
    if(node == nullptr) return -1;

    return max(height(node->left), height(node->right)) + 1;
}

int size(Node *node) {
    if(node == nullptr) return 0;

    return size(node->left) +  size(node->right) + 1;
}

bool find(Node *node, int data) {   //O(log n)
    Node *curr = node;

    while(curr != nullptr) {
        if(curr->data == data)  return true;
        else if(curr->data > data)  curr = curr->left;
        else    curr = curr->right;
    }

    return false;
}

bool findRec (Node *node, int data) {   //O(log n)
    if(node == nullptr) return false;
    if(node-> data == data) return true;
    bool res = false;
    if(node->data > data) res = res || findRec(node->left, data) ;
    if(node->data < data) res = res || findRec(node->right, data) ;
    return res;
}

int maximumEle(Node *node) {
    if(node == nullptr) return -1;
    while(node->right != nullptr) {
        node = node->right;
    }
    return node->data;
}

int minimumEle(Node *node) {
    if(node == nullptr) return (int) -1e8;
    while(node->left != nullptr)
        node = node->left;
    return node->data;
}

Node *lca (Node node) {
    
}

void solve()
{
    // vector<int> arr = {10, 20, 40, -1, -1, 50, 80, -1, -1, 90, -1, -1, 30, 60, 100, -1, -1, -1, 70, 110, -1, -1, 120, -1, -1};
    vector<int> arr = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130};
    Node *root = constructBST(arr, 0, arr.size() - 1);
    display(root);
    // cout << height(root) << endl;
    // cout << size(root) << endl;
    cout << find(root, 20) << endl;
    cout << findRec(root, 20) << endl;
    // cout << maximumEle(root) << endl;
    // cout << minimumEle(root) << endl;
}

int main(){
    solve();
    return 0;
}