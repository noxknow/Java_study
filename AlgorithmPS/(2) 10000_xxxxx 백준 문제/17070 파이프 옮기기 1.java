import java.io.*;
import java.util.*;

public class Main {

    static int n, ans;
    static int[][] board;
    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new FileReader("input.txt")); 테스트용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        ans = 0;
        board = new int[n][n];

        for (int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 1, 0);

        System.out.println(ans);
    }
    
    // State = 0 가로 , 1 세로, 2 대각선
    public static void dfs(int x, int y, int state) {

        if (x == n-1 && y == n-1) {
            ans++;
            return;
        }

        if (state == 0) { 
            if (y+1 <n && board[x][y+1] == 0) {
                dfs(x, y+1, 0);
            }
        } else if (state == 1) {
            if (x+1 <n && board[x+1][y] == 0) {
                dfs(x+1, y, 1);
            }
        } else if (state == 2) {
            if (y+1 <n && board[x][y+1] == 0) {
                dfs(x, y+1, 0);
            }
            if (x+1 <n && board[x+1][y] == 0) {
                dfs(x+1, y, 1);
            }
        }

        if (x+1 < n && y+1 <n && board[x][y+1] == 0 && board[x+1][y] == 0 && board[x+1][y+1] == 0) {
            dfs(x+1, y+1, 2);
        }
    }
}

