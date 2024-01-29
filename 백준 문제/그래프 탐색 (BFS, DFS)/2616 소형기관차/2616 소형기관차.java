import java.io.*;
import java.util.*;

public class Main {

    static int n, maxTrain;
    static int[] train, passengerSum;
    static int[][] dp;
    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        n = Integer.parseInt(br.readLine());
        train = new int[n+1];
        passengerSum = new int[n+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            train[i] = Integer.parseInt(st.nextToken());
            passengerSum[i] = passengerSum[i-1] + train[i];
        }

        maxTrain = Integer.parseInt(br.readLine());
        dp = new int[4][n+1];

        for (int i = 1; i < 4; i++) {
            for (int j = i * maxTrain; j <= n; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j - maxTrain] + passengerSum[j] - passengerSum[j - maxTrain]);
            }
        }

        System.out.println(dp[3][n]);
    }
}
