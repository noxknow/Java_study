# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/15.svg" width="30"> [1944] 복제 로봇

📌 → https://www.acmicpc.net/problem/1944 <br/><br/>

**핵심 로직**

- 미로의 정보를 채우고, 시작점(S)과 열쇠(K)의 위치를 저장한다.
- BFS를 이용해 각 시작점(S 또는 K)에서 다른 모든 시작점으로의 최단 거리를 계산하여 그래프를 구성한다.
- 계산된 최단 거리 정보를 `Vertex` 객체로 표현하고, 우선순위 큐에 삽입하여 오름차순으로 정렬된 상태를 유지한다.
- 크루스칼 알고리즘을 이용해 최소 스패닝 트리를 만든다. 이 과정에서 사이클을 방지하기 위해 `find`와 `union` 연산을 사용한다.
- 연결된 간선의 개수가 열쇠의 개수(M)와 다르면 모든 점을 연결할 수 없으므로 -1을 반환한다.
- 연결된 간선이 열쇠 개수와 같다면 최소 비용으로 모든 점을 연결한 총 거리를 반환한다. <br/><br/>

```
5 2
11111
1S001
10001
1K1K1
11111
```

### 최종 결과 (388 ms)

```java
import java.util.*;
import java.io.*;

public class Main {

    static class Node {

        int x, y, dis;

        Node (int x, int y, int dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;
        }
    }

    static class Vertex implements Comparable<Vertex> {

        int s, e, edge;

        Vertex(int s, int e, int edge) {
            this.s = s;
            this.e = e;
            this.edge = edge;
        }

        @Override
        public int compareTo(Vertex o) {
            return this.edge - o.edge;
        }
    }

    static BufferedReader br;
    static int n, m;
    static char[][] maze;
    static boolean[][] visited;
    static List<Node> nodes;
    static PriorityQueue<Vertex> vertexes;
    static int[] parent;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    public static void main(String[] args) throws Exception {

//      br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        nodes = new ArrayList<>();
        vertexes = new PriorityQueue<>();

        fillMaze();

        for (int i = 0; i < m + 1; i++) {
            bfs(i);
        }

        System.out.println(kruskal());

        br.close();
    }

    private static void fillMaze() throws Exception {

        maze = new char[n][n];
        for (int i = 0; i < n; i++) {
            char[] words = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                maze[i][j] = words[j];

                if (maze[i][j] == 'S' || maze[i][j] == 'K') {
                    nodes.add(new Node(i, j, 0));
                }
            }
        }
    }

    private static void bfs(int start) {

        Queue<Node> q = new LinkedList<>();
        q.add(nodes.get(start));
        visited = new boolean[n][n];
        visited[nodes.get(start).x][nodes.get(start).y] = true;

        while (!q.isEmpty()) {
            Node curNode = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = curNode.x + dx[i];
                int ny = curNode.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (maze[nx][ny] == '1' || visited[nx][ny]) continue;

                if (maze[nx][ny] == 'S' || maze[nx][ny] == 'K') {
                    for (int j = 0; j < m + 1; j++) {
                        if (nodes.get(j).x == nx && nodes.get(j).y == ny) {
                            vertexes.add(new Vertex(start, j, curNode.dis + 1));
                        }
                    }
                }

                visited[nx][ny] = true;
                q.add(new Node(nx, ny, curNode.dis + 1));
            }
        }
    }

    private static int kruskal() {

        parent = new int[m + 1];
        for (int i = 0; i < m + 1; i++) {
            parent[i] = i;
        }

        int totalDis = 0;
        int edgeCount = 0;
        while (!vertexes.isEmpty()) {
            Vertex curVertex = vertexes.poll();

            if (find(curVertex.s) != find(curVertex.e)) {
                union(curVertex.s, curVertex.e);
                totalDis += curVertex.edge;
                edgeCount++;
            }
        }

        if (edgeCount != m) return -1;
        return totalDis;
    }

    private static void union(int x, int y) {

        x = find(x);
        y = find(y);

        if (x != y) {
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
