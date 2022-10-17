import java.util.*;

class GraphLib {

    public static ArrayList<ArrayList<Integer>> GetGraph(int n, int m) {
        ArrayList<ArrayList<Integer>> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt() - 1;
            int v = sc.nextInt() - 1;
            a.get(u).add(v);
            a.get(v).add(u);
        }
        return a;
    }

    public static ArrayList<ArrayList<Pair>> GetGraphwithWeight(int n, int m) {
        ArrayList<ArrayList<Pair>> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt() - 1;
            int v = sc.nextInt() - 1;
            int w = sc.nextInt();
            a.get(u).add(new Pair(v, w));
            a.get(v).add(new Pair(u, w));
        }
        return a;
    }

    public static void diji(PriorityQueue<Pair> q, ArrayList<ArrayList<Pair>> a) {
        // int vis[]=new int[a.size()];
        boolean vis[] = new boolean[a.size()];
        // vis[0]=1;
        Arrays.fill(vis, false);
        int n = a.size();
        long val[] = new long[n];

        long inf = Long.MAX_VALUE;
        Arrays.fill(val, inf);
        q.add(new Pair(0, 0));
        val[0] = 0;
        int p[] = new int[n];
        p[0] = -1;
        while (q.size() > 0) {
            Pair r = q.remove();
            if (vis[r.i])
                continue;
            vis[r.i] = true;/// removed
            int u = r.i;
            for (int i = 0; i < a.get(u).size(); i++) {

                int v = a.get(u).get(i).i;
                long w = a.get(u).get(i).j;
                if (val[v] > r.j + w) {
                    p[v] = u;
                    val[v] = r.j + w;
                    q.add(new Pair(v, val[v]));
                }
            }
        }
        // pw.println(Arrays.toString(p));
        int i = n - 1;
        Stack<Integer> st = new Stack<>();
        if (val[i] == inf) {
            pw.println(-1);
            return;
        }
        while (i != -1) {
            st.add(i + 1);
            i = p[i];
        }
        while (st.size() > 0) {
            pw.print(st.pop() + " ");
        }

    }

    public static void diji(PriorityQueue<Pair> q, ArrayList<ArrayList<Pair>> a) {
        // int vis[]=new int[a.size()];
        boolean vis[] = new boolean[a.size()];
        // vis[0]=1;
        Arrays.fill(vis, false);
        int n = a.size();
        long val[] = new long[n];

        long inf = Long.MAX_VALUE;
        Arrays.fill(val, inf);
        q.add(new Pair(0, 0));
        val[0] = 0;
        int p[] = new int[n];
        p[0] = -1;
        while (q.size() > 0) {
            Pair r = q.remove();
            if (vis[r.i])
                continue;
            vis[r.i] = true;/// removed
            int u = r.i;
            for (int i = 0; i < a.get(u).size(); i++) {

                int v = a.get(u).get(i).i;
                long w = a.get(u).get(i).j;
                if (val[v] > r.j + w) {
                    p[v] = u;
                    val[v] = r.j + w;
                    q.add(new Pair(v, val[v]));
                }
            }
        }
        // pw.println(Arrays.toString(p));
        int i = n - 1;
        Stack<Integer> st = new Stack<>();
        if (val[i] == inf) {
            pw.println(-1);
            return;
        }
        while (i != -1) {
            st.add(i + 1);
            i = p[i];
        }
        while (st.size() > 0) {
            pw.print(st.pop() + " ");
        }

    }

    static void dfs(int src, ArrayList<ArrayList<Integer>> list, int vis[]) {
        // Here 0-white,1-gray & 2-black;
        // Time complexity O(n+m)
        System.out.print(src + " ");
        vis[src] = 1;
        for (int i = 0; i < list.get(src).size(); i++) {
            if (vis[list.get(src).get(i)] == 0) {
                dfs(list.get(src).get(i), list, vis);
            }
        }
        vis[src] = 2;
    }

    public static void briges(int r, int p, ArrayList<ArrayList<Integer>> a, ArrayList<Pair> b, int vis[], int low[],
            int time, int tin[]) {
        vis[r] = 1;
        low[r] = tin[r] = time++;
        for (int i = 0; i < a.get(r).size(); i++) {
            int to = a.get(r).get(i);
            if (to == p)
                continue;
            if (vis[to] == 0) {
                briges(to, r, a, b, vis, low, time, tin);
                low[r] = Math.min(low[r], low[to]);
                if (tin[r] < low[to]) {
                    b.add(new Pair(r, to));
                }
            } else
                low[r] = Math.min(low[r], tin[to]);
        }
    }

    static void bfs(int s, ArrayList<ArrayList<Integer>> list, boolean vis[]) {
        // add your code here
        // if(root==null){return;}
        Deque<Integer> qu = new LinkedList<>();
        qu.add(s);
        vis[s] = true;
        while (qu.size() > 0) {
            int n = qu.size();
            while (n-- > 0) {
                int root = qu.remove();
                System.out.print(root + " ");
                for (int i = 0; i < list.get(root).size(); i++) {
                    int node = list.get(root).get(i);
                    if (!vis[node]) {
                        vis[node] = true;
                        qu.add(node);
                    }
                }
            }
        }
    }

    public static void toposo(ArrayList<ArrayList<Integer>> a, int vis[], int r, int cycle[], String st[]) {
        vis[r] = 1;
        if (cycle[0] == 1)
            return;
        for (int i = 0; i < a.get(r).size(); i++) {
            int v = a.get(r).get(i);
            if (vis[v] == 1) {
                cycle[0] = 1;
            } else if (vis[v] == 0) {
                toposo(a, vis, v, cycle, st);
            }
        }
        vis[r] = 2;
        st[0] += (char) (r + 'a');
    }
}
