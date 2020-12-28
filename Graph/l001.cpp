#include<iostream>
#include<vector>
using namespace std;

class Edge {
    public:
        int v = 0;
        int w = 0;
        Edge(int v, int w) {
            this->v = v;
            this->w = w;
        }
}

int N = 7;
vector<vector<Edge*>> graph(N, vector<Edge*());

void addEdge(vector<vector<Edge*>>& gp, int u, int v, int w) {
    gp[u].push_back(new Edge(v, w));
    gp[v].push_back(new Edge(u, w));
}

void display(vector<vector<Edge*>> &gp) {
    for(int i = 0; i < gp.size(); i++){
        cout << i << " -> ";
        for(Edge* e : gp[i]) {
            cout << "(" << e->v << " : " << e->w << "), ";
        }
        cout << endl;
    }
    cout << endl;
}

void solve(){

}

int main() {
    return 0;
}
