# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30"> [2458] 키 순서

📌 → https://www.acmicpc.net/problem/2458 <br/><br/>

**핵심 로직**

1. 모든 정점에서 모든 정점으로의 관계를 파악하기 위해 graph이란 변수를 만들고 초기화 시켜준다. 
    1. 이때, i == j 를 제외한 다른 값들을 INF로 초기화 시킴으로서 최소 경로가 선택될 수 있도록 만들어준다.
2. 시작 정점, 끝 정점, 중간 정점에 대한 3중 for문을 통해 모든 정점간의 관계를 채운다.
3. 키 순서를 알기 위해서는 모든 정점과 관계가 있어야 하기 때문에 path를 통해서 관계있는 정점의 개수를 세어준다.
4. 관계있는 정점의 개수가 n - 1에 도달하면 키 순서를 알 수 있기떄문에 출력값을 늘려준다. <br/><br/>

```
6 6
1 5
3 4
5 4
4 2
4 6
5 2
```

### 최종 결과 (616 ms)

```java
import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int INF = 987654321;
    static int[][] graph;
    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = INF;

                if (i == j) graph[i][j] = 0;
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int p1 = Integer.parseInt(st.nextToken()) - 1;
            int p2 = Integer.parseInt(st.nextToken()) - 1;

            graph[p1][p2] = 1;
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        int res = 0;
        int[] path = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;

                if (graph[i][j] < INF) {
                    path[i] += 1;
                    path[j] += 1;

                    if(path[i] == n - 1) res += 1;
                    if(path[j] == n - 1) res += 1;
                }
            }
        }

        System.out.println(res);

        br.close();
    }
}
```
