# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30"> [2573] 빙산

📌 → https://www.acmicpc.net/problem/2573 <br/><br/>

**주의할 점**

1. 시간 초과가 난 코드와 달랐던 부분은 그룹 갯수를 언제 카운트해서 종료시키냐의 차이였다. 종료 시점에 따라 시간 차이가 많이 나니 조심해야한다.
2. minusIce 함수 내부의 visited 배열을 둠으로써 원래 있던 빙하가 녹아 0이 되는 부분은 카운트 되지 않도록 한다. <br/><br/>

**핵심 로직**

1. 빙하를 녹이는 minusIce 메서드는 BFS를 사용하여 빙하를 녹여준다.
    1. 이때, 빙하가 없는 부분이 아니라 있는 부분의 상하좌우를 탐색하여 빙하가 없는 곳이 2곳 이상이라면 그 수만큼 숫자를 줄여준다.
2. countGroup 메서드 내부에서 DFS 메서드를 부르고, 이 DFS 메서드는 group이 몇개 나오는지 탐색한다.
3. 이 과정에서 그룹이 2개 이상이 나올때까지 반복하며 매 반복마다 time을 늘려준다. <br/><br/>

```
5 7
0 0 0 0 0 0 0
0 2 4 5 3 0 0
0 3 0 2 5 2 0
0 7 6 2 4 0 0
0 0 0 0 0 0 0
```

### 최종 결과 (608 ms)

```java
import java.io.*;
import java.util.*;

public class Main {

    static class Node {

        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n, m, time;
    static int[][] iceberg;
    static Queue<Node> q;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        iceberg = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                iceberg[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        time = 0;
        while (true) {
            int groupNum = countGroup();
            if (groupNum >= 2) {
                System.out.println(time);
                break;
            }
            if (groupNum == 0) {
                System.out.println(0);
                break;
            }
            minusIce();
            time++;
        }

        br.close();
    }

    private static void minusIce() {

        q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (iceberg[i][j] != 0) {
                    q.add(new Node(i, j));
                    visited[i][j] = true;
                }
            }
        }

        while (!q.isEmpty()) {
            Node tmp = q.poll();
            int seaCount = 0;

            for (int i = 0; i < 4; i++) {
                int nx = tmp.x + dx[i];
                int ny = tmp.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                if (!visited[nx][ny] && iceberg[nx][ny] == 0) seaCount++;
            }

            iceberg[tmp.x][tmp.y] = Math.max(0, iceberg[tmp.x][tmp.y] - seaCount);
        }
    }

    private static int countGroup() {
        boolean[][] visited = new boolean[n][m];
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && iceberg[i][j] > 0) {
                    dfs(i, j, visited);
                    count++;

                    if (count >= 2) return count;
                }
            }
        }

        return count;
    }

    private static void dfs(int i, int j, boolean[][] visited) {

        visited[i][j] = true;

        for (int k = 0; k < 4; k++) {
            int nx = i + dx[k];
            int ny = j + dy[k];

            if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

            if (!visited[nx][ny] && iceberg[nx][ny] != 0) {
                dfs(nx, ny, visited);
            }
        }
    }
}
```

### 시간 초과

```java
import java.io.*;
import java.util.*;

public class Main {

    // 중략
    public static void main(String[] args) throws IOException {

        // 중략

        groupNum = 1;
        while (groupNum == 1) {
            minusIce();
            countGroup();
            time++;
        }

        if (groupNum == 1) time = 0;

        System.out.println(time);

        br.close();
    }

    private static void minusIce() {

        // 중략
    }

    private static void countGroup() {

        int cnt = 0;
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && iceberg[i][j] != 0) {
                    dfs(i, j, visited);
                    cnt++;

                    if (cnt >= 2) {
                        groupNum = cnt;
                        return;
                    }
                }
            }
        }
    }

    private static void dfs(int i, int j, boolean[][] visited) {

        // 중략
    }
}
```
