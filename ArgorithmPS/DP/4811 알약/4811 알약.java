import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static long[][] dp;
    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        dp = new long[31][31];

        for (int i = 0; i <= 30; i++) {
            for (int j = 0; j <= 30; j++) {
                if (j > i) continue;
                else if (j == 0) dp[i][j] = 1;
                else dp[i][j] = dp[i][j-1] + dp[i-1][j];
            }
        }

        boolean checkCount = true;

        while (checkCount) {
            n = Integer.parseInt(br.readLine());

            if (n == 0) {
                checkCount = false;
                return;
            }

            System.out.println(dp[n][n]);
        }

        br.close();
    }
}
