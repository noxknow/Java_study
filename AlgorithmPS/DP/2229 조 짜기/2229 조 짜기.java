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
        numList = new int[n+1];

        dp = new int[n+1];

        String[] words = br.readLine().split(" ");
        for (int i=1; i<n+1; i++) {
            int max = 0, min = 10001; // max와 min을 계속 초기화
            numList[i] = Integer.parseInt(words[i-1]); 

            for (int j=i; j>0; j--) {
                max = Math.max(max, numList[j]); // i=3 즉, 7이라고 가정하면 j=3 일 때 (7,7) j=2 (7,5) j=1 (7,2) 가 된다.
                min = Math.min(min, numList[j]);
                dp[i] = Math.max(dp[i], max - min + dp[j - 1]);
            }
        }

       System.out.println(dp[n]);
    }
}
