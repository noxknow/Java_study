import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt")); 
        String[] st = br.readLine().split(" ");

        int c = Integer.parseInt(st[0]);
        int n = Integer.parseInt(st[1]);

        int[] dp = new int[c+101];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        
        for (int i=0; i<n; i++) {
            st = br.readLine().split(" ");
            int cost = Integer.parseInt(st[0]);
            int people = Integer.parseInt(st[1]);
            for (int j=people; j<c+101; j++) {
                if (dp[j - people] != Integer.MAX_VALUE) dp[j] = Math.min(dp[j], cost + dp[j - people]);
            }
        }

        int result = Integer.MAX_VALUE;
        for(int i=c; i<c+101; i++){
            result = Math.min(result, dp[i]);
        }

        System.out.println(result);

        br.close();
    }
}
