import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int[][] relationship;
    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        relationship = new int[n + 1][n + 1];
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                relationship[i][j] = (int) 1e9;

                if (i == j) {
                    relationship[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            relationship[v1][v2] = relationship[v2][v1] = 1;
        }

        for (int k = 1; k < n + 1; k++) {
            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < n + 1; j++) {
                    relationship[i][j] = Math.min(relationship[i][j], relationship[i][k] + relationship[k][j]);
                }
            }
        }

        int idx = 0;
        int res = (int) 1e9;
        for (int i = 1; i < n + 1; i++) {
            int total = 0;

            for (int j = 1; j < n + 1; j++) {
                total += relationship[i][j];
            }

            if (res > total) {
                res = total;
                idx = i;
            }
        }

        System.out.println(idx);

        br.close();
    }
}
