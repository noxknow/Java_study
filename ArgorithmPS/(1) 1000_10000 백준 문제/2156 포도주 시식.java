import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n+1];
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        int[] dp = new int[n+1];
        dp[0] = arr[0];

        for(int j=1; j<n; j++) {
            if(j == 1) {
                dp[1] = arr[0] + arr[1];
                continue;
            } else if (j == 2) {
                dp[2] = Math.max(dp[1], Math.max(arr[0] + arr[2], arr[2] + arr[1]));
                continue;
            }
            dp[j] = Math.max(dp[j-1], Math.max(dp[j-2]+arr[j], dp[j-3]+arr[j-1]+arr[j]));
        }

        System.out.println(dp[n-1]);
    }
}
