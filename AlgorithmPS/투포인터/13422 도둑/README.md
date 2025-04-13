# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30"> [13422] 도둑

📌 https://www.acmicpc.net/problem/13422 <br/><br/>

**주의할 점**

1. 1~9번의 집이 있다면 9에서 1번집으로 갈 수 있음을 주의하며 풀이한다.
2. 입력이 [3 3 5] [1 1 1] 이라면  1 > 2 > 3 , 2 > 3 > 1, 3 > 1> 2가 모두 cnt에 누적돼 답이 3이 나오게된다. 이를 방지하기 위해 n == m 일때 break 하도록 한다. <br/><br/>

**핵심 로직**

1. 입력으로부터 여러 테스트 케이스를 처리하며 주어진 조건을 만족하는 구간의 개수를 계산한다.
2. 외부 반복문에서 각 테스트 케이스를 처리한다.
    1. 첫 줄에서 `n`(배열 길이), `m`(구간 길이), `k`(비교 기준 값)을 읽어온다.
    2. 두 번째 줄에서 `n`개의 정수를 읽어 `moneys` 배열에 저장한다.
3. 구간 합을 이용해 조건을 만족하는 구간의 개수를 계산한다.
    1. 초기 `sum`은 0으로 설정하고, 구간 시작 시점에서 구간 끝까지 누적합을 계산한다.
    2. 배열의 요소를 순회하며, 현재 요소를 합산하고 `sum` 값이 `k`보다 작으면 구간의 개수를 증가시킨다.
    3. 구간 길이 `m`을 초과한 경우, 가장 오래된 요소를 `sum`에서 빼 구간 합을 유지한다.
4. 만약 배열 길이 `n`이 구간 길이 `m`과 같을 경우 순환 계산을 피하기 위해 내부 루프를 일찍 종료한다.
5. 각 테스트 케이스의 결과를 `StringBuilder`에 저장하고, 모든 테스트 케이스를 처리한 후 출력한다. <br/><br/>

```
2
8 3 15
3 4 7 5 6 4 2 9
2 1 5
4 5
```

### 최종 결과 ( 528 ms )

```java
import java.util.*;
import java.io.*;

public class Main {

    static int testCase, n, m, k;
    static int[] moneys;
    static StringBuilder res;
    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        testCase = Integer.parseInt(br.readLine());
        res = new StringBuilder();
        for (int t = 1; t <= testCase; t++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st1.nextToken());
            m = Integer.parseInt(st1.nextToken());
            k = Integer.parseInt(st1.nextToken());

            moneys = new int[n];
            int sum = 0;
            int cnt = 0;
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int i = 0; i < n + (m - 1); i++) {
                if (i < n) {
                    moneys[i] = Integer.parseInt(st2.nextToken());
                    sum += moneys[i];
                } else {
                    sum += moneys[i - n];
                }

                if (i >= m - 1) {
                    if (sum < k) {
                        cnt++;
                    }
                    sum -= moneys[i - (m - 1)];
                }

                if (n == m && i == n - 1) break;
            }

            res.append(cnt).append("\n");
        }

        System.out.println(res);

        br.close();
    }
}

```
