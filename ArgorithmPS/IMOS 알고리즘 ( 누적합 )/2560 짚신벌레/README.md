# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/13.svg" width="30"> [2560] 짚신벌레

📌 → https://www.acmicpc.net/problem/2560

**주의할 점**

- **모듈러 연산**: 점화식에서 계산 결과를 항상 `1000`으로 나눈 나머지로 저장하기 때문에, 값이 `1000` 범위 안에 머물도록 관리해야 한다.
- **음수 방지**: 점화식에 뺄셈이 포함되어 있어 계산 도중 음수가 발생할 수 있다. 이 경우, `+1000`을 추가하여 양수로 만든 뒤 `%1000`을 수행하면 음수 문제를 방지할 수 있다. <br/><br/>

**핵심 로직**

1. 입력값으로 들어온 `a`, `b`, `d`, `n` 값을 저장한다.
2. `dp` 배열을 생성하고, 초기값으로 `dp[0] = 1`을 설정한다.
3. 1일부터 `n`일까지 반복문을 실행하며, 각 일자의 점화식을 계산한다.
    - 현재 날짜가 `a`보다 작으면, 이전 날까지의 결과값 `dp[i-1]`만을 저장한다.
    - 현재 날짜가 `b`보다 작으면, 이전 날의 값과 `a`일 전에 번식한 결과값을 더해 저장한다.
    - 현재 날짜가 `b` 이상이면, 이전 날의 값과 `a`일 전에 번식한 값, 그리고 `b`일 전에 번식이 끝난 값을 더하고 빼서 저장한다. 이 과정에서 음수를 방지하기 위해 `+1000`을 더하고 `%1000`을 수행한다.
4. 계산이 완료된 후, `n-d` 이상까지 누적된 값과 `n-d` 이전의 값을 비교하여 결과를 출력한다.
    - 만약 `n-d`가 0 이상이면, `dp[n] - dp[n-d]` 값을 출력한다.
    - 그렇지 않으면, `dp[n]` 값을 출력한다.
5. 모든 입력과 계산이 완료되면 프로그램을 종료한다. <br/><br/>

```
3 5 7 20000
```

### 최종 결과 (124 ms)

```java
import java.util.*;
import java.io.*;

public class Main {

    static int a, b, d, n;
    static int[] dp;
    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            if (i < a) {
                dp[i] = dp[i - 1] % 1000;
            } else if (i < b) {
                dp[i] = (dp[i - 1] + dp[i - a]) % 1000;
            } else {
                dp[i] = (dp[i - 1] + dp[i - a] - dp[i - b] + 1000) % 1000;
            }
        }

        if (n - d >= 0) {
            System.out.println((dp[n] - dp[n - d] + 1000) % 1000);
        } else {
            System.out.println(dp[n] % 1000);
        }

        br.close();
    }
}
```
