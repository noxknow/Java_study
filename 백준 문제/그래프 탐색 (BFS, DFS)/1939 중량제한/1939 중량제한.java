import java.io.*;
import java.util.*;

public class Main {

    static int n, m, maxWeight, left, right;
    static boolean[] visited;
    static List<List<Node>> islandBridge;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static class Node {

        int v, w;

        Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        maxWeight = 0;
        islandBridge = new ArrayList<>();

        for (int i = 0; i <= n; i++) { // 정점의 시작이 1부터 이기 때문에 0 인덱스는 채우지 않고 1 ~ n
            islandBridge.add(new ArrayList<Node>());
        }
        

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            islandBridge.get(v1).add(new Node(v2, w));
            islandBridge.get(v2).add(new Node(v1, w));

            maxWeight = Math.max(maxWeight, w);
        }

        right = maxWeight;

        st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        while (left <= right) {

            int mid = (left + right) / 2;
            visited = new boolean[n+1];

            if (bfs(start, end, mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(right);

        br.close();
    }

    private static boolean bfs(int start, int end, int mid) {

        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int startPoint = q.poll();

            if (startPoint == end) {
                return true;
            }

            for (Node now : islandBridge.get(startPoint)) {
                if (!visited[now.v] && mid <= now.w) { // mid는 최대 중량 기준
                    visited[now.v] = true;
                    q.add(now.v);
                }
            }
        }

        return false;
    }
}
