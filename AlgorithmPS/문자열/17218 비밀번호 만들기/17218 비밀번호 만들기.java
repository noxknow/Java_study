import java.io.*;
import java.util.*;

public class Main {

    static int[][] dp;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str1 = br.readLine();
        String str2 = br.readLine();

        LCS(str1, str2);
        getLCSToString(str1, str1.length(), str2.length());

        System.out.println(sb);

        br.close();
	    }
	
	  private static void LCS(String str1, String str2) {
	
	      int len1 = str1.length();
	      int len2 = str2.length();
	      dp = new int[len1 + 1][len2 + 1];
	
	      for (int i = 1; i <= len1; i++) {
	          for (int j = 1; j <= len2; j++) {
	              if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
	                  dp[i][j] = dp[i - 1][j - 1] + 1;
	              } else {
	                  dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
	              }
	          }
	      }
	  }

    private static void getLCSToString(String str, int i, int j) {
        Stack<Character> s = new Stack<>();

        while (i > 0 && j > 0) {

            if (i == 0 || j == 0) break;

            if (dp[i][j] == dp[i - 1][j]) {
                i--;
            } else if (dp[i][j] == dp[i][j - 1]) {
                j--;
            } else {
                s.push(str.charAt(i - 1));
                i--;
                j--;
            }
        }

        while (!s.isEmpty()) {
            sb.append(s.pop());
        }
    }
}
