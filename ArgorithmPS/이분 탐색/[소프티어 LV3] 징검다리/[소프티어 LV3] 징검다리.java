import java.io.*;
import java.util.*;

public class Main {

    static int[] dp;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] stones = new int[n];
        dp = new int[n + 1];
        int len = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            stones[i] = Integer.parseInt(st.nextToken());
        }

        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (stones[i] > dp[len]) {
                dp[++len] = stones[i];
            } else {
                idx = binarySearch(0, len, stones[i]);
                dp[idx] = stones[i];
            }
        }

        System.out.println(len);

        br.close();
    }

    private static int binarySearch(int left, int right, int target) {
        int mid = 0;
        
        while (left < right) {
            mid = (left + right) / 2;

            if (dp[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
    }
}
