import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int[] numbers, dp;
    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        n = Integer.parseInt(br.readLine());
        dp = new int[n + 1];
        numbers = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = -100000001;
        int len = 0;
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (numbers[i] > dp[len]) {
                dp[++len] = numbers[i];
            } else {
                idx = binarySearch(0, len, numbers[i]);
                dp[idx] = numbers[i];
            }
        }

        System.out.println(n - len);

        br.close();
    }

    private static int binarySearch(int left, int right, int target) {

        while (left < right) {

            int mid = (left + right) / 2;

            if (dp[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return right;
    }
}
