# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/13.svg" width="30"> [23286] 허들 넘기

📌 → https://www.acmicpc.net/problem/23286 <br/><br/>

**주의할 점**

**핵심 로직**

- `fillGraph` 메서드는 입력으로 주어진 간선 정보를 바탕으로 그래프를 초기화한다.
    - 먼저 `n + 1 × n + 1` 크기의 2차원 배열을 생성하고, 자기 자신을 제외한 모든 정점 간 거리를 `INF`로 설정한다.
    - 이후 `m`개의 간선 정보를 읽어와, 시작 정점 `u`에서 도착 정점 `v`로 가는 가중치 `h`를 `graphs[u][v]`에 저장한다.
    - 이는 단방향 그래프이며, 간선이 여러 개 들어올 수 있으므로 가장 마지막에 입력된 가중치로 덮어쓴다.
- `floydWarshall` 메서드는 플로이드-워셜 알고리즘을 사용하여 **최대 최소 비용 경로**를 구한다.
    - 일반적인 플로이드-워셜 알고리즘에서는 `min()`을 통해 모든 정점 간 최소 거리 합을 구하지만, 이 문제에서는 두 경로 중 **더 긴 경로를 최소화**해야 하므로 `max()`를 사용하여 중간 경유지를 지날 경우의 최대값 중 가장 작은 값을 구한다.
    - 조건문은 현재 `i → j`로 바로 가는 비용이 `i → k`와 `k → j`로 거쳐 가는 비용보다 큰 경우에만 업데이트를 수행한다.
- `printResult` 메서드는 주어진 `t`개의 질의에 대해 두 지점 `s`에서 `e`로 가는 최소 최대비용을 출력한다.
    - `graphs[s][e]`의 값이 `INF`이면 경로가 없다는 의미이므로 `1`을 출력하고,
    - 그 외에는 해당 값을 그대로 출력한다.
- 최종적으로 프로그램은 입력 파일에서 정점 수, 간선 수, 질의 수를 읽은 후 그래프를 초기화하고, 플로이드-워셜 알고리즘을 적용한 뒤, 각 질의에 대한 결과를 출력한다. <br/><br/>

```
5 6 3
1 2 12
3 2 8
1 3 5
2 5 3
3 4 4
2 4 8
3 4
1 2
5 1
```

### 최종 결과 (636 ms)

```java
import java.util.*;
import java.io.*;

public class Main {

    static final int INF = 123456789;

    static BufferedReader br;
    static int n, m, t;
    static int[][] graphs;
    public static void main(String[] args) throws Exception {

//      br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        fillGraph();
        floydWarshall();
        printResult();

        br.close();
    }

    private static void fillGraph() throws Exception {

        graphs = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (i != j) {
                    graphs[i][j] = INF;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            graphs[u][v] = h;
        }
    }

    private static void floydWarshall() {

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (graphs[i][j] > graphs[i][k] && graphs[i][j] > graphs[k][j]) {
                        graphs[i][j] = Math.max(graphs[i][k], graphs[k][j]);
                    }
                }
            }
        }
    }

    private static void printResult() throws Exception {

        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            System.out.println(graphs[s][e] == INF ? -1 : graphs[s][e]);
        }
    }
}
```
