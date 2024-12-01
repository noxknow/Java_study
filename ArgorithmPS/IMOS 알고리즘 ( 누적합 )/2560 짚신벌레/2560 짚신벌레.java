import java.util.*;
import java.io.*;

public class Main {

    static int a, b, d, n;
    static int[] dp;
    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            if (i < a) {
                dp[i] = dp[i - 1] % 1000;
            } else if (i < b) {
                dp[i] = (dp[i - 1] + dp[i - a]) % 1000;
            } else {
                dp[i] = (dp[i - 1] + dp[i - a] - dp[i - b] + 1000) % 1000;
            }
        }

        if (n - d >= 0) {
            System.out.println((dp[n] - dp[n - d] + 1000) % 1000);
        } else {
            System.out.println(dp[n] % 1000);
        }

        br.close();
    }
}
