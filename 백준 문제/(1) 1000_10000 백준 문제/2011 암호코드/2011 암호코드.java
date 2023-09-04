import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new FileReader("input.txt")); 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String words = br.readLine();

        int[] dp = new int[words.length()+1];
        dp[0] = 1;
        
        for (int i=0; i<words.length(); i++) {

            int num = words.charAt(i) - '0';
            if (num != 0) { 
                dp[i+1] += dp[i];
                dp[i+1] %= 1000000; 
            }

            if (i == 0) { continue; }

            int prev = words.charAt(i-1) - '0';

            prev = prev*10 + num;
            if (prev >= 10 && prev <= 26) { 
                dp[i+1] += dp[i-1];
                dp[i+1] %= 1000000;
            }
        } 

        System.out.println(dp[dp.length-1]);
    }
}
