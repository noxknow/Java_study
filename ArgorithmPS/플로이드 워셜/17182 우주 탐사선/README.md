# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/13.svg" width="30"> [17182] 우주 탐사선

📌 → https://www.acmicpc.net/problem/17182 <br/><br/>

**핵심 로직**

- `fillGraph()` 메서드는 입력 파일(`input.txt`)로부터 그래프의 가중치를 읽어와 2차원 배열 `times`에 저장한다. 이 배열은 노드 간의 이동 비용을 나타낸다.
- `floydWarshall()` 메서드는 플로이드-워셜 알고리즘을 사용하여 그래프에서 모든 쌍 간 최단 경로를 계산한다. 이는 동적 계획법을 활용하여 현재 경로와 경유 노드를 비교해 최단 경로를 갱신한다.
- `dfs()` 메서드는 깊이 우선 탐색(DFS)을 활용하여 가능한 모든 방문 순서를 탐색한다.
    - `depth`는 현재 탐색 깊이를 나타내며, 방문한 노드의 개수를 추적한다.
    - `start`는 현재 노드를 나타내며, 탐색의 진행 상황을 갱신한다.
    - `totalTime`은 현재까지의 경로 비용을 누적한다.
    - 모든 노드를 방문했을 경우(`depth == n - 1`), 현재까지의 총 비용(`totalTime`)과 결과값(`res`)을 비교하여 최소값을 갱신한다.
- `visited` 배열은 이미 방문한 노드를 표시하여 중복 방문을 방지한다. <br/><br/>

```
3 0
0 30 1
1 0 29
28 1 0
```

### 최종 결과 (128 ms)

```java
import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br;
    static int n, k, res;
    static int[][] times;
    static boolean[] visited;
    public static void main(String[] args) throws Exception {

//      br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        res = Integer.MAX_VALUE;

        fillGraph();
        floydWarshall();

        visited = new boolean[n];
        visited[k] = true;
        dfs(0, k, 0);

        System.out.println(res);

        br.close();
    }

    private static void fillGraph() throws Exception {

        times = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                times[i][j] = Integer.parseInt(st1.nextToken());
            }
        }
    }

    private static void floydWarshall() {

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j) continue;
                    times[i][j] = Math.min(times[i][j], times[i][k] + times[k][j]);
                }
            }
        }
    }

    private static void dfs(int depth, int start, int totalTime) {

        if (depth == n - 1) {
            res = Math.min(res, totalTime);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(depth + 1, i, totalTime + times[start][i]);
                visited[i] = false;
            }
        }
    }
}
```
