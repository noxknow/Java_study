import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static List<List<Integer>> arr;
    static boolean[] visited;
    static int n,m;
    static int count = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        visited = new boolean[n+1];

        arr = new ArrayList<>();
        for (int i=0; i<=n; i++) {
			arr.add(new ArrayList<>());
		}
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr.get(a).add(b);
            arr.get(b).add(a);
        }
        System.out.println(bfs(1));
    }

    public static int bfs(int v) {
        Queue<Integer> q = new LinkedList<Integer>();
        q.offer(v);
        visited[v] = true;
        while(!q.isEmpty()) {
            int tmp = q.poll();
            for (Integer nx : arr.get(tmp)) {
                if (visited[nx] == false) {
                    visited[nx] = true;
                    q.offer(nx);
                    count += 1;
                }
            }
        }
        return count;
    }
}

[[], [2, 5], [1, 3, 5], [2], [7], [1, 2, 6], [5], [4]]
