import java.io.*;
import java.util.*;

public class Main {

    static int n, m, x, y, k;
    static int[][] board;
    static int[] dice = new int[6];
    static int[] dx = {0, 0, -1, 1}; // 동 서 북 남
	  static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new FileReader("input.txt")); 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        board = new int[n][m];

        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i=0; i<k; i++) {
            int d = Integer.parseInt(st.nextToken());
			      move(d);
        }
    }

    public static void move(int d) {

        int nx = x + dx[d-1];
        int ny = y + dy[d-1];

        if (nx <0 || ny < 0 || nx >= n || ny >= m) { return; }

        roll(nx, ny, d);
        x = nx; y = ny;
    }

    public static void roll(int x, int y, int d) {

        int tmp = dice[5];
        if (d == 1) { // 동
            dice[5] = dice[2];
            dice[2] = dice[0];
            dice[0] = dice[3];
            dice[3] = tmp;
        } else if (d == 2) { // 서
            dice[5] = dice[3];
            dice[3] = dice[0];
            dice[0] = dice[2];
            dice[2] = tmp;
        } else if (d == 3) { // 북
            dice[5] = dice[1];
            dice[1] = dice[0];
            dice[0] = dice[4];
            dice[4] = tmp;
        } else { // 남
            dice[5] = dice[4];
            dice[4] = dice[0];
            dice[0] = dice[1];
            dice[1] = tmp;
        }

        if (board[x][y] == 0) {
    			board[x][y] = dice[5];
    		} else {
    			dice[5] = board[x][y];
    			board[x][y] = 0;
    		}
        
        System.out.println(dice[0]);
    }
}
