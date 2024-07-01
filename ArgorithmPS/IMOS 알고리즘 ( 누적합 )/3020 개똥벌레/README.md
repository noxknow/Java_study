# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/11.svg" width="30"> [3020] 개똥벌레

📌 → https://www.acmicpc.net/problem/3020 <br/><br/>

**핵심 로직**

1. Imos 알고리즘을 활용한 누적합 방법으로 풀이했다.
2. 누적합을 구하기 위한 카운팅 배열을 만든다. 
    1. 석순의 경우 stalactites 배열에 카운팅하고, 종유석의 경우는 stalagmites 배열에 카운팅하는데 이때, stalagmites 배열은 기존 동굴의 높이인 h 에서 빼고 + 1을 한 높이의 인덱스에 값을 카운팅한다.
3. 카운팅 배열을 토대로 누적합을 계산하는데 이때, 석순은 카운팅 되어있는 인덱스 기준 아래쪽으로 나있기 때문에 위쪽부터 누적합을 더해나가고, 종유석은 인덱스 기준 위쪽이기 때문에 아래부터 누적합을 채워나간다.
4. 최소 장애물 수와 최소 개수를 초기화 해두고 장애물 수가 최소 장애물 수 보다 작다면 최소 장애물 수 를 현재의 장애물 수로 변경하고 최소 개수를 1로 초기화 한다.
    1. 만약, 장애물 수와 최소 장애물 수가 같다면 최소 개수를 늘려준다. <br/><br/>

```
6 7
1
5
3
3
5
1
```

### 최종 결과 (324 ms)

```java
import java.io.*;
import java.util.*;

public class Main {

    static int n, h;
    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        int[] stalactites = new int[h + 1];
        int[] stalagmites = new int[h + 1];
        for (int i = 0; i < n / 2; i++) {
            int stalactitesHeight = Integer.parseInt(br.readLine());
            int stalagmitesHeight = Integer.parseInt(br.readLine());

            stalactites[stalactitesHeight]++;
            stalagmites[h - stalagmitesHeight + 1]++;
        }

        for (int i = h - 1; i >= 1; i--) {
            stalactites[i] += stalactites[i + 1];
        }

        for (int i = 2; i <= h; i++) {
            stalagmites[i] += stalagmites[i - 1];
        }

        int minObstacles = Integer.MAX_VALUE;
        int minCount = 0;
        for (int i = 1; i <= h; i++) {
            int obstacles = stalactites[i] + stalagmites[i];

            if (obstacles < minObstacles) {
                minObstacles = obstacles;
                minCount = 1;
            } else if (obstacles == minObstacles) {
                minCount++;
            }
        }

        System.out.println(minObstacles + " " + minCount);

        br.close();
    }
}

```
