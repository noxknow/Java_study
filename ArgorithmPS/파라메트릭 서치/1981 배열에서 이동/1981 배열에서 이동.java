import java.util.*;
import java.io.*;

public class Main {

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n, maxNum, minNum, res;
    static boolean[][] visited;
    static int[][] maps;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        n = Integer.parseInt(br.readLine());
        maxNum = Integer.MIN_VALUE;
        minNum = Integer.MAX_VALUE;
        res = Integer.MAX_VALUE;

        maps = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
                maxNum = Math.max(maxNum, maps[i][j]);
                minNum = Math.min(minNum, maps[i][j]);
            }
        }

        binarySearch();

        System.out.println(res);

        br.close();
    }

    private static void binarySearch() {

        int left = 0;
        int right = maxNum - minNum;

        while (left <= right) {

            int mid = (left + right) / 2;
            boolean flag = false;

            for (int i = minNum; i + mid <= maxNum; i++) {
                int s = i;
                int e = i + mid;

                if (maps[0][0] >= s && maps[0][0] <= e) {
                    if (bfs(s, e)) {
                        flag = true;
                        break;
                    }
                }
            }

            if (flag) {
                right = mid - 1;
                res = Math.min(res, mid);
            } else {
                left = mid + 1;
            }
        }
    }

    private static boolean bfs(int s, int e) {

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0));
        visited = new boolean[n][n];
        visited[0][0] = true;

        while (!q.isEmpty()) {

            Node curNode = q.poll();

            if (curNode.x == n - 1 && curNode.y == n - 1) return true;

            for (int i = 0; i < 4; i++) {
                int nx = curNode.x + dx[i];
                int ny = curNode.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (visited[nx][ny]) continue;

                if (maps[nx][ny] >= s && maps[nx][ny] <= e) {
                    q.add(new Node(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }

        return false;
    }
}
