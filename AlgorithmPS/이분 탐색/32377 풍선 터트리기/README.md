# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/13.svg" width="30"> [32377] 풍선 터트리기

📌 → https://www.acmicpc.net/problem/32377 <br/><br/>

**주의할 점**

**핵심 로직**

- `binarySearch` 메서드는 풍선 개수 N을 만족하는 **최소 시간**을 찾기 위해 이분 탐색을 수행한다.
    - 가능한 시간의 범위를 1분부터 최대 `최대 주기 × N`까지 설정하고, 중간값을 기준으로 A, B, C가 그 시간까지 터트릴 수 있는 풍선의 총합을 계산한다.
    - 풍선 총합이 N 이상이면 조건을 만족하므로 더 작은 시간으로도 가능할 수 있으므로, 오른쪽 경계를 줄인다.
    - 총합이 N 미만이면 아직 풍선이 부족하므로 왼쪽 경계를 늘려 더 큰 시간을 탐색한다.
- 탐색이 끝나고 얻은 시간은 **N번째 풍선이 터지는 정확한 시각**이며, 이 값을 `target` 변수에 저장한다.
- `getLastWinner` 메서드는 `target` 시점 직전까지 터진 풍선 개수를 구하고, 그 이후 `target` 시점에 A, B, C가 풍선을 터뜨리는지를 확인한다.
    - 먼저 `target - 1` 시점까지 각 사람이 터뜨린 풍선 수의 총합을 계산하여 `beforeTarget` 변수에 저장한다.
    - 이후 `target` 시점에 풍선을 터뜨릴 수 있는 사람을 A → B → C 순으로 확인하고, 그 사람의 풍선이 `N`번째라면 승자로 판단하여 출력한다.
- 최종적으로 `target` 시점에 **N번째 풍선을 터트리는 사람**을 찾아 `"A win"`, `"B win"`, `"C win"` 중 하나를 출력한다. <br/><br/>

```
10 5 3 6
```

### 최종 결과 (104 ms)

```java
import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br;
    static long n,maxTime, target, beforeTarget;
    static long[] times;
    public static void main(String[] args) throws Exception {

//      br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Long.parseLong(st.nextToken());
        times = new long[3];

        for (int i = 0; i < 3; i++) {
            times[i] = Long.parseLong(st.nextToken());
            maxTime = Math.max(maxTime, times[i]);
        }

        target = binarySearch();

        getLastWinner();

        br.close();
    }

    private static long binarySearch() {

        long left = 1;
        long right = maxTime * n;

        while (left <= right) {

            long mid = (left + right) / 2;
            long cnt = 0;

            for (int i = 0; i < 3; i++) {
                cnt += (mid / times[i]);
            }

            if (cnt >= n) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private static void getLastWinner() {

        beforeTarget = 0;
        for (int i = 0; i < 3; i++) {
            beforeTarget += ((target - 1) / times[i]);
        }

        for (int i = 0; i < 3; i++) {
            if (target % times[i] == 0) {
                beforeTarget++;
                if (beforeTarget == n) {
                    if (i == 0) {
                        System.out.println("A win");
                    } else if (i == 1) {
                        System.out.println("B win");
                    } else {
                        System.out.println("C win");
                    }
                    break;
                }
            }
        }
    }
}
```
