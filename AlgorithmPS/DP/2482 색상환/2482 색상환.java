import java.io.*;
import java.util.*;

public class Main {

    private static final int MOD = 1_000_000_003;

    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt")); 

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        int[][] dp = getDp(n);

        if (k == 1) System.out.println(n);
        else System.out.println((dp[n-3][k-1] + dp[n-1][k]) % MOD);
    }

    public static int[][] getDp(int n) {
        int[][] dp = new int[n+1][n+1];
        dp[1][0] = 1;
        dp[1][1] = 1;

        for (int i=2; i<=n; i++) {
            for (int j=1; j<=n; j++) {
                if (j == 1) dp[i][j] = i;
                else if (i > j) {
                    dp[i][j] = (dp[i-2][j-1] + dp[i-1][j]) % MOD;
                }
            }
        }

        return dp;
    }
}
