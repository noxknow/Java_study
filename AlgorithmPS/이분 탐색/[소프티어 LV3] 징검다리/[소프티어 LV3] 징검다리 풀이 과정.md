📌 → https://softeer.ai/practice/6293 <br/><br/>

DP의 대표적인 LIS문제이다. 문제에서 주어지는 입력이 1억으로 DP로도 충분히 값을 구할 수 있지만 이분 탐색을 활용한다면 더 빠르게 풀 수 있다.

```
5
3 2 1 4 5
```

<br/>

1) 이분탐색으로 푸는 방법 <br/><br/>

**핵심 로직**
---

dp의 마지막 요소보다 넣고자 하는 배열의 요소가 더 작을때마다 이분 탐색을 통해 배열의 요소를 대입해준다.

```java
else {
    idx = binarySearch(0, len, stones[i]);
    dp[idx] = stones[i];
}
```

위의 코드가 성립하는 이유는 배열의 첫 네 원소가 [5,6,1,2]일 때 길이가 2인 증가 부분수열은 [5,6]과 [1,2]이 있다. 그 다음 수가 무엇이 될지는 모르겠지만 작은 정보를 유지할 때는 LIS를 문제없이 구할 수 있다. 

가령 원 수열의 바로 뒤의 수이자 마지막 수가 3일 때나 11111일 때 모두 [1,2]는 LIS를 만들어낼 수 있다. ([1,2,3],[1,2,11111]) 하지만 [5,6]은 3일 때 LIS를 이어가지 못한다.

즉, 배열의 요소가 dp값 보다 작거나 같다면 배열의 결과 자체는 원하는 결과가 나오지 않지만 배열의 개수는 구하고자 하는 개수와 같을 것이다.

1. dp의 배열의 길이는 대체를 하더라도 커지지 않는다. 
    1. → len의 크기가 변하지 않은 상태에서 dp에 값을 넣어주는 것 이기 때문에 문제가 없다.
2. 요소가 대체 되고난 후 dp의 마지막 요소보다 큰 값이 나와서 추가되더라도 이전 요소보다 뒤에 있는 요소라서 문제가 없다. <br/><br/>

### 최종 결과 (93 ms)

```java
import java.io.*;
import java.util.*;

public class Main {

    static int[] dp;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] stones = new int[n];
        dp = new int[n + 1];
        int len = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            stones[i] = Integer.parseInt(st.nextToken());
        }

        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (stones[i] > dp[len]) {
                dp[++len] = stones[i];
            } else {
                idx = binarySearch(0, len, stones[i]);
                dp[idx] = stones[i];
            }
        }

        System.out.println(len);

        br.close();
    }

    private static int binarySearch(int left, int right, int target) {
        int mid = 0;
        
        while (left < right) {
            mid = (left + right) / 2;

            if (dp[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
    }
}
```

<br/>

2) DP로 푸는 방법 <br/><br/>

**핵심 로직**

---

DP를 이용한 LIS 찾기

**수열의 한 원소에 대해, 그 원소에서 끝나는 최장 증가 수열**을 생각해보자. 그 최장 증가 수열의 k를 제외한 모든 원소들은 반드시 k보다 작아야 할 것이다.

따라서 **k의 앞 순서에 있는 모든 원소들 중 값이 k보다 작은 원소**에 대해, 그 **각각의 원소에서 끝나는 최장 증가 수열의 길이를 알고 있다면**, k에서 끝나는 최장 증가 수열의 길이도 구할 수 있을 것이다.

(1, 5, 4, 2, 3, 8)의 배열을 예시로 들면 8 이전의 (1, 5, 4, 2, 3)까지의 수열 중 각각의 원소에서 끝나는 최장 증가 수열의 길이는 다음과 같다.

- 1에서 끝나는 LIS 길이 : 1 (1)

- 5에서 끝나는 LIS 길이 : 2 (1, 5)

- 4에서 끝나는 LIS 길이 : 2 (1, 4)

- 2에서 끝나는 LIS 길이 : 2 (1, 2)

- 3에서 끝나는 LIS 길이 : 3 (1, 2, 3)

이들 중 가장 긴 (1, 2, 3)에 현재 수 8을 더한 **(1, 2, 3, 8)이 8에서 끝나는 최장 증가 수열**이 된다. 이러한 LIS 구하는 방법을 활용하여 구할 수 있다. <br/><br/>

### 최종 결과 (최대 129ms)

```java
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] stones = new int[n];
        int[] dp = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            stones[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (stones[i] > stones[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, dp[i]);
        }
        
        System.out.println(max + 1);

        br.close();
    }
}
```
