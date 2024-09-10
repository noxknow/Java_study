# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/15.svg" width="30"> [2307] 도로검문

📌 → https://www.acmicpc.net/problem/2307 <br/><br/>

**핵심 로직**

1. 최소한으로 시간을 소비하고 목적지에 도달해야 하기 때문에 PriorityQueue를 활용한다.
    1. 새로운 경로를 발견했을 때 ) pq에 추가할 때 현재까지의 소요 시간 + 새 경로의 소요 시간을 해준다.
    2. 기존 경로보다 빠른 경로를 발견했을 때 ) pq에 추가할 때 새로 계산된 더 짧은 소요 시간으로 갱신한다.
2. 그래프를 탐색할 때마다 각 노드까지의 최소 소요 시간을 time 배열에 입력해둔다.
3. 2번부터 n-1번 도시를 막았다고 가정하고 다익스트라 알고리즘을 실행한다. 이 중 가장 오래 걸리는 시간을 res에 저장한다.
4. 막는곳 없이 1번에서 n번으로 가는 최단 경로를 계산하여 ans에 저장한다.
5. res와 ans의 차이를 출력한다. 만약 경로가 존재하지 않으면 -1을 출력한다. <br/><br/>

```
6 7
1 2 1
1 4 3
3 6 1
4 5 1
2 3 2
3 4 1
5 6 2
```

### 최종 결과 (796 ms)

```java
import java.util.*;
import java.io.*;

public class Main {

    static class Node implements Comparable<Node> {

        int to, time;

        Node(int to, int time) {
            this.to = to;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return this.time - o.time;
        }
    }

    static int n, m, res, ans;
    static int INF = 987654321;
    static int[] time;
    static boolean[] visited;
    static List<List<Node>> graph;
    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        res = Integer.MIN_VALUE;

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {

            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Node(to, t));
            graph.get(to).add(new Node(from, t));
        }

        for (int i = 2; i < n; i++) {
            visited = new boolean[n + 1];
            visited[i] = true;
            dijkstra(1);

            res = Math.max(res, time[n]);
        }

        visited = new boolean[n + 1];
        dijkstra(1);
        ans = time[n];

        if (res == INF) {
            System.out.println(-1);
        } else {
            System.out.println(res - ans);
        }

        br.close();
    }

    private static void dijkstra(int start) {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        visited[start] = true;
        time = new int[n + 1];
        Arrays.fill(time, INF);
        time[start] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            visited[now.to] = true;

            for (Node next : graph.get(now.to)) {

                if (!visited[next.to] && time[next.to] > time[now.to] + next.time) {
                    time[next.to] = time[now.to] + next.time;
                    pq.add(new Node(next.to, time[next.to]));
                }
            }
        }
    }
}
```
