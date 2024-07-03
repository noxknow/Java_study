# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/14.svg" width="30"> [1202] 보석 도둑

📌 → https://www.acmicpc.net/problem/1202 <br/><br/>

**주의 사항**

1. 모든 가방에 대해 모든 보석을 검사하면 문제를 해결할 수 있으나, N과 K는 최대 30만이기 때문에 O(9 x 10^10)으로 시간 초과가 난다. 따라서 O(N^2)보다 줄이기 위해서 우선순위 큐 자료구조를 이용한다. <br/><br/>


**핵심 로직**

1. 보석은 무게에 대해 오름차순 정렬하되, 무게가 같을 경우 가격에 대해 내림차순 정렬한다.
2. 가방은 오름차순 정렬한다.
3. 모든 가방에 대해서 반복문을 수행한다.
    
    3-1. 특정 가방의 무게보다 작은 보석의 가격을 모두 우선순위 큐에 집어넣는다. (이때, 우선순위 큐는 가격에 대해 내림차순 정렬을 해야한다.)
    
    3-2. 우선순위 큐가 비어있지 않다면, 우선순위 큐에서 맨 앞 요소를 하나 빼고 그 가격을 ans에 더한다. <br/><br/>

    

```
2 1
5 10
100 100
11
```

### 최종 결과 (1980 ms)

```java
import java.util.*;
import java.io.*;

public class Main {

    static class Jewelry implements Comparable<Jewelry> {

        int m, v;

        Jewelry(int m, int v) {
            this.m = m;
            this.v = v;
        }

        @Override
        public int compareTo(Jewelry o) {
            if (this.m == o.m) {
                return o.v - this.v;
            }

            return this.m - o.m;
        }
    }

    static int n, k;
    static int[] bags;
    static Jewelry[] jewelries;
    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        jewelries = new Jewelry[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            jewelries[i] = new Jewelry(m, v);
        }

        Arrays.sort(jewelries);

        bags = new int[k];
        for (int i = 0; i < k; i++) {
            int c = Integer.parseInt(br.readLine());
            bags[i] = c;
        }

        Arrays.sort(bags);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        long ans = 0;
        for (int i = 0, j = 0; i < k; i++) {

            while (j < n && jewelries[j].m <= bags[i]) {
                pq.offer(jewelries[j++].v);
            }

            if (!pq.isEmpty()) {
                ans += pq.poll();
            }
        }

        System.out.println(ans);

        br.close();
    }
}
```
