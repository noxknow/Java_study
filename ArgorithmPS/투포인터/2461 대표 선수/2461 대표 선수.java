import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br;
    static int n, m, res;
    static int[] indexes;
    static int[][] students;
    public static void main(String[] args) throws Exception {

//      br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        res = Integer.MAX_VALUE;

        fillBoard();
        makeIndexArray();
        selectPlayer();

        System.out.println(res);

        br.close();
    }

    private static void fillBoard() throws Exception {

        students = new int[n][m];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                students[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void makeIndexArray() {

        indexes = new int[n];
        for (int i = 0; i < n; i++) {
            Arrays.sort(students[i]);
            indexes[i] = 0;
        }
    }

    private static void selectPlayer() {

        while (true) {

            int minScore = students[0][indexes[0]];
            int maxScore = students[0][indexes[0]];
            int minIdx = 0;

            for (int i = 1; i < n; i++) {
                if (minScore > students[i][indexes[i]]) {
                    minScore = students[i][indexes[i]];
                    minIdx = i;
                }

                if (maxScore < students[i][indexes[i]]) {
                    maxScore = students[i][indexes[i]];
                }
            }

            if (maxScore - minScore < res) {
                res = maxScore - minScore;
            }

            if (++indexes[minIdx] >= m) break;
        }
    }
}
