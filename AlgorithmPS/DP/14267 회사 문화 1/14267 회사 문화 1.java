import java.io.*;
import java.util.*;

public class Main {

    public static int n, m;
    public static List<List<Integer>> parent;
    public static int[] dp;
    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new ArrayList<>();
        parent.add(new ArrayList<>());
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            parent.add(new ArrayList<>());
            int employee = Integer.parseInt(st.nextToken());

            if (employee != -1) {
                parent.get(employee).add(i);
            }
        }

        dp = new int[n + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int employee = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            dp[employee] += value;
        }

        dfs(1);

        for (int i = 1; i <= n; i++) {
            System.out.print(dp[i] + " ");
        }

        br.close();
    }

    private static void dfs(int idx) {

        for (int nextIdx : parent.get(idx)) {
            dp[nextIdx] += dp[idx];
            dfs(nextIdx);
        }
    }
}
