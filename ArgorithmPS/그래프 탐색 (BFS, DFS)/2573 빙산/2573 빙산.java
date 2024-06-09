import java.io.*;
import java.util.*;

public class Main {

    static class Node {

        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n, m, time;
    static int[][] iceberg;
    static Queue<Node> q;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        iceberg = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                iceberg[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        time = 0;
        while (true) {
            int groupNum = countGroup();
            if (groupNum >= 2) {
                System.out.println(time);
                break;
            }
            if (groupNum == 0) {
                System.out.println(0);
                break;
            }
            minusIce();
            time++;
        }

        br.close();
    }

    private static void minusIce() {

        q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (iceberg[i][j] != 0) {
                    q.add(new Node(i, j));
                    visited[i][j] = true;
                }
            }
        }

        while (!q.isEmpty()) {
            Node tmp = q.poll();
            int seaCount = 0;

            for (int i = 0; i < 4; i++) {
                int nx = tmp.x + dx[i];
                int ny = tmp.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                if (!visited[nx][ny] && iceberg[nx][ny] == 0) seaCount++;
            }

            iceberg[tmp.x][tmp.y] = Math.max(0, iceberg[tmp.x][tmp.y] - seaCount);
        }
    }

    private static int countGroup() {
        boolean[][] visited = new boolean[n][m];
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && iceberg[i][j] > 0) {
                    dfs(i, j, visited);
                    count++;

                    if (count >= 2) return count;
                }
            }
        }

        return count;
    }

    private static void dfs(int i, int j, boolean[][] visited) {

        visited[i][j] = true;

        for (int k = 0; k < 4; k++) {
            int nx = i + dx[k];
            int ny = j + dy[k];

            if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

            if (!visited[nx][ny] && iceberg[nx][ny] != 0) {
                dfs(nx, ny, visited);
            }
        }
    }
}
