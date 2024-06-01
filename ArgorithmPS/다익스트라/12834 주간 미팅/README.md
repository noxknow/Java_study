# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30"> [12834] 주간 미팅

📌 → https://www.acmicpc.net/problem/12834 <br/><br/>

**핵심 로직**

1. 주어진 입력값을 변수 선언을 통해 받아준다.
2. 출발 노드와 도착 노드, 거리값을 대입해준다.
3. 팀원의 위치마다 다익스트라 함수가 반복되도록 하고 반복하는 동안 거리 배열과 방문 배열을 초기화 시켜준다. 
4. 다익스트라 함수 내부에선 현재 위치한 노드의 인접 노드 중 방문하지 않은 노드를 구별하고, 방문하지 않은 노드 중 거리가 가장 짧은 노드를 선택한다. 그 노드를 방문 처리한다.
5. 해당 노드를 거쳐 다른 노드로 넘어가는 간선 비용(가중치)을 계산해 '최단 거리 테이블'을 업데이트한다.
6. 다익스트라 함수가 끝나면 KIST와 씨알푸드까지의 거리를 계산해서 res에 추가하고 다른 팀원의 위치에 대해 3 ~ 6의 과정을 반복한다. <br/><br/>

```
2 5 10
3 5
2 4
3 2 91
1 3 44
5 3 93
1 4 1
4 5 53
4 2 23
5 1 60
2 1 63
3 4 38
5 2 17
```

### 다익스트라 (484 ms)

```java
import java.io.*;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {
        int to, dist;

        Node(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }

    static int n, v, e, a, b, res;
    static int INF = 987654321;
    static int[] positions;
    static int[] dist;
    static boolean[] visited;
    static List<List<Node>> graph;
    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        positions = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            positions[i] = Integer.parseInt(st.nextToken());
        }

        graph = new ArrayList<>();
        for (int i = 0; i < e; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Node(to, dist));
            graph.get(to).add(new Node(from, dist));
        }

        for (int position : positions) {
            dijkstra(position);

            dist[a] = dist[a] == INF ? -1 : dist[a];
            dist[b] = dist[b] == INF ? -1 : dist[b];
            res += dist[a] + dist[b] ;
        }

        System.out.println(res);

        br.close();
    }

    private static void dijkstra(int start) {

        dist = new int[v + 1];
        visited = new boolean[v + 1];
        Arrays.fill(dist, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            visited[now.to] = true;

            for (Node next : graph.get(now.to)) {

                if (!visited[next.to] && dist[next.to] > dist[now.to] + next.dist) {
                    dist[next.to] = dist[now.to] + next.dist;
                    pq.add(new Node(next.to, dist[next.to]));
                }
            }

        }
    }
}
```
