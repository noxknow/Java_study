# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/13.svg" width="30"> [4386] 별자리 만들기

📌 → https://www.acmicpc.net/problem/4386 <br/><br/>

**핵심 로직**

- `fillGraph()` 메서드를 통해 각 정점의 좌표 정보를 입력받고, `Graph` 객체 배열에 저장한다.
- `connectNodes()` 메서드를 이용해 모든 정점 쌍 사이의 거리를 계산한 후, 이를 `Edge` 객체로 표현하여 리스트에 저장한다. 거리 계산은 유클리드 거리를 사용한다.
- `kruskal()` 메서드에서 크루스칼 알고리즘을 적용하여 최소 스패닝 트리를 만든다. 이를 위해 간선 리스트를 거리 기준으로 오름차순 정렬한 후, 사이클 방지를 위해 유니온-파인드(Union-Find) 알고리즘의 `find`와 `union` 연산을 사용한다.
- 간선을 선택할 때마다 두 정점이 서로 다른 집합에 속해 있는 경우에만 합치고, 그 거리를 총 비용(`ans`)에 더한다.
- 모든 간선을 처리한 후, 최소 스패닝 트리의 총 거리를 출력한다. <br/><br/>

```
3
1.0 1.0
2.0 2.0
2.0 4.0
```

### 최종 결과 (116 ms)

```java
import java.util.*;
import java.io.*;

public class Main {

    static class Graph {

        int idx;
        double x, y;

        Graph(int idx, double x, double y) {
            this.idx = idx;
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge> {

        int start, end;
        double dis;

        Edge(int start, int end, double dis) {
            this.start = start;
            this.end = end;
            this.dis = dis;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.dis, o.dis);
        }
    }

    static BufferedReader br;
    static int n;
    static double ans;
    static Graph[] graphs;
    static List<Edge> edges;
    static int[] parent;
    public static void main(String[] args) throws Exception {

//      br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new FileReader("input.txt"));

        n = Integer.parseInt(br.readLine());

        fillGraph();
        connectNodes();

        ans = 0;
        kruskal();

        System.out.println(ans);

        br.close();
    }

    private static void fillGraph() throws Exception {

        graphs = new Graph[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());

            graphs[i] = new Graph(i, x, y);
        }
    }

    private static void connectNodes() {

        edges = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double dis = calculateDis(graphs[i], graphs[j]);

                edges.add(new Edge(graphs[i].idx, graphs[j].idx, dis));
            }
        }
    }

    private static void kruskal() {

        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        Collections.sort(edges);

        for (Edge edge : edges) {
            if (find(edge.start) != find(edge.end)) {
                union(edge.start, edge.end);
                ans += edge.dis;
            }
        }
    }

    private static double calculateDis(Graph g1, Graph g2) {
        return Math.sqrt(Math.pow((g1.x - g2.x), 2) + Math.pow((g1.y - g2.y), 2));
    }

    private static void union(int x, int y) {

        x = find(x);
        y = find(y);

        if (y != x) {
            parent[y] = x;
        }
    }

    private static int find(int x) {

        if (x == parent[x]) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }
}
```
