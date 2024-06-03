# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30"> [6497] 전력난

📌 → https://www.acmicpc.net/problem/6497 <br/><br/>

**주의할 점**

1. 입력이 여러개의 테스트 케이스로 구분되어 있다고 했고, 입력의 끝이 0, 0이라고 나와있기 때문에 while문을 통해서 반복을 해주고 변수들을 초기화 해줘야 한다. <br/><br/>

**핵심 로직**

1. 여러개의 테스트 케이스를 받기 위한 while문 내부에서 입력값을 받아준다.
2. 사용하는 액수가 아닌 절약 액수이기 때문에 res 변수에 미리 모든 액수를 담아둔다.
3. 모든 간선에 대한 정보를 graph변수에 넣어주고 오름차순으로 정렬해준다.
4. 유니온-파인드 구조를 활용하여 최소 신장 트리를 완성하고 res 변수에 저장해둔 액수에서 사용한 액수를 빼준 절약 액수를 출력한다. <br/><br/>

```
7 11
0 1 7
0 3 5
1 2 8
1 3 9
1 4 7
2 4 5
3 4 15
3 5 6
4 5 8
4 6 9
5 6 11
0 0
```

### 최종 결과 (928 ms)

```java
import java.io.*;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {
        int start, end, dist;

        Node(int start, int end, int dist) {
            this.start = start;
            this.end = end;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }

    static int m, n, res;
    static int[] parent;
    static List<Node> graph;
    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            res = 0;

            if (m == 0 && n == 0) {
                br.close();
                return;
            }

            graph = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int dist = Integer.parseInt(st.nextToken());

                graph.add(new Node(start, end, dist));
                res += dist;
            }

            parent = new int[m];
            for (int i = 0; i < m; i++) {
                parent[i] = i;
            }

            Collections.sort(graph);

            for (Node curNode : graph) {
                if (find(curNode.start) != find(curNode.end)) {
                    union(curNode.start, curNode.end);

                    res -= curNode.dist;
                }
            }

            System.out.println(res);
        }
    }

    public static void union(int x, int y) {
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
