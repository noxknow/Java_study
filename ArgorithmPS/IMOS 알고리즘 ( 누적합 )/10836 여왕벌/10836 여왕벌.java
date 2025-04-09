import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br;
    static int m, n;
    static int[][] graphs;
    public static void main(String[] args) throws Exception {

//      br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        graphs = new int[m][m];

        fillGraph();

        for (int i = 0; i < n; i++) {
            StringTokenizer inputs = new StringTokenizer(br.readLine());
            int zero = Integer.parseInt(inputs.nextToken());
            int one = Integer.parseInt(inputs.nextToken());
            int two = Integer.parseInt(inputs.nextToken());

            increaseGraph(zero, one, two);
        }

        increaseOther();
        printResult();

        br.close();
    }

    private static void fillGraph() {

        for (int i = 0; i < m; i++) {
            Arrays.fill(graphs[i], 1);
        }
    }

    private static void increaseGraph(int zero, int one, int two) {

        for (int i = m - 1; i > 0; i--) {
            if (zero != 0) {
                zero--;
            } else if (one != 0) {
                graphs[i][0]++;
                one--;
            } else if (two != 0) {
                graphs[i][0] += 2;
                two--;
            }
        }

        for (int i = 0; i < m; i++) {
            if (zero != 0) {
                zero--;
            } else if (one != 0) {
                graphs[0][i]++;
                one--;
            } else if (two != 0) {
                graphs[0][i] += 2;
                two--;
            }
        }
    }

    private static void increaseOther() {

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < m; j++) {
                graphs[i][j] = Math.max(graphs[i - 1][j], Math.max(graphs[i - 1][j - 1], graphs[i][j - 1]));
            }
        }
    }

    private static void printResult() {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(graphs[i][j]);
                if (j != m - 1) sb.append(" ");
            }

            sb.append("\n");
        }

        System.out.print(sb);
    }
}
