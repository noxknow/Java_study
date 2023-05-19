import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Main {
    public static int n, m;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static int[][] board;
    public static int[][] test_board;
    public static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        makeWall(0);

        System.out.println(max);
    }

    public static void makeWall(int cnt) {
        if (cnt == 3) {
            bfs();
            return;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 0) {
                    board[i][j] = 1;
                    makeWall(cnt+1);
                    board[i][j] = 0;
                }
            }
        }
    }

    public static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        test_board = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                test_board[i][j] = board[i][j];
                if (test_board[i][j] == 2) {
                    q.add(new int[]{i, j});
                }
            }
        }
        // for (int i = 0; i < n; i++) {
        //     test_board[i] = Arrays.copyOf(board[i], m);
        // }
        while (!q.isEmpty()) {
            int[] tmp = q.poll();
            int x = tmp[0];
            int y = tmp[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && test_board[nx][ny] == 0) {
                    q.add(new int[]{nx, ny});
                    test_board[nx][ny] = 2;
                }
            }
        }
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (test_board[i][j] == 0) {
                    cnt++;
                }
            }
        }
        max = Math.max(max, cnt);
    }
}
