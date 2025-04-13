# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30"> [16398] 행성 연결

📌 → https://www.acmicpc.net/problem/16398 <br/><br/>

**핵심 로직**

1. 최소 스패닝 트리 문제이고, 각 정점마다의 플로우 비용이 행렬로 나와있기 때문에 그에 맞게 이중 for문으로 입력값을 받아준다. 
    1. 이때, Cij = Cji 이기 때문에 중복되는 값을 제외시켜준다.
2. 최소 비용이 될 수 있도록 compareTo 메서드를 오버라이딩 해주고 정렬시킨다.
3. 유니온 - 파인드 자료구조를 활용하여 각 정점을 최소로 연결한 후 값을 출력한다. <br/><br/>

```
3
0 2 3
2 0 1
3 1 0
```

### 최종 결과 (1184 ms)

```java
import java.io.*;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {

        int start, end, cost;

        Node(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static int n;
    static long res;
    static int[] parent;
    static List<Node> graph;
    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        n = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = i + 1; j < n; j++) {
                int cost = Integer.parseInt(input[j]);

                graph.add(new Node(i, j, cost));
            }
        }

        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        Collections.sort(graph);

        for (Node node : graph) {
            if (find(node.start) != find(node.end)) {
                union(node.start, node.end);
                res += node.cost;
            }
        }

        System.out.println(res);

        br.close();
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
