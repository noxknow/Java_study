# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/13.svg" width="30"> [7579] 앱

📌 → https://www.acmicpc.net/problem/7579 <br/><br/>

**주의할 점**

1) 0 ≤ c1, ..., cN ≤ 100로 cost의 범위가 정해져있고, N 역시 100까지 이기 때문에 둘을 곱했을 때의 최대인 10000까지 dp의 범위를 정해야한다. <br/><br/>

**핵심 로직**

1) 입력값을 각 배열에 대입한다.

2) cost의 범위와 N의 범위를 확인하고 그 최대값인 10001까지 반복문을 진행한다.

3) 앱이 하나인 경우에는 인덱스에 해당하는 앱 하나만 들어갈 수 있기 때문에 i == 0 을 기준으로 처리를 한다.

    3 - 1) i == 0인 경우, dp에 메모리값을 그대로 넣어준다.

    3 - 2) i ≠ 0 인 경우, 현재의 메모리 값과 그 비용 이전의 메모리값의 합 & 그 전까지의 최대 메모리 값을 비교하여 최대값을 dp에 메모이제이션 한다.

4) dp의 메모리 값이 문제에 주어진 m값보다 크거나 같다면 그때마다 ans 값을 갱신한다. <br/><br/>

```
5 60
30 10 20 35 40
3 0 3 5 4
```

### 최종 결과 (160 ms)

```java
import java.util.*;
import java.io.*;

public class Main {

    static int n, m, ans;
    static int[] memory;
    static int[] cost;
    static int[][] dp;
    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        ans = Integer.MAX_VALUE;

        memory = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }

        cost = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[n][10001];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 10001; j++) {
                if (i == 0) {
                    if (j >= cost[i]) dp[i][j] = memory[i];
                } else {
                    if (j >= cost[i]) dp[i][j] = Math.max(dp[i - 1][j - cost[i]] + memory[i], dp[i - 1][j]);
                    else dp[i][j] = dp[i - 1][j];
                }

                if (dp[i][j] >= m) ans = Math.min(ans, j);
            }
        }

        System.out.println(ans);

        br.close();
    }
}
```
