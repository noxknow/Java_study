import java.io.*;
import java.util.*;

public class Main {

    static int n, m, cnt;
    static int r, c, d;
    static int[][] board;
    static int[] dx = {-1, 0, 1, 0}; // 0인 경우 북쪽, 1인 경우 동쪽, 2인 경우 남쪽, 3인 경우 서쪽
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new FileReader("input.txt")); 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        cnt = 1;
        board = new int[n][m];

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(r, c, d);

        System.out.println(cnt);
    }

    public static void dfs(int x, int y, int dir) {

        board[x][y] = 2;

        for (int i=0; i<4; i++) {

            dir -= 1; 
            if(dir == -1) dir = 3;

            int nx = x + dx[dir]; 
            int ny = y + dy[dir];

            if(nx >= 0 && nx < n && ny >= 0 && ny < m && board[nx][ny] == 0) {

                cnt++;
                dfs(nx, ny, dir);
                return;
            }
        }

        int d = (dir + 2) % 4; //반대 방향으로 후진.
        int bx = x + dx[d]; //후진
        int by = y + dy[d];
        if (bx >= 0 && bx < n && by >= 0 && by < m && board[bx][by] != 1) {
            dfs(bx, by, dir); //후진할 때 방향을 유지.
        }
    } 
}
