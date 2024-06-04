import java.io.*;
import java.util.*;

public class Main {

    static int n, m, k;
    static int res = Integer.MAX_VALUE;
    static int[] arr;
    static boolean[] visited;
    static int[][] arrayMap;
    static int[][] rotation;
    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arrayMap = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arrayMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        rotation = new int[k][3];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());

            rotation[i][0] = Integer.parseInt(st.nextToken()) - 1;
            rotation[i][1] = Integer.parseInt(st.nextToken()) - 1;
            rotation[i][2] = Integer.parseInt(st.nextToken());
        }

        arr = new int[k];
        visited = new boolean[k];
        permutation(0);

        System.out.println(res);

        br.close();
    }

    private static void permutation(int idx) {
        if (idx == k) {
            rotate();
            return;
        }

        for (int i = 0; i < k; i++) {
            if (!visited[i]) {
                visited[i] = true;
                arr[idx] = i; // 회전 연산의 순서를 위한 배열
                permutation(idx + 1);
                visited[i] = false;
            }
        }
    }

    private static void rotate() {
        int[][] tmp = copyMap();

        for (int i = 0; i < k; i++) {
            int r = rotation[arr[i]][0];
            int c = rotation[arr[i]][1];
            int S = rotation[arr[i]][2];

            for (int s = 1; s <= S; s++) { // 내부에 있는 사각형의 회전
                // 위쪽
                int upTmp = tmp[r - s][c + s];
                for (int y = c + s; y > c - s; y--) {
                    tmp[r - s][y] = tmp[r - s][y - 1];
                }

                // 오른쪽
                int rightTmp = tmp[r + s][c + s];
                for (int x = r + s; x > r - s; x--) {
                    tmp[x][c + s] = tmp[x - 1][c + s];
                }
                tmp[r - s + 1][c + s] = upTmp;

                //아래
                int leftTmp = tmp[r + s][c - s];
                for (int y = c - s; y < c + s; y++) {
                    tmp[r + s][y] = tmp[r + s][y + 1];
                }
                tmp[r + s][c + s - 1] = rightTmp;

                //왼쪽
                for (int x = r - s; x < r + s; x++) {
                    tmp[x][c - s] = tmp[x + 1][c - s];
                }
                tmp[r + s - 1][c - s] = leftTmp;
            }
        }

        getResult(tmp);
    }

    private static int[][] copyMap() {
        int[][] tmp = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                tmp[i][j] = arrayMap[i][j];
            }
        }
        
        return tmp;
    }

    private static void getResult(int[][] tmp) {

        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < m; j++) {
                sum += tmp[i][j];
            }

            res = Math.min(res, sum);
        }
    }
}
