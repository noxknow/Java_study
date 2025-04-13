# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30"> [20040] 사이클 게임 

📌 → https://www.acmicpc.net/problem/20040 <br/><br/>

**핵심 로직**

1. union - find 자료구조를 활용한다.
2. 사이클이 처음 만들어지는 차례를 찾아야 하기 때문에 차례마다 find를 통해 부모가 같은지 확인하고 같지 않다면 union을 통해 연결하고, 만약 같다면 가장 처음 만들어진 사이클이기 때문에 그때의 차례를 출력한다. <br/><br/>

```
6 5
0 1
1 2
2 3
5 4
0 4
```

### 최종 결과 (576 ms)

```java
import java.util.*;
import java.io.*;

public class Main {

    static class Node {

        int start, end;

        Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    static int n, m, ans;
    static int[] parent;
    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            if (find(v1) == find(v2)) {
                System.out.println(i + 1);
                return;
            } else {
                union(v1, v2);
            }
        }

        System.out.println(0);

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
