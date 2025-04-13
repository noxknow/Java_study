import java.io.*;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {

        int x, y, dir, cnt;

        Node(int x, int y, int dir, int cnt) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o) {
            return this.cnt - o.cnt;
        }
    }

    static int n, res;
    static Node startNode;
    static PriorityQueue<Node> pq;
    static int[][] visited;
    static char[][] houseMap;
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        n = Integer.parseInt(br.readLine());

        visited = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(visited[i], -1);
        }

        houseMap = new char[n][n];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                houseMap[i][j] = str.charAt(j);

                if (houseMap[i][j] == '#') {
                    startNode = new Node(i, j, 0, 0);
                }
            }
        }

        houseMap[startNode.x][startNode.y] = '*';

        pq = new PriorityQueue<>();
        for (int i = 0; i < 4; i++) {
            int nx = startNode.x + dx[i];
            int ny = startNode.y + dy[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= n || houseMap[nx][ny] == '*') continue;
            pq.offer(new Node(startNode.x, startNode.y, i, 0));
        }

        bfs();
        System.out.println(res);

        br.close();
    }

    private static void bfs() {

        visited[startNode.x][startNode.y] = 0;

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();

            if (houseMap[curNode.x][curNode.y] == '#') {
                res = curNode.cnt;
                return;
            }

            int nx = curNode.x + dx[curNode.dir];
            int ny = curNode.y + dy[curNode.dir];

            if (nx < 0 || ny < 0 || nx >= n || ny >= n || houseMap[nx][ny] == '*') continue;
            if (visited[nx][ny] >= curNode.cnt) continue; // pq를 통해서 이미 최소의 거울 개수를 구하고 있기 때문에 현재의 cnt가 visited보다 크거나 같은 부분은 의미가 없다.

            visited[nx][ny] = curNode.cnt;

            if (houseMap[nx][ny] == '!') {
                pq.offer(new Node(nx, ny, (curNode.dir+1)%4, curNode.cnt+1));
                pq.offer(new Node(nx, ny, (curNode.dir+3)%4, curNode.cnt+1));
            }

            pq.offer(new Node(nx, ny, curNode.dir, curNode.cnt));
        }
    }
}
