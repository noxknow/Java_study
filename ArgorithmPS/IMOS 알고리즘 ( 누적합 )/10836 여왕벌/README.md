# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30"> [10836] 여왕

📌 → https://www.acmicpc.net/problem/10836 <br/><br/>

**핵심 로직**

- `graphs` 배열을 생성하고, 모든 원소의 초기값을 1로 설정한다.
    - 이는 `fillGraph()` 메서드에서 `Arrays.fill`을 활용하여 처리한다.
- `n`번 반복문을 수행하며, 각각의 `(zero, one, two)` 입력에 대해 그래프 값을 증가시킨다.
    - `increaseGraph()` 메서드에서 두 방향(좌측 열, 상단 행)에 대해 값을 증가시킨다.
        - 아래에서 위로 올라가며 좌측 열(`graphs[i][0]`)에 대해 먼저 증가시킨다.
        - 이어서 좌측에서 우측으로 진행하며 상단 행(`graphs[0][i]`)을 증가시킨다.
        - 이때, zero 개수만큼은 아무 변화 없이 넘어가고, 이후 one이면 +1, two이면 +2를 누적시킨다.
- 나머지 내부 셀에 대해서는 점화식을 이용하여 값을 채운다.
    - `increaseOther()` 메서드에서 `graphs[i][j] = max(상, 좌상, 좌)` 값을 따르도록 설정한다.
    - 이로써 경로 상 가능한 최대값만을 남기며 값을 구성한다.
- 완성된 `graphs` 배열을 출력한다.
    - `printResult()` 메서드에서는 `StringBuilder`를 사용하여 값을 한 줄씩 출력하며, 각 값은 공백으로 구분되도록 출력한다.
    - 마지막 값 뒤에는 공백이 들어가지 않도록 조건문을 사용하여 처리한다. <br/><br/>

```
2 3
1 1 1
0 3 0
0 0 3
```

### 최종 결과 (2932 ms)

```java
import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br;
    static int m, n;
    static int[][] graphs;
    public static void main(String[] args) throws Exception {

//      br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        graphs = new int[m][m];

        fillGraph();

        for (int i = 0; i < n; i++) {
            StringTokenizer inputs = new StringTokenizer(br.readLine());
            int zero = Integer.parseInt(inputs.nextToken());
            int one = Integer.parseInt(inputs.nextToken());
            int two = Integer.parseInt(inputs.nextToken());

            increaseGraph(zero, one, two);
        }

        increaseOther();
        printResult();

        br.close();
    }

    private static void fillGraph() {

        for (int i = 0; i < m; i++) {
            Arrays.fill(graphs[i], 1);
        }
    }

    private static void increaseGraph(int zero, int one, int two) {

        for (int i = m - 1; i > 0; i--) {
            if (zero != 0) {
                zero--;
            } else if (one != 0) {
                graphs[i][0]++;
                one--;
            } else if (two != 0) {
                graphs[i][0] += 2;
                two--;
            }
        }

        for (int i = 0; i < m; i++) {
            if (zero != 0) {
                zero--;
            } else if (one != 0) {
                graphs[0][i]++;
                one--;
            } else if (two != 0) {
                graphs[0][i] += 2;
                two--;
            }
        }
    }

    private static void increaseOther() {

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < m; j++) {
                graphs[i][j] = Math.max(graphs[i - 1][j], Math.max(graphs[i - 1][j - 1], graphs[i][j - 1]));
            }
        }
    }

    private static void printResult() {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(graphs[i][j]);
                if (j != m - 1) sb.append(" ");
            }

            sb.append("\n");
        }

        System.out.print(sb);
    }
}
```
