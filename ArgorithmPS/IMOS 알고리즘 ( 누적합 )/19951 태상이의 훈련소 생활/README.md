# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/11.svg" width="30"> [19951] 태상이의 훈련소 생활 

📌 → https://www.acmicpc.net/problem/19951 <br/><br/>

**핵심 로직**

1. 입력값으로 들어온 높이값을 저장하는 heights 배열을 만든다.
2. 누적합을 계산하기 위해 특정구간의 시작과 끝의 변화를 afterHeights 배열에 저장한다. 이때, 시작 인덱스는 + 끝 인덱스는 - 해준다.
3. 저장했던 afterHeights 배열을 활용하여 누적합을 계산하고 기존의 heights 배열과 더해 최종결과를 출력한다. <br/><br/>

```
10 3
1 2 3 4 5 -1 -2 -3 -4 -5
1 5 -3
6 10 5
2 7 2
```

### 최종 결과 (1208 ms)

```java
import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static int[] heights;
    static int[] afterHeights;
    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        heights = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        afterHeights = new int[n + 2];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int diffHeight = Integer.parseInt(st.nextToken());

            afterHeights[s] += diffHeight;
            afterHeights[e + 1] -= diffHeight; // 1 ~ n 이기 때문에 + 1
        }

        for (int i = 1; i <= n; i++) {
            afterHeights[i] += afterHeights[i - 1];
        }

        for (int i = 1; i <= n; i++) {
            System.out.print(heights[i] + afterHeights[i] + " ");
        }

        br.close();
    }
}
```
