import java.io.*;
import java.util.*;
 
public class Solution {
 
    static int t;
    static int[][] ladders;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1};
    static int[] dy = {1, -1, 0};
    public static void main(String[] args) throws IOException {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        for (int i = 1; i <= 10; i++) {
            t = Integer.parseInt(br.readLine());
            int res = 0;
            ladders = new int[100][100];
            visited = new boolean[100][100];
 
            for (int j = 0; j < 100; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 100; k++) {
                    ladders[j][k] = Integer.parseInt(st.nextToken());
                }
            }
 
            for (int j = 0; j < 100; j++) {
                if (ladders[99][j] == 2) {
                    findEscape(99, j);
                }
            }
        }
    }
 
    private static void findEscape(int x, int y) {
 
        if (x == 0 && ladders[x][y] == 1) {
            System.out.println("#" + t + " " + y);
            return;
        }
 
        visited[x][y] = true;
 
        for (int i = 0; i < 3; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
 
            if (nx >= 0 && nx < 100 && ny >= 0 && ny < 100 && ladders[nx][ny] == 1 && !visited[nx][ny]) {
                findEscape(nx, ny);
                break;
            }
        }
    }
}
