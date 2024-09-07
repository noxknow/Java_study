import java.util.*;
import java.io.*;

public class Main {

    static int n, number, res;
    static int[] guilty;
    static boolean[] isDead;
    static int[][] R;
    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        n = Integer.parseInt(br.readLine());

        guilty = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            guilty[i] = Integer.parseInt(st.nextToken());
        }

        R = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                R[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        isDead = new boolean[n];
        number = Integer.parseInt(br.readLine());

        int len = guilty.length;

        dfs(len, 0);

        System.out.println(res);

        br.close();
    }

    private static void dfs(int len, int cnt) {

        if (isDead[number] || len == 1) {
            res = Math.max(res, cnt);
            return;
        }

        if (len % 2 == 0) {
            for (int i = 0; i < n; i++) {
                if (!isDead[i] && i != number) {
                    isDead[i] = true;
                    resizeGuilty(0, i);
                    dfs(len - 1, cnt + 1);
                    resizeGuilty(1, i);
                    isDead[i] = false;
                }
            }
        } else {
            int maxGuilty = Integer.MIN_VALUE;
            int idx = 0;

            for (int i = 0; i < n; i++) {
                if (!isDead[i] && maxGuilty < guilty[i]) {
                    maxGuilty = guilty[i];
                    idx = i;
                }
            }

            isDead[idx] = true;
            dfs(len - 1, cnt);
            isDead[idx] = false;
        }
    }

    private static void resizeGuilty(int type, int idx) {

        if (type == 0) {
            for (int i = 0; i < n; i++) {
                if (!isDead[i]) {
                    guilty[i] += R[idx][i];
                }
            }
        } else {
            for (int i = 0; i < n; i++) {
                if (!isDead[i]) {
                    guilty[i] -= R[idx][i];
                }
            }
        }
    }
}
