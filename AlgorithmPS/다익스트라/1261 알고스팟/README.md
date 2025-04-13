# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30"> [1261] 알고스팟

📌 → https://www.acmicpc.net/problem/1261 <br/><br/>

**주의할 점**

1. n과 m이 헷갈리지 않도록 한다. <br/><br/>

**핵심 로직**

1. 최소한으로 벽을 부수고 목적지에 도달해야하기 때문에 PriorityQueue를 활용한다.
    1. 벽이 있을 때 ) pq에 추가할 때 벽 부순 갯수 + 1을 해준다.
    2. 벽이 없을 때 ) pq에 추가할 때 벽 부순 갯수 그대로 들어간다.
2. 미로를 움직일 때 마다 부순 벽의 갯수를 walls 배열에 입력해둔 후 출력한다. <br/><br/>

```
3 3
011
111
110
```

### 최종 결과 (148 ms)

```java
import java.io.*;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {

        int x, y, wall;

        Node(int x, int y, int wall) {
            this.x = x;
            this.y = y;
            this.wall = wall;
        }

        @Override
        public int compareTo(Node o) {
            return this.wall - o.wall;
        }
    }

    static int n, m;
    static int[][] maze;
    static int[][] walls;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        maze = new int[m][n];
        walls = new int[m][n];
        for (int i = 0; i < m; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                maze[i][j] = Character.getNumericValue(str.charAt(j));
            }
        }

        dijkstra();

        System.out.println(walls[m - 1][n - 1]);

        br.close();
    }

    public static void dijkstra() {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[m][n];

        pq.add(new Node(0, 0, 0));
        visited[0][0] = true;
        while (!pq.isEmpty()) {
            Node tmp = pq.poll();

            for (int i = 0; i < 4; i++) {
                int nx = tmp.x + dx[i];
                int ny = tmp.y + dy[i];

                if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                if (visited[nx][ny]) continue;

                if (maze[nx][ny] == 1) {
                    pq.add(new Node(nx, ny, tmp.wall + 1));
                    walls[nx][ny] = tmp.wall + 1;
                } else {
                    pq.add(new Node(nx, ny, tmp.wall));
                    walls[nx][ny] = tmp.wall;
                }

                visited[nx][ny] = true;
            }
        }
    }
}
```
