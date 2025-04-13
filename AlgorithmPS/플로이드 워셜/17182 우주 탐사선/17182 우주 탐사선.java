import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br;
    static int n, k, res;
    static int[][] times;
    static boolean[] visited;
    public static void main(String[] args) throws Exception {

//      br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        res = Integer.MAX_VALUE;

        fillGraph();
        floydWarshall();

        visited = new boolean[n];
        visited[k] = true;
        dfs(0, k, 0);

        System.out.println(res);

        br.close();
    }

    private static void fillGraph() throws Exception {

        times = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                times[i][j] = Integer.parseInt(st1.nextToken());
            }
        }
    }

    private static void floydWarshall() {

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j) continue;
                    times[i][j] = Math.min(times[i][j], times[i][k] + times[k][j]);
                }
            }
        }
    }

    private static void dfs(int depth, int start, int totalTime) {

        if (depth == n - 1) {
            res = Math.min(res, totalTime);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(depth + 1, i, totalTime + times[start][i]);
                visited[i] = false;
            }
        }
    }
}
