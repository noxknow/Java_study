import java.io.*;
import java.util.*;

public class Main {

    static int n, groupNum;
    static boolean[][] check;
    static int[][] bridgeMap;
    static int minVal = Integer.MAX_VALUE;
    static Queue<Node> q;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static class Node {

        int x, y, dist;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        n = Integer.parseInt(br.readLine());
        bridgeMap = new int[n + 1][n + 1];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                bridgeMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        check = new boolean[n + 1][n + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!check[i][j] && bridgeMap[i][j] != 0) {
                    check[i][j] = true;
                    bridgeMap[i][j] += groupNum;
                    makeIslandGroup(i, j);
                    groupNum++;
                }
            }
        }

        check = new boolean[n + 1][n + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!check[i][j] && bridgeMap[i][j] != 0) {
                    check[i][j] = true;
                    countBridge(i, j, bridgeMap[i][j]);
                    check = new boolean[n + 1][n + 1];
                }
            }
        }

        System.out.print(minVal == Integer.MAX_VALUE ? "0" : minVal);
        
        br.close();
    }

    private static void makeIslandGroup(int x, int y) {

        q = new LinkedList<>();
        q.add(new Node(x, y));

        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx<0 || nx>n-1 || ny <0 || ny>n-1) continue;
                if(check[nx][ny]) continue;

                if (bridgeMap[nx][ny] != 0) {
                    check[nx][ny] = true;
                    bridgeMap[nx][ny] += groupNum;
                    q.add(new Node(nx, ny));
                }
            }
        }
    }

    private static void countBridge(int x, int y, int groupNum) {

        Queue<Node> bridgeQueue = new LinkedList<>();
        bridgeQueue.add(new Node(x, y, 0));

        while (!bridgeQueue.isEmpty()) {
            Node now = bridgeQueue.poll();

            if (now.dist >= minVal) continue;

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx<0 || nx>n-1 || ny <0 || ny>n-1) continue;
                if(check[nx][ny]) continue;
                if(bridgeMap[nx][ny] == groupNum) continue;

                if (bridgeMap[nx][ny] == 0) {
                    check[nx][ny] = true;
                    bridgeQueue.add(new Node(nx, ny, now.dist + 1));
                } else {
                    minVal = Math.min(now.dist, minVal);
                }
            }
        }
    }
}
