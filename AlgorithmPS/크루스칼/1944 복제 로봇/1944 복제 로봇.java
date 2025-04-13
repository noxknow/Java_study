import java.util.*;
import java.io.*;

public class Main {

    static class Node {

        int x, y, dis;

        Node (int x, int y, int dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;
        }
    }

    static class Vertex implements Comparable<Vertex> {

        int s, e, edge;

        Vertex(int s, int e, int edge) {
            this.s = s;
            this.e = e;
            this.edge = edge;
        }

        @Override
        public int compareTo(Vertex o) {
            return this.edge - o.edge;
        }
    }

    static BufferedReader br;
    static int n, m;
    static char[][] maze;
    static boolean[][] visited;
    static List<Node> nodes;
    static PriorityQueue<Vertex> vertexes;
    static int[] parent;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    public static void main(String[] args) throws Exception {

//      br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        nodes = new ArrayList<>();
        vertexes = new PriorityQueue<>();

        fillMaze();

        for (int i = 0; i < m + 1; i++) {
            bfs(i);
        }

        System.out.println(kruskal());

        br.close();
    }

    private static void fillMaze() throws Exception {

        maze = new char[n][n];
        for (int i = 0; i < n; i++) {
            char[] words = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                maze[i][j] = words[j];

                if (maze[i][j] == 'S' || maze[i][j] == 'K') {
                    nodes.add(new Node(i, j, 0));
                }
            }
        }
    }

    private static void bfs(int start) {

        Queue<Node> q = new LinkedList<>();
        q.add(nodes.get(start));
        visited = new boolean[n][n];
        visited[nodes.get(start).x][nodes.get(start).y] = true;

        while (!q.isEmpty()) {
            Node curNode = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = curNode.x + dx[i];
                int ny = curNode.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (maze[nx][ny] == '1' || visited[nx][ny]) continue;

                if (maze[nx][ny] == 'S' || maze[nx][ny] == 'K') {
                    for (int j = 0; j < m + 1; j++) {
                        if (nodes.get(j).x == nx && nodes.get(j).y == ny) {
                            vertexes.add(new Vertex(start, j, curNode.dis + 1));
                        }
                    }
                }

                visited[nx][ny] = true;
                q.add(new Node(nx, ny, curNode.dis + 1));
            }
        }
    }

    private static int kruskal() {

        parent = new int[m + 1];
        for (int i = 0; i < m + 1; i++) {
            parent[i] = i;
        }

        int totalDis = 0;
        int edgeCount = 0;
        while (!vertexes.isEmpty()) {
            Vertex curVertex = vertexes.poll();

            if (find(curVertex.s) != find(curVertex.e)) {
                union(curVertex.s, curVertex.e);
                totalDis += curVertex.edge;
                edgeCount++;
            }
        }

        if (edgeCount != m) return -1;
        return totalDis;
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
