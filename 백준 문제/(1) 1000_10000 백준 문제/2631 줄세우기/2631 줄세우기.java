import java.io.*;
import java.util.*;

public class Main {

    static int n, ans;
    static int[] numList;
    static int[] dp;
    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new FileReader("input.txt")); 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        numList = new int[n];
        for (int i=0; i<n; i++) {
            numList[i] = Integer.parseInt(br.readLine());
        }

        dp = new int[n];
        ans = 0;
        dp[0] = 1;
        for (int i=1; i<n; i++) {
            dp[i] = 1;
            for (int j=0; j<i; j++) {
                if (numList[i] > numList[j]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }

        System.out.println(n - ans);
    }
