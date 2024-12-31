import java.util.*;
import java.io.*;

public class Main {

    static class Node{

        int x, y;

        Node (int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static int n, m, k, minStrength, maxStrength;
    static int[][] minerals;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        minStrength = Integer.MAX_VALUE;
        maxStrength = Integer.MIN_VALUE;

        minerals = new int[n][m];
        for (int i = 0; i < n; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                minerals[i][j] = Integer.parseInt(st1.nextToken());
                maxStrength = Math.max(maxStrength, minerals[i][j]);
                minStrength = Math.min(minStrength, minerals[i][j]);
            }
        }

        int res = binarySearch();

        System.out.println(res);

        br.close();
    }

    private static int binarySearch() {

        int left = minStrength;
        int right = maxStrength;
        while (left <= right) {

            int mid = (left + right) / 2;

            if (checkMinerals(mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private static boolean checkMinerals(int target) {

        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            if (minerals[i][0] <= target) {
                q.offer(new Node(i, 0));
                visited[i][0] = true;
            }
            if (minerals[i][m - 1] <= target) {
                q.offer(new Node(i, m - 1));
                visited[i][m - 1] = true;
            }
        }

        for (int i = 1; i < m - 1; i++) {
            if (minerals[0][i] <= target) {
                q.offer(new Node(0, i));
                visited[0][i] = true;
            }
        }

        int cnt = q.size();

        while (!q.isEmpty()) {

            Node curNode = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = curNode.x + dx[i];
                int ny = curNode.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (visited[nx][ny]) continue;

                if (minerals[nx][ny] <= target) {
                    q.offer(new Node(nx, ny));
                    visited[nx][ny] = true;
                    cnt++;
                }
            }
        }

        return cnt >= k;
    }
}
