import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static Queue<Node> q;
    static int[][] basicMap;
    static boolean visited[][][];
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    static class Node {

        int x, y, dist;
        boolean broke;

        Node (int x, int y, int dist, boolean broke) {
            this.x = x;
            this.y = y;
            this.dist = dist; 
            this.broke = broke; //벽 부순 여부
        }
    }

    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        basicMap = new int[n+1][m+1];
        visited = new boolean[2][n+1][m+1];

        for (int i=1; i<=n; i++) {
            String[] line = br.readLine().split("");
            for (int j=1; j<=m; j++) {
                basicMap[i][j] = Integer.parseInt(line[j-1]);
            }
        }

        bfs();

        br.close();
    }

    private static void bfs() {

        q = new LinkedList<>();
        q.add(new Node(1, 1, 1, false)); // 시작점 큐에 넣기
        visited[0][1][1] = true; 

        while(!q.isEmpty()) {
            Node now = q.poll();

            if (now.x == n && now.y == m) {
                System.out.println(now.dist);
                return;
            }

            for (int i=0; i<4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                int nDist = now.dist+1;

                if (nx <= 0 || nx > n || ny <= 0 || ny > m) continue; // 맵 범위 벗어나는 경우

                // (1) 벽이 아니면
                // a. 벽을 한번도 안 부수고 진행해온 경우
                // b. 벽을 한번 부수고 진행해온 경우
                if (basicMap[nx][ny] == 0) { 
                    if (!now.broke) { 
                        if (visited[0][nx][ny]) continue; // 방문한 곳이면 패스

                        visited[0][nx][ny] = true; // 벽 안부순 방문처리
                        q.add(new Node(nx, ny, nDist, false));
                    } else { 
                        if (visited[1][nx][ny]) continue; // 방문한 곳이면 패스

                        visited[1][nx][ny] = true; // 벽 부순 방문처리
                        q.add(new Node(nx, ny, nDist, true));
                    }
                } else { 
                // (2) 벽이면
                // a. 지금까지 벽을 한번도 안 부수고 진행해온 경우
                // b. 벽을 한번 부수고 진행해온 경우
                    if (!now.broke) { 
                        visited[1][nx][ny] = true; // 벽 부순 방문처리

                        q.add(new Node(nx, ny, nDist, true)); // 벽 부순거 기록
                    }
                }
            }
        }

        System.out.println(-1);
    }
}
