import java.io.*;
import java.util.*;

public class Main {

    static int t, n;
    static int[] cards;
    static int[][] dp;
    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            n = Integer.parseInt(br.readLine());
            cards = new int[n];
            dp = new int[n+1][n+1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                cards[j] = Integer.parseInt(st.nextToken());
            }

            solve(0, n-1, true);
            System.out.println(dp[0][n-1]);
        }
    }

    private static int solve(int left, int right, boolean flag) {

        if (left > right) return 0;
        if (dp[left][right] != 0) return dp[left][right];

        // 근우가 왼쪽 오른쪽 중 큰 값을 고른다면 명우는 작은 값을 고른다.
        if (flag) {
            return dp[left][right] = Math.max(cards[left] + solve(left + 1, right, false), cards[right] + solve(left, right - 1, false));
        } else {
            return dp[left][right] = Math.min(solve(left + 1, right, true), solve(left, right - 1, true));
        }
    }
}
