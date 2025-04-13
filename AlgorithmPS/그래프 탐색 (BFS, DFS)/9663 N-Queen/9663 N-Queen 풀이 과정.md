📌 → https://www.acmicpc.net/problem/9663 <br/><br/>

**핵심 로직**

1. 같은 행에 퀸을 둘 수 없으니 행마다 퀸을 둔다고 생각해야 한다.
2. `backTracking(1)` 은 0번째 행에 퀸을 뒀기때문에 다음 행인 첫번째 행부터 퀸의 위치를 생각해야 하기 때문에 depth1을 넣는다. 
3. `isValid` 의 경우는 행에 대해서는 검사했으니 열과 오른쪽 위 대각선, 왼쪽 위 대각선을 검사하기 위한 메서드이고 for문은 모든 열을 검사하기 위한 반복문을 의미한다 <br/><br/>

```
8
```

### 최종 결과 (7076 ms)

```java
import java.io.*;
import java.util.*;

public class Main {

    static int n, res;
    static int[][] chessMap;
    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        n = Integer.parseInt(br.readLine());
        chessMap = new int[n][n];
        res = 0;

        for (int i = 0; i < n; i++) {
            chessMap[0][i] = 1;
            backTracking(1);
            chessMap[0][i] = 0;
        }

        System.out.println(res);

        br.close();
    }

    private static void backTracking(int depth) {

        if (depth == n) {
            res++;
            return;
        }

        for (int i = 0; i < n; i++) {
            if (isValid(depth, i)) {
                chessMap[depth][i] = 1;
                backTracking(depth + 1);
                chessMap[depth][i] = 0;
            }
        }
    }

    private static boolean isValid(int depth, int col) {

        // 세로 방향 검사 -> 4행으로 예를 들면, 0번 행 부터 3번행 까지 4번째 행에서 두고자 하는 곳의 세로 방향에 위치하는지 확인하는 로직
        for (int i = 0; i < depth; i++) {
            if (chessMap[i][col] == 1) return false;
        }

        // \ 방향 검사
        int px = depth - 1;
        int py = col - 1;
        while (px >= 0 && py >= 0) {
            if (chessMap[px--][py--] == 1) {
                return false;
            }
        }

        // / 방향 검사
        px = depth - 1;
        py = col + 1;
        while (px >= 0 && py < n) {
            if (chessMap[px--][py++] == 1)
                return false;
        }

        return true;
    }
}

```

### 다른 풀이 (5256 ms)

```java
import java.io.*;
import java.util.*;

public class Main {

    static int n, res;
    static int[] chessMap;
    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        n = Integer.parseInt(br.readLine());
        chessMap = new int[n];
        res = 0;

        backTracking(0);

        System.out.println(res);

        br.close();
    }

    private static void backTracking(int depth) {

        if (depth == n) {
            res++;
            return;
        }

        for (int i = 0; i < n; i++) {
            chessMap[depth] = i;
            if (possible(depth)) {
                backTracking(depth + 1);
            }
        }
    }

    private static boolean possible(int row) {

        for (int i = 0; i < row; i++) {
            if (chessMap[row] == chessMap[i]) return false;
            if (Math.abs(row - i) == Math.abs(chessMap[row] - chessMap[i])) return false;
        }

        return true;
    }
}

```
