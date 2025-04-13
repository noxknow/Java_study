import java.io.*;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {

        int x, y, wall;

        Node(int x, int y, int wall) {
            this.x = x;
            this.y = y;
            this.wall = wall;
        }

        @Override
        public int compareTo(Node o) {
            return this.wall - o.wall;
        }
    }

    static int n, m;
    static int[][] maze;
    static int[][] walls;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        maze = new int[m][n];
        walls = new int[m][n];
        for (int i = 0; i < m; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                maze[i][j] = Character.getNumericValue(str.charAt(j));
            }
        }

        dijkstra();

        System.out.println(walls[m - 1][n - 1]);

        br.close();
    }

    public static void dijkstra() {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[m][n];

        pq.add(new Node(0, 0, 0));
        visited[0][0] = true;
        while (!pq.isEmpty()) {
            Node tmp = pq.poll();

            for (int i = 0; i < 4; i++) {
                int nx = tmp.x + dx[i];
                int ny = tmp.y + dy[i];

                if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                if (visited[nx][ny]) continue;

                if (maze[nx][ny] == 1) {
                    pq.add(new Node(nx, ny, tmp.wall + 1));
                    walls[nx][ny] = tmp.wall + 1;
                } else {
                    pq.add(new Node(nx, ny, tmp.wall));
                    walls[nx][ny] = tmp.wall;
                }

                visited[nx][ny] = true;
            }
        }
    }
}
