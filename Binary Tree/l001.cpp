#include <iostream>
#include <vector>
//BST PRACTICE
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

int maximumEle(Node *node) {    //logn
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
int LCAoFBST_Rec(Node *node, int a, int b) // a<b
{
    if (node == nullptr)
        return -1;

    if (b < node->data && a < node->data)
        return LCAoFBST_Rec(node->left, a, b);
    else if (a > node->data && b > node->data)
        return LCAoFBST_Rec(node->right, a, b);
    else
        return node->data; //LCA point.
}

int LCAoFBST(Node *node, int a, int b) // a<b
{
    Node *curr = node;
    while (curr != nullptr)
    {
        if (b < curr->data && a < curr->data)
            curr = curr->left;
        else if (a > curr->data && b > curr->data)
            curr = curr->right;
        else
            return find(curr, a) && find(curr, b) ? curr->data : -1; //LCA point.
    }

    return -1;
}

//In Order
void allNodesInRange_01(Node *node, int a, int b, vector<int> &ans)
{
    if (node == nullptr)
        return;

    allNodesInRange_01(node->left, a, b, ans);

    // sorted Region
    if (node->data >= a && node->data <= b)
        ans.push_back(node->data);
    
    allNodesInRange_01(node->right, a, b, ans);
}

//pre Order
void allNodesInRange_02(Node *node, int a, int b, vector<int> &ans)
{
    if (node == nullptr)
        return;
    if (node->data >= a && node->data <= b)
        ans.push_back(node->data);

    if (b < node->data && a < node->data)
        allNodesInRange_02(node->left, a, b, ans);
    else if (a > node->data && b > node->data)
        allNodesInRange_02(node->right, a, b, ans);
    else //LCA Region.
    {
        allNodesInRange_02(node->left, a, b, ans);
        allNodesInRange_02(node->right, a, b, ans);
    }
}

void preAndSucc_InOrder(Node *node, int data) {
    Node *curr = node;
    Node *pred = nullptr;
    Node *succ = nullptr;
    while(curr != nullptr) {
        if(curr->data == data) {
            //Pred
            if(curr->left == nullptr) {
                cout << "Pred: " << (pred == nullptr? -1 : pred->data) << endl;
            } else {
                pred = curr->left;
                while(pred->right != nullptr)
                    pred = pred->right;
                cout << "Pred: " << pred->data <<endl;
            }

            //Succ
            if(curr->right == nullptr) {
                cout << "Succ: " << (succ == nullptr? -1 : succ->data) << endl;
            } else {
                succ = curr->right;
                while(succ->left != nullptr)
                    succ = succ->left;
                cout << "Succ: " << succ->data <<endl;
            }
            
        } else if (data < curr->data) {
            succ = curr;
            curr = curr->left;
        } else {
            pred = curr;
            curr = curr->right;
        }
    }
}


void solve()
{
    vector<int> arr = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130};
    Node *root = constructBST(arr, 0, arr.size() - 1);
    display(root);
}

int main()
{
    solve();
    return 0;
}