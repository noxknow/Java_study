import java.io.*;
import java.util.*;

public class Solution {

    static int[] arriveTime, dp;
    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        int t = Integer.parseInt(br.readLine());

        for (int i = 1; i <= t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int num = 0;
            int res = 1;
            arriveTime = new int[n];

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arriveTime[j] = Integer.parseInt(st.nextToken());
            }

            timeSort();
            dp = new int[arriveTime[n - 1] + 1];

            for (int idx = 1; idx <= arriveTime[n - 1]; idx++) {
                dp[idx] = dp[idx - 1];

                if (idx % m == 0) dp[idx] += k;
            }

            StringBuilder sb = new StringBuilder("#" + i);
            for (int value : arriveTime) {
                num++;

                if (dp[value] - num < 0) {
                    res = 0;
                    break;
                }
            }

            if (res == 0) {
                sb.append(" ").append("Impossible");
            } else {
                sb.append(" ").append("Possible");
            }

            System.out.println(sb);
        }

        br.close();
    }

    private static void timeSort() {

        for(int index = 1; index < arriveTime.length ; index++){
            int temp = arriveTime[index];
            int prev = index - 1;
            while( (prev >= 0) && (arriveTime[prev] > temp) ) {
                arriveTime[prev+1] = arriveTime[prev];
                prev--;
            }

            arriveTime[prev + 1] = temp;
        }
    }
}
