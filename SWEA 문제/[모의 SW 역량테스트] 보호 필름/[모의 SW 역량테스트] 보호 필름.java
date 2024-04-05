import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Solution {

    static int d, w, k, ans;
    static int[][] dp;
    static int[][] films;
    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            d = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            films = new int[d][w];
            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    films[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            ans = Integer.MAX_VALUE;

            if (!testIssue()) {
                int[][] newFilms = deepCopy(films);
                backTracking(0, 2, 0);

                films = newFilms;
                backTracking(0, 0, 1);

                films = newFilms;
                backTracking(0, 1, 1);

                System.out.println("#" + tc + " " + ans);
            } else {
                System.out.println("#" + tc + " " + 0);
            }
        }

        br.close();
    }

    private static int[][] deepCopy(int[][] original) {
        int[][] copy = new int[original.length][];

        for (int i = 0; i < original.length; i++) {
            copy[i] = Arrays.copyOf(original[i], original[i].length);
        }

        return copy;
    }

    private static boolean testIssue() {

        dp = new int[w][d];

        for (int i = 0; i < w; i++) {
            Arrays.fill(dp[i], 1);
            for (int j = 1; j < d ; j++) {
                if (films[j - 1][i] == films[j][i]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - 1] + 1);
                }
            }

            int maxValue = Arrays.stream(dp[i])
                    .max()
                    .getAsInt();

            if (maxValue < k) {
                return false;
            }
        }

        return true;
    }

    private static void backTracking(int depth, int film, int cnt) {

        if (cnt >= ans) return;

        if (film != 2) {
            for (int i = 0; i < w; i++) {
                films[depth][i] = film;
            }
        }

        if (depth == d - 1) {
            if (testIssue()) {
                ans = Math.min(ans, cnt);
            }

            return;
        }

        backTracking(depth + 1, 2, cnt);

        int[][] newFilms = deepCopy(films);

        backTracking(depth + 1, 0, cnt + 1);

        films = newFilms;
        newFilms = deepCopy(films);

        backTracking(depth + 1, 1, cnt + 1);

        films = newFilms;
    }
}
