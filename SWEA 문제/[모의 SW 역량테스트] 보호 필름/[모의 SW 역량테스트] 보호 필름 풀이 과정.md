📌 → https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V1SYKAaUDFAWu <br/><br/>

주의할 점

1. 기존에는 testIssue 메서드에서 모든 열에 대한 dp 배열을 만든 후 모든 열이 합격기준을 만족하는지 검사했지만 결과적으로 시간초과가 나오게 되었다. 이 문제를 해결하기 위해서 각 열의 dp배열을 완성할 때 마다 합격기준을 검사하고 불합격이 나오는 경우 나머지 열에 대한 dp는 만들지않고 false를 return 함으로써 시간을 줄인다.  <br/><br/>

**기존 코드**

```java
 private static void testIssue() {

        dp = new int[w][d];

        for (int i = 0; i < w; i++) {
            dp[i][0] = 1;
            for (int j = 1; j < d ; j++) {
                if (films[j - 1][i] == films[j][i]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - 1] + 1);
                } else {
                    dp[i][j] = 1;
                }
            }
        }
    }
    
private static void backTracking(int depth, int film, int cnt) {

    // 중략

    if (depth == d - 1) {
        testIssue();

        if (calculateTest()) {
            ans = Math.min(ans, cnt);
        }

        return;
    }

    // 중략
}
 
 private static boolean calculateTest() {

        for (int i = 0; i < w; i++) {
            int maxValue = Arrays.stream(dp[i])
                    .max()
                    .getAsInt();

            if (maxValue < k) {
                return false;
            }
        }

        return true;
    }
```

**바뀐 코드**

```java
private static boolean testIssue() {

        dp = new int[w][d];

        for (int i = 0; i < w; i++) {
            Arrays.fill(dp[i], 1);
            for (int j = 1; j < d ; j++) {
                if (films[j - 1][i] == films[j][i]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - 1] + 1);
                }
            }

            int maxValue = Arrays.stream(dp[i])
                    .max()
                    .getAsInt();

            if (maxValue < k) {
                return false;
            }
        }

        return true;
    }
```

<br/>

**핵심 로직**

1. 입력값을 받은 후 각 열에서 가장 길게 연속적으로 이어지는 부분을 testIssue() 메서드를 통해 찾으며 합격기준을 넘는지 검사한다.
    1. 기준을 넘지 못한다면, 특성을 바꾸지 않는 경우, A로 바꾸는 경우, B로 바꾸는 경우에 대해 backTracking을 시작한다.
    2. 기준을 넘는다면 특성을 바꿀 필요가 없기 때문에 0을 출력한다.
2. backTracking 내부에서 행을 늘려가며 특성을 바꾸지 않는 경우는 cnt를 그대로 유지하고 바꾸는 경우는 cnt + 1을 통해 약품 투입 횟수를 증가시킨다.
    1. backTracking 중 return을 했을 때 이전의 배열로 돌아가야 하기 때문에 이전의 배열을 newFilms로 복사하고 return 이후 `films = newFilms` 를 통해 이전의 배열로 돌아온다.
    2. 특성을 바꾸지 않는 경우를 제외하고 특성을 바꾸는 경우엔 바꾼 특성으로 배열을 채운다.
3. backTracking 진행 중 최소값으로 갱신 된 ans보다 큰 횟수만큼 약을 투여한다면 그 경로는 return하도록 한다.
4. 행의 깊이가 마지막까지 도달한다면 testIssue 메서드를 통해 합격기준을 검사하고 통과한다면 결과값을 최소값으로 갱신한다.
5. 모든 경로를 탐색하며 2 ~ 4번을 반복한다. <br/><br/>

```java
1
6 8 3         
0 0 1 0 1 0 0 1
0 1 0 0 0 1 1 1
0 1 1 1 0 0 0 0
1 1 1 1 0 0 0 1
0 1 1 0 1 0 0 1
1 0 1 0 1 1 0 1
```

### 최종 결과 (3950 ms)

```java
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Solution {

    static int d, w, k, ans;
    static int[][] dp;
    static int[][] films;
    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            d = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            films = new int[d][w];
            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    films[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            ans = Integer.MAX_VALUE;

            if (!testIssue()) {
                int[][] newFilms = deepCopy(films);
                backTracking(0, 2, 0);

                films = newFilms;
                backTracking(0, 0, 1);

                films = newFilms;
                backTracking(0, 1, 1);

                System.out.println("#" + tc + " " + ans);
            } else {
                System.out.println("#" + tc + " " + 0);
            }
        }

        br.close();
    }

    private static int[][] deepCopy(int[][] original) {
        int[][] copy = new int[original.length][];

        for (int i = 0; i < original.length; i++) {
            copy[i] = Arrays.copyOf(original[i], original[i].length);
        }

        return copy;
    }

    private static boolean testIssue() {

        dp = new int[w][d];

        for (int i = 0; i < w; i++) {
            Arrays.fill(dp[i], 1);
            for (int j = 1; j < d ; j++) {
                if (films[j - 1][i] == films[j][i]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - 1] + 1);
                }
            }

            int maxValue = Arrays.stream(dp[i])
                    .max()
                    .getAsInt();

            if (maxValue < k) {
                return false;
            }
        }

        return true;
    }

    private static void backTracking(int depth, int film, int cnt) {

        if (cnt >= ans) return;

        if (film != 2) {
            for (int i = 0; i < w; i++) {
                films[depth][i] = film;
            }
        }

        if (depth == d - 1) {
            if (testIssue()) {
                ans = Math.min(ans, cnt);
            }

            return;
        }

        backTracking(depth + 1, 2, cnt);

        int[][] newFilms = deepCopy(films);

        backTracking(depth + 1, 0, cnt + 1);

        films = newFilms;
        newFilms = deepCopy(films);

        backTracking(depth + 1, 1, cnt + 1);

        films = newFilms;
    }
}
```
