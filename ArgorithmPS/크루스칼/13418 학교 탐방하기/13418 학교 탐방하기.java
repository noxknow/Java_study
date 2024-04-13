import java.io.*;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {

        int start, end, e;

        Node(int start, int end, int e) {
            this.start = start;
            this.end = end;
            this.e = e;
        }

        @Override
        public int compareTo(Node o) {
            return this.e - o.e;
        }
    }

    static int n, m, maxCost, minCost;
    static int[] parent;
    static List<Node> graph;
    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            graph.add(new Node(v1, v2, e));
        }

        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }
        
        Collections.sort(graph);

        for (int i = 0; i < graph.size(); i++) {
            Node node = graph.get(i);
            if (find(node.start) != find(node.end)) {
                union(node.start, node.end);

                if (node.e == 0) {
                    maxCost++;
                }
            }
        }

        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = graph.size() - 1; i >= 0; i--) {
            Node node = graph.get(i);
            if (find(node.start) != find(node.end)) {
                union(node.start, node.end);

                if (node.e == 0) {
                    minCost++;
                }
            }
        }

        System.out.println(maxCost * maxCost - minCost * minCost);

        br.close();
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
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
