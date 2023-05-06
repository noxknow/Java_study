import java.util.*;
 
public class Main {
    
    static Scanner scan = new Scanner(System.in);
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    
    public static void main(String[] args) { 
        int m = scan.nextInt();
        int n = scan.nextInt();
        
        int[][] node = new int[m][n];
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                node[i][j] = scan.nextInt();
                dp[i][j] = -1;
            }
        }
        
        int result = dfs(node, dp, 0, 0);
        System.out.println(result);
    }
    
    public static int dfs(int[][] node, int[][] dp, int x, int y) {
        if(x == node.length - 1 && y == node[0].length - 1) {
            return 1;
        }
        
        if(dp[x][y] == -1) {
            dp[x][y] = 0;
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(nx >= 0 && ny >= 0 && nx < node.length && ny < node[0].length) {
                    if(node[nx][ny] < node[x][y]) {
                        dp[x][y] += dfs(node, dp, nx, ny);
                    }
                }
            }
        }
        return dp[x][y];
    }
 }
