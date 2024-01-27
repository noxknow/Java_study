import java.io.*;
import java.util.*;

public class Main {

    static int n, m, k, cnt;
    static String targetWord;
    static int[][][] dp;
    static char[][] wordMap;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        cnt = 0;
        wordMap = new char[n][m];

        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            for (int j = 0; j < m; j++) {
                wordMap[i][j] = word.charAt(j);
            }
        }

        targetWord = br.readLine();

        dp = new int[n][m][targetWord.length()]; // 좌표 (i, j)에 영단어( target )의 k 번째 인덱스로 방문했을 시, 그 뒤로 만들어지는 경로들의 수

        for (int[][] outerArr : dp) {
            for (int[] innerArr : outerArr) {
                Arrays.fill(innerArr, -1); // 경로의 수 0과 초기화 값을 구분하기 위해 -1
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (wordMap[i][j] == targetWord.charAt(0)) {
                    cnt += dfs(i, j, 0);
                }
            }
        }

        System.out.println(cnt);

        br.close();
    }

    private static int dfs(int x, int y, int idx) {

        if (dp[x][y][idx] != -1) return dp[x][y][idx];
        if (idx == targetWord.length() - 1) return dp[x][y][idx] = 1; // 타겟 문자열이 만들어진 경우 마지막 경로는 1이 된다.

        dp[x][y][idx] = 0; // 뒤에 올 경로의 경우가 0이더라도 초기화를 -1로 했기 때문에 0을 넣어줘야 한다.

        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= k; j++) {
                int nx = x + dx[i] * j;
                int ny = y + dy[i] * j;

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                if (wordMap[nx][ny] == targetWord.charAt(idx + 1)) {
                    dp[x][y][idx] += dfs(nx, ny, idx + 1);
                }
            }
        }

        return dp[x][y][idx];
    }
}
