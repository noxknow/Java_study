import java.util.*;
import java.io.*;

public class Main {

    static final int INF = 123456789;

    static BufferedReader br;
    static int n, m, t;
    static int[][] graphs;
    public static void main(String[] args) throws Exception {

//      br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        fillGraph();
        floydWarshall();
        printResult();

        br.close();
    }

    private static void fillGraph() throws Exception {

        graphs = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (i != j) {
                    graphs[i][j] = INF;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            graphs[u][v] = h;
        }
    }

    private static void floydWarshall() {

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (graphs[i][j] > graphs[i][k] && graphs[i][j] > graphs[k][j]) {
                        graphs[i][j] = Math.max(graphs[i][k], graphs[k][j]);
                    }
                }
            }
        }
    }

    private static void printResult() throws Exception {

        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            System.out.println(graphs[s][e] == INF ? -1 : graphs[s][e]);
        }
    }
}
