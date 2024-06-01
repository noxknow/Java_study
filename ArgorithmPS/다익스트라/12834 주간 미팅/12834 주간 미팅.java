import java.io.*;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {
        int to, dist;

        Node(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }

    static int n, v, e, a, b, res;
    static int INF = 987654321;
    static int[] positions;
    static int[] dist;
    static boolean[] visited;
    static List<List<Node>> graph;
    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        positions = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            positions[i] = Integer.parseInt(st.nextToken());
        }

        graph = new ArrayList<>();
        for (int i = 0; i < e; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Node(to, dist));
            graph.get(to).add(new Node(from, dist));
        }

        for (int position : positions) {
            dijkstra(position);

            dist[a] = dist[a] == INF ? -1 : dist[a];
            dist[b] = dist[b] == INF ? -1 : dist[b];
            res += dist[a] + dist[b] ;
        }

        System.out.println(res);

        br.close();
    }

    private static void dijkstra(int start) {

        dist = new int[v + 1];
        visited = new boolean[v + 1];
        Arrays.fill(dist, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            visited[now.to] = true;

            for (Node next : graph.get(now.to)) {

                if (!visited[next.to] && dist[next.to] > dist[now.to] + next.dist) {
                    dist[next.to] = dist[now.to] + next.dist;
                    pq.add(new Node(next.to, dist[next.to]));
                }
            }

        }
    }
}
