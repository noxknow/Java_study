import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new FileReader("input.txt")); 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 

        int t = Integer.parseInt(br.readLine());
        for (int i=0; i<t; i++) {

            int n = Integer.parseInt(br.readLine());
            int[] coins = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int money = Integer.parseInt(br.readLine());
            int[] dp = new int[money+1];
            dp[0] = 1;

            for (int coin : coins) {
                for (int j=1; j<money+1; j++) {
                    if (j-coin >= 0) {
                        dp[j] += dp[j-coin];
                    }
                }
            }

            System.out.println(dp[money]);
        }
    }
}
