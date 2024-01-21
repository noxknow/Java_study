import java.io.*;
import java.util.*;

public class Main {

    static int n, m, cheeseNum, time;
    static int[][] cheeseMap, air;
    static Queue<Node> q;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    static class Node {

        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        time = 0;
        cheeseNum = 0;
        cheeseMap = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                cheeseMap[i][j] = Integer.parseInt(st.nextToken());

                if (cheeseMap[i][j] == 1) {
                    cheeseNum += 1;
                }
            }
        }

        while (cheeseNum != 0) {
            bfs();
        }

        System.out.println(time);

        br.close();
    }

    private static void bfs() {
        air = new int[n][m];

        q = new LinkedList<>();
        q.add(new Node(0, 0));
        air[0][0] = -1;

        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                if (cheeseMap[nx][ny] == 1) { // 2변 이상이 외부 공기와 접촉했는지 확인
                    air[nx][ny]++;
                }
                if (cheeseMap[nx][ny] == 0 && air[nx][ny] == 0) { // 외부 공기 구하는 로직
                    air[nx][ny] = -1;
                    q.add(new Node(nx, ny));
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (air[i][j] >= 2) {
                    cheeseNum--;
                    cheeseMap[i][j] = 0;
                }
            }
        }

        time++;
    }
}
