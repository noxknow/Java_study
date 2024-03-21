import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[] times;
    static int[] indegree;
    static int[] res;
    static List<List<Integer>> targets;

    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        n = Integer.parseInt(br.readLine());
        targets = new ArrayList<>();
        indegree = new int[n + 1];
        times = new int[n + 1];
        res = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            targets.add(new ArrayList<>());
        }

        for (int i = 1; i <= n; i++) {
            String[] words = br.readLine().split(" ");
            int idx = 1;
            times[i] = Integer.parseInt(words[0]);

            while (Integer.parseInt(words[idx]) != -1) {
                targets.get(Integer.parseInt(words[idx])).add(i);
                indegree[i]++;
                idx++;
            }
        }

        topologicalSort();

        for (int i = 1; i <= n; i++) {
            System.out.println(res[i]);
        }

        br.close();
    }

    private static void topologicalSort() {
        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i < n+1; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int v = q.poll();
            res[v] += times[v];

            for (int i = 0; i < targets.get(v).size(); i++) {
                int nv = targets.get(v).get(i);

                indegree[nv]--;
                if (indegree[nv] == 0) {
                    q.offer(nv);
                }

                res[nv] = Math.max(res[nv], res[v]);
            }
        }
    }
}
