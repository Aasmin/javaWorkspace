import java.util.ArrayList;

public class l001{
    public static class Edge{
        int v = 0, w = 0;   //v -> neighbour, w -> weight
        Edge(int v, int w) {this.v = v; this.w = w;}
    }
    
    public static int N = 7;
    public static ArrayList<Edge>[] graph;    //graph[r].get(c);
    // public static ArrayList<ArrayList<Edge>> graph;  //graph.get(r).get(c);

    public static void constructGraph() {
        graph = new ArrayList[N];
        for(int i = 0; i < N; i++) 
            graph[i] = new ArrayList<Edge>();

		addEdge(graph, 0, 1, 20);
		addEdge(graph, 0, 3, 10);
		addEdge(graph, 1, 2, 10);
		addEdge(graph, 2, 3, 40);
		addEdge(graph, 3, 4, 2);
		addEdge(graph, 4, 5, 2);
		addEdge(graph, 4, 6, 3);
        addEdge(graph, 5, 6, 8);
        
        display(graph);        
    }

    public static void addEdge(ArrayList<Edge>[] gp, int u, int v, int w) {
        gp[u].add(new Edge(v, w));
        gp[v].add(new Edge(u, w));
    }

    public static void display(ArrayList<Edge>[] gp) {
        for(int i = 0; i < gp.length; i++) {
            System.out.print(i + "-> ");
            for(Edge e : gp[i]) {
                System.out.print("(" + e.v + ", " + e.w + "), ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int findEdge(int v1, int v2)
    {
        int vtx = -1;
        for (int i = 0; i < graph[v1].size(); i++)
        {
            Edge e = graph[v1].get(i);
            if (e.v == v2)
            {
                vtx = i;
                break;
            }
        }
        return vtx;
    }
    
    public static boolean removeEdge(int u, int v) {
        int idx1 = findEdge(u, v);
        int idx2 = findEdge(v, u);
        if(idx1 != -1 && idx2 != -1) {
            graph[u].remove(idx1);
            graph[v].remove(idx2);
            return true;
        }
        return false;
    }

    public static void removeVtx(int vtx) { 
        while(graph[vtx].size() != 0) {
            Edge e = graph[vtx].get(0);
            removeEdge(vtx, e.v);
        }
    }

    //Questions.====================================================================
    /**
     * DFS psseudo code:
     * 1. Mark
     * 2. From all unvisited negihbours
     *  2.1 Call DFS(neighbour)
     */
    static boolean[] visited;
    public static boolean hasPath(boolean[] visited, int src, int dest) {
        if(src == dest) return true;
        visited[src] = true;
        
        boolean res = false;
        for(Edge e : graph[src]) {
            if(!visited[e.v])
            res = res || hasPath(visited, e.v, dest);
        }
        return res;
    }

    static int allPath(int src, int dest, boolean[] vis, int w, String ans)
    {
        if (src == dest)
        {
            System.out.println(ans + dest + " @ " + w );
            return 1;
        }
    
        vis[src] = true;
    
        int count = 0;
        for (Edge e : graph[src])
        {
            if (!vis[e.v])
                count += allPath(e.v, dest, vis, w + e.w, ans + src + " ");
        }
    
        vis[src] = false;
        return count;
    }

    static void set1() {
        visited = new boolean[N];
        String str = "";    int wei = 0;
        // System.out.println(hasPath(visited, 3, 4));
        System.out.println(allPath(0, 6, visited, wei,  str));

    }

    public static void solve() {
        constructGraph();
        // System.out.println(removeEdge(3, 4));
        // removeVtx(3);
        // display(graph);
        set1();
    }
 
    public static void main(String[] args) {
        solve();
    }
}