# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30"> [1005] ACM Craft 

📌 → https://www.acmicpc.net/problem/1005 <br/><br/>

**핵심 로직**

1. 각 노드의 진입차수를 저장할 indegree 배열을 만든다
2. 진입차수가 0인 노드를 큐에 넣는다
3. 큐가 빌 때까지 큐에서 원소를 꺼내 해당 노드에서 나가는 간선을 그래프에서 제거
4. 새롭게 진입차수가 0이 된 노드를 큐에 삽입
5. 3 ~ 4 를 반복한다 <br/><br/>

```
2
4 4
10 1 100 10
1 2
1 3
2 4
3 4
4
8 8
10 20 1 5 8 7 1 43
1 2
1 3
2 4
2 5
3 6
5 7
6 7
7 8
7
```

### 최종 결과 (808 ms)

```java
import java.util.*;
import java.io.*;

public class Main {

    static int n, k;
    static int[] time;
    static int[] indegree;
    static int[] res;
    static List<List<Integer>> graphs;
    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            time = new int[n + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                time[i] = Integer.parseInt(st.nextToken());
            }

            graphs = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                graphs.add(new ArrayList<>());
            }

            indegree = new int[n + 1];
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                graphs.get(from).add(to);
                indegree[to]++;
            }

            res = new int[n + 1];

            topologicalSort();

            int winNumber = Integer.parseInt(br.readLine());

            System.out.println(res[winNumber]);
        }

        br.close();
    }

    private static void topologicalSort() {

        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int v = q.poll();
            res[v] += time[v];

            for (int i = 0; i < graphs.get(v).size(); i++) {
                int nv = graphs.get(v).get(i);

                indegree[nv]--;
                if (indegree[nv] == 0) {
                    q.offer(nv);
                }

                res[nv] = Math.max(res[nv], res[v]);
            }
        }
    }
}
```
