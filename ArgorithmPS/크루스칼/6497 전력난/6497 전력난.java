import java.io.*;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {
        int start, end, dist;

        Node(int start, int end, int dist) {
            this.start = start;
            this.end = end;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }

    static int m, n, res;
    static int[] parent;
    static List<Node> graph;
    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            res = 0;

            if (m == 0 && n == 0) {
                br.close();
                return;
            }

            graph = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int dist = Integer.parseInt(st.nextToken());

                graph.add(new Node(start, end, dist));
                res += dist;
            }

            parent = new int[m];
            for (int i = 0; i < m; i++) {
                parent[i] = i;
            }

            Collections.sort(graph);

            for (Node curNode : graph) {
                if (find(curNode.start) != find(curNode.end)) {
                    union(curNode.start, curNode.end);

                    res -= curNode.dist;
                }
            }

            System.out.println(res);
        }
    }

    public static void union(int x, int y) {
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
