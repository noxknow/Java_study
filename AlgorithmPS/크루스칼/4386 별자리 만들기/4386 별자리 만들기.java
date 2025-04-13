import java.util.*;
import java.io.*;

public class Main {

    static class Graph {

        int idx;
        double x, y;

        Graph(int idx, double x, double y) {
            this.idx = idx;
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge> {

        int start, end;
        double dis;

        Edge(int start, int end, double dis) {
            this.start = start;
            this.end = end;
            this.dis = dis;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.dis, o.dis);
        }
    }

    static BufferedReader br;
    static int n;
    static double ans;
    static Graph[] graphs;
    static List<Edge> edges;
    static int[] parent;
    public static void main(String[] args) throws Exception {

//      br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new FileReader("input.txt"));

        n = Integer.parseInt(br.readLine());

        fillGraph();
        connectNodes();

        ans = 0;
        kruskal();

        System.out.println(ans);

        br.close();
    }

    private static void fillGraph() throws Exception {

        graphs = new Graph[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());

            graphs[i] = new Graph(i, x, y);
        }
    }

    private static void connectNodes() {

        edges = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double dis = calculateDis(graphs[i], graphs[j]);

                edges.add(new Edge(graphs[i].idx, graphs[j].idx, dis));
            }
        }
    }

    private static void kruskal() {

        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        Collections.sort(edges);

        for (Edge edge : edges) {
            if (find(edge.start) != find(edge.end)) {
                union(edge.start, edge.end);
                ans += edge.dis;
            }
        }
    }

    private static double calculateDis(Graph g1, Graph g2) {
        return Math.sqrt(Math.pow((g1.x - g2.x), 2) + Math.pow((g1.y - g2.y), 2));
    }

    private static void union(int x, int y) {

        x = find(x);
        y = find(y);

        if (y != x) {
            parent[y] = x;
        }
    }

    private static int find(int x) {

        if (x == parent[x]) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }
}
