# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/13.svg" width="30"> [9466] 텀 프로젝트 

📌 → https://www.acmicpc.net/problem/9466 <br/><br/>

**주의할 점**

1. 일반 BFS로 모든 경우를 다 돌면서 처리를 할경우 O(N^2) 으로  T * (100,000) * (100,000) 으로 최악의 경우에 100억 * T 의 시간이 걸린다. <br/><br/>

**핵심 로직**

1. 방문 체크와 팀 완성 여부의 두개 배열을 활용해서 팀 여부를 파악한다.
    1. 싸이클이 완성되어 이미 방문했다면 count를 늘린다.
    2. 방문하지 않았다면 방문 처리
2. 팀 결성이 되지 않았다면 dfs를 해준다. <br/><br/>

```
2
7
3 1 3 7 3 4 6
8
1 2 3 4 5 6 7 8
```

### 최종 결과 (924 ms)

```java
import java.util.*;
import java.io.*;

public class Main {

    static int n, count;
    static boolean[] visit, done;
    static int[] graph;
    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {

            n = Integer.parseInt(br.readLine());
            visit = new boolean[n+1];
            done = new boolean[n+1];
            count = 0;

            graph = new int[n + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                int num = Integer.parseInt(st.nextToken());
                graph[i] = num;
            }

            for(int i=1; i<= n; i++){
                if(!done[i]){
                    dfs(i);
                }
            }

            System.out.println(n - count);
        }

        br.close();
    }

    public static void dfs(int n) {

        if (visit[n]) {
            done[n] = true;
            count++;
        } else {
            visit[n] = true;
        }

        if (!done[graph[n]]) {
            dfs(graph[n]);
        }

        visit[n] = false;
        done[n] = true;
    }
}
```
