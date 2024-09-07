# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/11.svg" width="30"> [1079] 마피아 

📌 → https://www.acmicpc.net/problem/1079 <br/><br/>

**핵심 로직**

1. 게임에 참여하는 모든 사람의 수 n만큼 `guilty` 배열과 `isDead` 배열을 만들어 각 참가자의 유죄 지수와 생존 여부를 관리합니다.
2. 참가자 간의 반응을 나타내는 2차원 배열 R을 입력받아 저장합니다.
3. 은진이(마지막 마피아)의 번호를 `number` 변수에 저장합니다.
4. DFS를 사용하여 게임의 모든 가능한 상황을 탐색합니다:
    - 밤일 때(참가자가 짝수 명 남았을 때):
        - 은진이를 제외한 모든 살아있는 참가자를 한 명씩 죽이는 경우를 시도합니다.
        - 참가자를 죽일 때마다 `resizeGuilty` 함수를 사용하여 참가자가 죽었을 때 다른 참가자들의 유죄 지수를 조정합니다.
    - 낮일 때(참가자가 홀수 명 남았을 때):
        - 유죄 지수가 가장 높은 살아있는 참가자를 찾아 죽입니다.
5. 게임 종료 조건(은진이가 죽거나 1명만 남았을 때)에 도달하면, 진행된 밤의 횟수를 `res`와 비교하여 최대값을 저장합니다.
6. 모든 가능한 경우를 탐색한 후, 최대 진행 가능한 밤의 횟수 `res`를 출력합니다. <br/><br/>

```
4
500 500 500 500
1 4 3 -2
-2 1 4 3
3 -2 1 4
4 3 -2 1
1
```

### 최종 결과 (560 ms)

```java
import java.util.*;
import java.io.*;

public class Main {

    static int n, number, res;
    static int[] guilty;
    static boolean[] isDead;
    static int[][] R;
    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        n = Integer.parseInt(br.readLine());

        guilty = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            guilty[i] = Integer.parseInt(st.nextToken());
        }

        R = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                R[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        isDead = new boolean[n];
        number = Integer.parseInt(br.readLine());

        int len = guilty.length;

        dfs(len, 0);

        System.out.println(res);

        br.close();
    }

    private static void dfs(int len, int cnt) {

        if (isDead[number] || len == 1) {
            res = Math.max(res, cnt);
            return;
        }

        if (len % 2 == 0) {
            for (int i = 0; i < n; i++) {
                if (!isDead[i] && i != number) {
                    isDead[i] = true;
                    resizeGuilty(0, i);
                    dfs(len - 1, cnt + 1);
                    resizeGuilty(1, i);
                    isDead[i] = false;
                }
            }
        } else {
            int maxGuilty = Integer.MIN_VALUE;
            int idx = 0;

            for (int i = 0; i < n; i++) {
                if (!isDead[i] && maxGuilty < guilty[i]) {
                    maxGuilty = guilty[i];
                    idx = i;
                }
            }

            isDead[idx] = true;
            dfs(len - 1, cnt);
            isDead[idx] = false;
        }
    }

    private static void resizeGuilty(int type, int idx) {

        if (type == 0) {
            for (int i = 0; i < n; i++) {
                if (!isDead[i]) {
                    guilty[i] += R[idx][i];
                }
            }
        } else {
            for (int i = 0; i < n; i++) {
                if (!isDead[i]) {
                    guilty[i] -= R[idx][i];
                }
            }
        }
    }
}
```
