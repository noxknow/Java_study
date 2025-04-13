import java.util.*;
import java.io.*;

public class Main {

    static int n, k;
    static int[] time;
    static int[] indegree;
    static int[] res;
    static List<List<Integer>> graphs;
    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            time = new int[n + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                time[i] = Integer.parseInt(st.nextToken());
            }

            graphs = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                graphs.add(new ArrayList<>());
            }

            indegree = new int[n + 1];
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                graphs.get(from).add(to);
                indegree[to]++;
            }

            res = new int[n + 1];

            topologicalSort();

            int winNumber = Integer.parseInt(br.readLine());

            System.out.println(res[winNumber]);
        }

        br.close();
    }

    private static void topologicalSort() {

        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int v = q.poll();
            res[v] += time[v];

            for (int i = 0; i < graphs.get(v).size(); i++) {
                int nv = graphs.get(v).get(i);

                indegree[nv]--;
                if (indegree[nv] == 0) {
                    q.offer(nv);
                }

                res[nv] = Math.max(res[nv], res[v]);
            }
        }
    }
}
