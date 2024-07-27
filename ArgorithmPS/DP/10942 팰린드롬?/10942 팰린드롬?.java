import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static int[] palindromes;
    static int[][] dp;
    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        n = Integer.parseInt(br.readLine());
        palindromes = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            palindromes[i] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;

            boolean flag = checkPalindrome(s, e) == 1;

            if (flag) {
                sb.append("1\n");
            } else {
                sb.append("0\n");
            }
        }

        System.out.println(sb);

        br.close();
    }

    private static int checkPalindrome(int s, int e) {

        if (s >= e) return 1;

        if (dp[s][e] != -1) return dp[s][e];

        if (palindromes[s] == palindromes[e]) return dp[s][e] = checkPalindrome(s + 1, e - 1);

        return 0;
    }
}
