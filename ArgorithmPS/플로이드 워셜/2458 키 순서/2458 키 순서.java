import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int INF = 987654321;
    static int[][] graph;
    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = INF;

                if (i == j) graph[i][j] = 0;
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int p1 = Integer.parseInt(st.nextToken()) - 1;
            int p2 = Integer.parseInt(st.nextToken()) - 1;

            graph[p1][p2] = 1;
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        int res = 0;
        int[] path = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;

                if (graph[i][j] < INF) {
                    path[i] += 1;
                    path[j] += 1;

                    if(path[i] == n - 1) res += 1;
                    if(path[j] == n - 1) res += 1;
                }
            }
        }

        System.out.println(res);

        br.close();
    }
}
