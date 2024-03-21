import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int r, c;
    static int[][] graph;
    static boolean[] visited = new boolean[26];
		static int[] dx = { -1, 1, 0, 0 };
		static int[] dy = { 0, 0, -1, 1 };
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        graph = new int[r][c];
        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                graph[i][j] = str.charAt(j) - 'A';
            }
        }

        dfs(0,0,0);
        System.out.println(ans);
    }

    public static void dfs(int x, int y, int cnt) {
        if (visited[graph[x][y]]) {
            ans = Math.max(ans, cnt);
            return;
        } else {
            visited[graph[x][y]] = true;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx>=0 && nx<r && ny>=0 && ny<c) {
                    dfs(nx,ny,cnt+1);
                }
            }
            visited[graph[x][y]] = false;
        }
    }
}
