# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/14.svg" width="30"> [1365] 꼬인 전깃줄

📌 → https://www.acmicpc.net/problem/1365 <br/><br/>

**핵심 로직**

- 숫자 배열에서 최장 증가 부분 수열의 길이를 구하기 위해 dp 배열을 사용한다.
- dp[0]에는 작은 수를 초기화하여 이후 비교가 편하도록 한다.
- 배열의 각 요소를 순회하면서 현재 요소가 dp 배열의 마지막 값보다 크면 dp에 추가하여 수열의 길이를 늘린다.
    - 만약 작다면 이분 탐색을 통해 dp 배열 내에서 현재 요소가 들어갈 위치를 찾고, 해당 위치의 값을 갱신한다.
- 모든 요소를 순회한 뒤, n에서 최장 증가 부분 수열의 길이를 뺀 값을 출력하여 결과를 구한다. <br/><br/>

```
4
2 3 4 1
```

### 최종 결과 (284 ms)

```java
import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int[] numbers, dp;
    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        n = Integer.parseInt(br.readLine());
        dp = new int[n + 1];
        numbers = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = -100000001;
        int len = 0;
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (numbers[i] > dp[len]) {
                dp[++len] = numbers[i];
            } else {
                idx = binarySearch(0, len, numbers[i]);
                dp[idx] = numbers[i];
            }
        }

        System.out.println(n - len);

        br.close();
    }

    private static int binarySearch(int left, int right, int target) {

        while (left < right) {

            int mid = (left + right) / 2;

            if (dp[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return right;
    }
}

```
