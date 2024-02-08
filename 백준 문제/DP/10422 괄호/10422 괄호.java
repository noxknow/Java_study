import java.io.*;
import java.util.*;

public class Main {

    static int l;
    static long[] dp;
    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        int t = Integer.parseInt(br.readLine());
        int num = 1;

        dp = new long[5001];
        dp[0] = 1;
        dp[2] = 1;

        for (int i = 2; i < 2501; i++) {
            for (int j = 0; j < i; j++) {
                dp[i * 2] += (dp[j * 2] * dp[(i - 1 - j) * 2]);
                dp[i * 2] %= 1000000007L;
            }
        }

        for (int i = 1; i <= t; i++) {
            l = Integer.parseInt(br.readLine());

            if (l % 2 == 1) {
                System.out.println(0);
                continue;
            }

            System.out.println(dp[l]);
        }

        br.close();
    }
}
