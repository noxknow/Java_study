import java.util.*;
import java.io.*;

public class Main {

    static int n, m, ans;
    static int[] memory;
    static int[] cost;
    static int[][] dp;
    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        ans = Integer.MAX_VALUE;

        memory = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }

        cost = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[n][10001];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 10001; j++) {
                if (i == 0) {
                    if (j >= cost[i]) dp[i][j] = memory[i];
                } else {
                    if (j >= cost[i]) dp[i][j] = Math.max(dp[i - 1][j - cost[i]] + memory[i], dp[i - 1][j]);
                    else dp[i][j] = dp[i - 1][j];
                }

                if (dp[i][j] >= m) ans = Math.min(ans, j);
            }
        }

        System.out.println(ans);

        br.close();
    }
}
