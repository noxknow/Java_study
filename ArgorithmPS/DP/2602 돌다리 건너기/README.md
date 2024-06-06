# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30"> [2602] 돌다리 건너기

📌 → https://www.acmicpc.net/problem/2602 <br/><br/>

**핵심 로직**

1. 완전탐색을 사용하는 경우 시간이 초과된다. 그렇기 때문에 DP를 사용한다.
2. dp의 각 행의 의미는 dp[현재 밟은 문자][찾는 문자][어떤 돌다리]
    1. ex) G 을 밟고 찾는 문자가 S 이면서, 현재 악마의 돌다리인 경우가 이전에 있었다면 같은 결과가 나오도록 dp를 만들어준다.
3. 그 후, 재귀를 반복해서 결과값을 구한 후 출력한다. <br/><br/>

```
RGS
RINGSR
GRGGNS
```

### 최종 결과 (128 ms)

```java
import java.io.*;
import java.util.*;

public class Main {

    static String target, devil, angel;
    static int[][][] dp;
    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        target = br.readLine();
        devil = br.readLine();
        angel = br.readLine();

        int bridgeLen = devil.length();
        dp = new int[target.length() + 1][bridgeLen + 1][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < bridgeLen; j++) {
                for (int k = 0; k < target.length(); k++) {
                    dp[k][j][i] = -1;
                }
            }
        }

        int dev_start = crossBridge(0,0,0);
        int ang_start = crossBridge(0,0,1);

        System.out.println(dev_start + ang_start);

        br.close();
    }

    private static int crossBridge(int idx, int curStone, int turn) {

        if (idx == target.length()) return 1;
        if (dp[idx][curStone][turn] != -1) return dp[idx][curStone][turn];

        int res = 0;
        if (turn == 0) {
            for (int i = curStone; i < devil.length(); i++) {
                if (devil.charAt(i) == target.charAt(idx)) {
                    res += crossBridge(idx + 1, i + 1, 1);
                }
            }
        } else {
            for (int i = curStone; i < angel.length(); i++) {
                if (angel.charAt(i) == target.charAt(idx)) {
                    res += crossBridge(idx + 1, i + 1, 0);
                }
            }
        }

        return dp[idx][curStone][turn] = res;
    }
}
```

### 시간 초과

```java
import java.io.*;
import java.util.*;

public class Main {

    static int res;
    static String target, devil, angel;
    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        target = br.readLine();
        devil = br.readLine();
        angel = br.readLine();

        crossDevil(0, 0);
        crossAngel(0, 0);

        System.out.println(res);

        br.close();
    }

    private static void crossDevil(int idx, int curStone) {

        if (idx == target.length()) {
            res++;
            return;
        }

        for (int i = curStone; i < devil.length(); i++) {
            if (devil.charAt(i) == target.charAt(idx)) {
                crossAngel(idx + 1, i + 1);
            }
        }
    }

    private static void crossAngel(int idx, int curStone) {

        if (idx == target.length()) {
            res++;
            return;
        }

        for (int i = curStone; i < angel.length(); i++) {
            if (angel.charAt(i) == target.charAt(idx)) {
                crossDevil(idx + 1, i + 1);
            }
        }
    }
}
```
