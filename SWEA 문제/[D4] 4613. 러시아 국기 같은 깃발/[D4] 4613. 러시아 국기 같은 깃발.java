import java.io.*;
import java.util.*;

public class Solution {

    static int n, m, res;
    static char[] colors;
    static char[][] flags;
    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        int t = Integer.parseInt(br.readLine());

        for (int i = 1; i <= t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            StringBuilder sb = new StringBuilder();

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            res = Integer.MAX_VALUE;
            colors = new char[n];
            flags = new char[n][m];

            for (int j = 0; j < n; j++) {
                String words = br.readLine();
                for (int k = 0; k < m; k++) {
                    flags[j][k] = words.charAt(k);
                }
            }

            dfs(0, 'W');

            // 0번째 행과 n-1번째 행 체크
            for (int j = 0; j < m; j++) {
                if (flags[0][j] != 'W') {
                    res++;
                }
                if (flags[n - 1][j] != 'R') {
                    res++;
                }
            }

            sb.append("#").append(i).append(" ").append(res);

            System.out.println(sb);
        }

        br.close();
    }

    public static void dfs(int cnt, char currentColor) {

        if (cnt == n - 2) {
            int maxColorCnt = 0;

            for (int i = 1; i < n - 1; i++) {
                int colorCnt = 0;
                for (int j = 0; j < m; j++) {
                    if (flags[i][j] != colors[i - 1]) {
                        colorCnt++;
                    }
                }

                maxColorCnt += colorCnt;
            }

            res = Math.min(res, maxColorCnt);
            return;
        }

        // return이 필요한 이유 -> return이 없다면 다른 조건문들이 if - else if의 관계가 아닌 각자의 조건문인 if 여러개이기 때문에
        // 이 조건문의 문장을 수행한 후에도 아래의 if문들을 수행하게 된다.
        if (cnt == n - 3 && currentColor == 'W') {
            colors[cnt] = 'B';
            dfs(cnt + 1, 'B');
            return;
        }

        if (currentColor == 'W') {
            colors[cnt] = 'W';
            dfs(cnt + 1, 'W');
            colors[cnt] = 'B';
            dfs(cnt + 1, 'B');
        }
        else if (currentColor == 'B') {
            colors[cnt] = 'B';
            dfs(cnt + 1, 'B');
            colors[cnt] = 'R';
            dfs(cnt + 1, 'R');
        }
        else {
            colors[cnt] = 'R';
            dfs(cnt + 1, 'R');
        }
    }
}
