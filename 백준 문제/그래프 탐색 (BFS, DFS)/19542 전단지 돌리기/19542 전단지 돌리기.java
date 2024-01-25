import java.io.*;
import java.util.*;

public class Main {

    static int n, s, d, ans;
    static List<List<Integer>> edgeMap;
    static boolean[] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        ans = 0;
        edgeMap = new ArrayList<>();
        visited = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {
            edgeMap.add(new ArrayList<>());
        }

        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            edgeMap.get(v1).add(v2);
            edgeMap.get(v2).add(v1);
        }

        dfs(s);

        System.out.println(Math.max(0, (ans - 1) * 2));

        br.close();
    }

    private static int dfs(int v) {

        int max = 0;
        visited[v] = true;

        for (int next : edgeMap.get(v)) {
            if (visited[next]) continue;
            visited[next] = true;

            max = Math.max(max, dfs(next));
        }

        if (max >= d) {
            ans++;
        }

        return max + 1;
    }
}
