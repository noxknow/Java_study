import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new FileReader("input.txt")); 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] board = new int[n][3];
        int[][] maxDp = new int[n][3];
        int[][] minDp = new int[n][3];

        for (int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
      			int b = Integer.parseInt(st.nextToken());
      			int c = Integer.parseInt(st.nextToken());
      			
      			board[i][0] = a;
      			board[i][1] = b;
      			board[i][2] = c;
        }

        maxDp[0][0] = board[0][0];
    		maxDp[0][1] = board[0][1];
    		maxDp[0][2] = board[0][2];
    		
    		minDp[0][0] = board[0][0];
    		minDp[0][1] = board[0][1];
    		minDp[0][2] = board[0][2];

        for (int i=1; i<n; i++) {

            maxDp[i][0] = Math.max(maxDp[i-1][0], maxDp[i-1][1]) + board[i][0];
            maxDp[i][1] = Math.max(maxDp[i-1][0], Math.max(maxDp[i-1][1], maxDp[i-1][2])) + board[i][1];
            maxDp[i][2] = Math.max(maxDp[i-1][1], maxDp[i-1][2]) + board[i][2];

            minDp[i][0] = Math.min(minDp[i-1][0], minDp[i-1][1]) + board[i][0];
      			minDp[i][1] = Math.min(Math.min(minDp[i-1][0], minDp[i-1][1]), minDp[i-1][2]) + board[i][1];
      			minDp[i][2] = Math.min(minDp[i-1][1], minDp[i-1][2]) + board[i][2];
        }

        int maxResult = Math.max(Math.max(maxDp[n-1][0], maxDp[n-1][1]), maxDp[n-1][2]);
    		int minResult = Math.min(Math.min(minDp[n-1][0], minDp[n-1][1]), minDp[n-1][2]);

        System.out.println(maxResult + " " + minResult);
    }
}

