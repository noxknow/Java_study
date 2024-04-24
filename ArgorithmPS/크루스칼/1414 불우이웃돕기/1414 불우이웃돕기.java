import java.io.*;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {

        int to, from, value;

        Node(int to, int from, int value) {
            this.to = to;
            this.from = from;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return this.value - o.value;
        }
    }

    static int n, res, total;
    static int[] parent;
    static char[][] connection;
    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        n = Integer.parseInt(br.readLine());
        connection = new char[n][n];

        for (int i = 0; i < n; i++) {
            connection[i] = br.readLine().toCharArray();
        }

        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        Queue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ('a' <= connection[i][j] && connection[i][j] <= 'z') {
                    total += connection[i][j] - 96;
                    pq.add(new Node(i + 1, j + 1, connection[i][j] - 96));
                } else if ('A' <= connection[i][j] && connection[i][j] <= 'Z') {
                    total += connection[i][j] - 38;
                    pq.add(new Node(i + 1, j + 1, connection[i][j] - 38));
                }
            }
        }

        int cnt = 1;
        while (!pq.isEmpty()) {
            Node curNode = pq.poll();

            if (find(curNode.to) != find(curNode.from)) {
                union(curNode.to, curNode.from);
                res += curNode.value;
                cnt++;
            }
        }

        if (cnt != n) {
            System.out.println(-1);
        } else {
            System.out.println(total - res);
        }

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
