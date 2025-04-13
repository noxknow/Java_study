import java.io.*;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {

        int to, cost;

        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static int INF = 987654321;
    static int v, e;
    static int[] dist, ans;
    static boolean[] visited;
    static List<List<Node>> graph;
    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            for (int i = 0; i <= v; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 1; i <= e; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                graph.get(from).add(new Node(to, cost));
                graph.get(to).add(new Node(from, cost));
            }

            int k = Integer.parseInt(br.readLine());
            ans = new int[v + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < k; i++) {
                int startV = Integer.parseInt(st.nextToken());
                dijkstra(startV);

                for (int j = 1; j <= v; j++) {
                    ans[j] += dist[j];
                }
            }

            int result = Integer.MAX_VALUE;
            int res = 0;
            for (int i = 1; i <= v; i++) {
                if (ans[i] < result) {
                    result = ans[i];
                    res = i;
                }
            }

            sb.append(res).append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    private static void dijkstra(int curNode) {

        dist = new int[v + 1];
        Arrays.fill(dist, INF);
        visited = new boolean[v + 1];

        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(curNode, 0));
        dist[curNode] = 0;

        while (!q.isEmpty()) {
            Node now = q.poll();
            visited[now.to] = true;

            for (Node next : graph.get(now.to)) {

                if (!visited[next.to] && dist[next.to] > dist[now.to] + next.cost) {
                    dist[next.to] = dist[now.to] + next.cost;
                    q.add(new Node(next.to, dist[next.to]));
                }
            }
        }
    }
}
