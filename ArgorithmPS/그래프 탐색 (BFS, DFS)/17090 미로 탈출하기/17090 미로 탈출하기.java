import java.io.*;
import java.util.*;

public class Main {

    static int n, m, cnt;
    static char[][] maze;
    static boolean[][] visited, depth;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        maze = new char[n][m];
        visited = new boolean[n][m];
        depth = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            for (int j = 0; j < m; j++) {
                maze[i][j] = word.charAt(j);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                backTracking(maze[i][j], i, j);
            }
        }

        System.out.println(cnt);

        br.close();
    }

    private static boolean backTracking(char dir, int x, int y) {

        visited[x][y] = true;

        int nx = x + dx[dirIndex(dir)];
        int ny = y + dy[dirIndex(dir)];

        if ((nx < 0 || nx >= n || ny < 0 || ny >= m) || depth[nx][ny]) {
            depth[x][y] = true;
            cnt++;
            return true;
        }

        if (visited[nx][ny]) {
            return false;
        }

        return depth[x][y] = backTracking(maze[nx][ny], nx, ny);
    }

    private static int dirIndex(char dir) {

        if (dir == 'D') {
            return 1;
        } else if (dir == 'U') {
            return 0;
        } else if (dir == 'L') {
            return 2;
        } else {
            return 3;
        }
    }
}
