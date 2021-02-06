import java.util.ArrayList;
import java.util.LinkedList;

public class l001{
    public static class Edge{
        int v = 0, w = 0;   //v -> neighbour, w -> weight
        Edge(int v, int w) {this.v = v; this.w = w;}
    }
    
    public static int N = 9;    //N = Vertex
    public static ArrayList<Edge>[] graph;    //Tp access: graph[r].get(c);
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
        
        addEdge(graph, 2, 7, 8);
        addEdge(graph, 7, 8, 8);
        addEdge(graph, 8, 2, 8);

        
        // addEdge(graph, 2, 5, 2);
        
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

    public static void preOrder(int src, boolean[] visited, int w, String ans)
    {
        visited[src] = true;

        System.out.println(ans + " @ " + w);
        for (Edge e : graph[src])
            if (!visited[e.v])
                preOrder(e.v, visited, w + e.w, ans + src + " ");

        visited[src] = false;
    }

    public static class allSolutionPair {
        int lightW = Integer.MAX_VALUE;
        int heavyW = Integer.MIN_VALUE;
        int ceil = Integer.MAX_VALUE;
        int floor = Integer.MIN_VALUE;
    }

    public static void ceilFloor(int src, int dst, boolean[] visited, int w, int val, allSolutionPair pair, String ans) {
        if(src == dst) {
            pair.heavyW = Math.max(pair.heavyW, w);
            pair.lightW = Math.min(pair.lightW, w);

            if(w > val)     pair.ceil = Math.min(pair.ceil, w);
            if(w < val)     pair.floor = Math.max(pair.floor, w);
            System.out.println(ans + " @ " + w);
            return;
        }

        visited[src] = true;
        for(Edge e: graph[src]) {
            if(!visited[e.v]) {
                ceilFloor(e.v, dst, visited, w + e.w, val, pair, ans + " " + e.v);
            }
        }
        visited[src] = false;
    }

    // A Hamiltonian path, also called a Hamilton path, is a graph path between two vertices of a graph that 
    // visits each vertex exactly once.
    //Finding Hamiltonian Paths and Cycles
    public static void hamiltonianPath(int src, int oSrc, boolean[] visited, String ans, int count) {
        if(count == N) {    //maintain the count to seek if every vtx visited
            if(findEdge(src, oSrc) != -1) //Check adjacent vtx is the org source to find the cycle
                System.out.println("Cycle: " + ans + " " + src);
            else 
                System.out.println("Path: " + ans + " " + src);
        }

        visited[src] = true;
        for(Edge e: graph[src]) {
            if(!visited[e.v]) 
                hamiltonianPath(e.v, oSrc, visited, ans + " " + src, count + 1);
        }
        visited[src] = false;
    }
    
    //getConnectedComponent - Finding the number of components (which are not connected) in a given graph.
    public static int getConnectedComponent() {
        boolean[] vis = new boolean[N];
        int count = 0;
        int maxSize = 0;
        for(int i = 0; i < N; i++) {
            if(!vis[i]) {
                count++;
                maxSize = Math.max(maxSize, GCC_dfs(i, vis));
            }
        }
        System.out.println("Max size of component: " + maxSize);
        return count;
    }

    public static int GCC_dfs(int src, boolean[] vis) {
        vis[src] = true;
        int count = 0;
        for(Edge e : graph[src]) {
            if(!vis[e.v]) 
                count += GCC_dfs(e.v, vis);
        }
        return count + 1;
    }


    // Graph 3 
    public static class pair {
        int vtx;
        String psf;
        int level;

        pair(int vtx, String psf) {
            this.vtx = vtx; this.psf = psf;
        } 
        pair(int vtx, String psf, int lvl) {
            this.vtx = vtx; this.psf = psf; this.level = lvl;
        } 
    }

    //Breadth First Search
	public static void BFS(int src,boolean[] vis){  //delimiter method
		LinkedList<pair> que=new LinkedList<>();
		que.addLast(new pair(src,src+""));
		que.addLast(null);
		int desti=6;

		int level=0;
		while(que.size()!=1){
			pair rvtx=que.removeFirst();

			if(vis[rvtx.vtx]){
				System.out.println("Cycle: " + rvtx.psf);
				continue;
			}

			if(rvtx.vtx==desti){
				System.out.println("destinantion: " + rvtx.psf + " -> " + level);
			}

			vis[rvtx.vtx]=true;

			for(Edge e: graph[rvtx.vtx]){
				if(!vis[e.v])
				   que.addLast(new pair(e.v,rvtx.psf+ e.v));
			}

			if(que.getFirst()==null){
				level++;
				que.removeFirst();
				que.addLast(null);
			}
		}
    }
    
    public static void BFS_02(int src,boolean[] vis){
		LinkedList<pair> que=new LinkedList<>();
		que.addLast(new pair(src,src+"", 0));   //addding levels in the pair
		que.addLast(null);
		int desti=6;

		while(que.size()!=0){
			pair rvtx=que.removeFirst();

			if(vis[rvtx.vtx]){
				System.out.println("Cycle: " + rvtx.psf);
				continue;
			}

			if(rvtx.vtx==desti){
				System.out.println("destinantion: " + rvtx.psf + " -> " + rvtx.level);
			}

			vis[rvtx.vtx]=true;

			for(Edge e: graph[rvtx.vtx]){
				if(!vis[e.v])
				   que.addLast(new pair(e.v,rvtx.psf+ e.v, rvtx.level+1));
			}
		}
	}


// void BFS_03(int src, boolean[] vis)
// {

//     queue<pair<int, string>> que;
//     que.push({src, to_string(src) + ""});

//     int desti = 6;

//     while (que.size() != 0)
//     {
//         pair<int, string> vtx = que.front();
//         que.pop();

//         if (vis[vtx.first])
//         { //cycle.
//             cout << "Cycle: " << vtx.second << endl;
//             continue;
//         }

//         if (vtx.first == desti)
//         {
//             cout << "destination: " << vtx.second << endl;
//         }

//         vis[vtx.first] = true;
//         for (Edge e : graph[vtx.first])
//         {
//             if (!vis[e.v])
//                 que.push({e.v, vtx.second + to_string(e.v)});
//         }
//     }
// }


    static void set1() {
        visited = new boolean[N];
        // String str = "";    int wei = 0;
        // // System.out.println(hasPath(visited, 3, 4));
        // System.out.println(allPath(0, 6, visited, wei,  str));
        // preOrder(0, visited, 0, "");

        // allSolutionPair pair = new allSolutionPair();
        // ceilFloor(0, 6, visited, 0, 20, pair, "0");
        // System.out.println("\nMax: " + pair.heavyW + "\nMin: " + pair.lightW + "\nCeil: " + pair.ceil + "\nFloor: " + pair.floor);

        // hamiltonianPath(2, 2, visited, "", 1);

        // System.out.println(getConnectedComponent());

        BFS(0, visited);
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