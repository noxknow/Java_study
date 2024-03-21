import java.io.*;
import java.util.*;

public class Main {

    static int n, m, cnt;
    static char[][] directionMap;
    static boolean[][] visited, finished;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        cnt = 0;
        directionMap = new char[n][m];
        visited = new boolean[n][m];
        finished = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            for (int j = 0; j < m; j++) {
                directionMap[i][j] = word.charAt(j);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j]) {
                    dfs(directionMap[i][j], i, j);
                }
            }
        }

        System.out.println(cnt);

        br.close();
    }

    private static void dfs(char dir, int x, int y) {

        visited[x][y] = true;
        int nx = x + dx[dirIndex(dir)];
        int ny = y + dy[dirIndex(dir)];

        if (!visited[nx][ny]) {
            dfs(directionMap[nx][ny], nx, ny);
        } else {
            if (!finished[nx][ny]) cnt++;
        }

        finished[x][y] = true;
    }

    private static int dirIndex(char dir) {
        switch (dir) {
            case 'D': return 1;
            case 'U': return 0;
            case 'L': return 2;
            case 'R': return 3;
            default: return -1; 
        }
    }
}
