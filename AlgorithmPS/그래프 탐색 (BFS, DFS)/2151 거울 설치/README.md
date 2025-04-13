# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/13.svg" width="30"> [2151] 거울 설치

📌 → https://www.acmicpc.net/problem/2151 <br/><br/>

**핵심 로직** (블로그 참고)

1. 출발하는 문(#)을 저장해두고 출발할 수 있는 방향을 우선순위 큐에 넣어둔다.
    - 출발하는 문은 중복되지 않게 *으로 초기화해둔다.
2. 방문 체크 배열(visited)를 int형으로 선언해두고 -1로 초기화 시킨다(거울을 사용하지 않을 경우도 있기 때문에 -1로 초기화)
3. bfs 알고리즘을 동작 시키면서 거울을 놓을 수 있는 곳(!)일 경우, 방향을 현재방향에서 시계, 반시계 방향 90도로 돌리고 cnt를 하나 높이고 큐에 넣어준다.
4. ! or . 일 경우 모두 현재 방향 기준으로 전진시켜서 큐에 넣어준다.
5. 목적지(#)에 도착한 경우 cnt를 출력해주고 함수를 종료시킨다.
    - PriorityQueue를 사용했기 때문에 거울을 최소로 사용한 경우가 보장된다. <br/><br/>

```
5
***#*
*.!.*
*!.!*
*.!.*
*#***
```

### 최종 결과 (152 ms)

```java
import java.io.*;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {

        int x, y, dir, cnt;

        Node(int x, int y, int dir, int cnt) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o) {
            return this.cnt - o.cnt;
        }
    }

    static int n, res;
    static Node startNode;
    static PriorityQueue<Node> pq;
    static int[][] visited;
    static char[][] houseMap;
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        n = Integer.parseInt(br.readLine());

        visited = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(visited[i], -1);
        }

        houseMap = new char[n][n];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                houseMap[i][j] = str.charAt(j);

                if (houseMap[i][j] == '#') {
                    startNode = new Node(i, j, 0, 0);
                }
            }
        }

        houseMap[startNode.x][startNode.y] = '*';

        pq = new PriorityQueue<>();
        for (int i = 0; i < 4; i++) {
            int nx = startNode.x + dx[i];
            int ny = startNode.y + dy[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= n || houseMap[nx][ny] == '*') continue;
            pq.offer(new Node(startNode.x, startNode.y, i, 0));
        }

        bfs();
        System.out.println(res);

        br.close();
    }

    private static void bfs() {

        visited[startNode.x][startNode.y] = 0;

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();

            if (houseMap[curNode.x][curNode.y] == '#') {
                res = curNode.cnt;
                return;
            }

            int nx = curNode.x + dx[curNode.dir];
            int ny = curNode.y + dy[curNode.dir];

            if (nx < 0 || ny < 0 || nx >= n || ny >= n || houseMap[nx][ny] == '*') continue;
            if (visited[nx][ny] >= curNode.cnt) continue; // pq를 통해서 이미 최소의 거울 개수를 구하고 있기 때문에 현재의 cnt가 visited보다 크거나 같은 부분은 의미가 없다.

            visited[nx][ny] = curNode.cnt;

            if (houseMap[nx][ny] == '!') {
                pq.offer(new Node(nx, ny, (curNode.dir+1)%4, curNode.cnt+1));
                pq.offer(new Node(nx, ny, (curNode.dir+3)%4, curNode.cnt+1));
            }

            pq.offer(new Node(nx, ny, curNode.dir, curNode.cnt));
        }
    }
}
```

### 참고

[https://velog.io/@hoonze/백준-2151-거울-설치JAVA](https://velog.io/@hoonze/%EB%B0%B1%EC%A4%80-2151-%EA%B1%B0%EC%9A%B8-%EC%84%A4%EC%B9%98JAVA)
