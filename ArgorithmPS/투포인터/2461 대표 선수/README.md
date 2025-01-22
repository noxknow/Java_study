# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/14.svg" width="30"> [2461] 대표 선수

📌 → https://www.acmicpc.net/problem/2461 <br/><br/>

**핵심 로직**

- 학생들의 성적 데이터를 이용해 주어진 조건을 만족하는 최소 점수 차이를 계산한다.
- 프로그램은 입력 데이터를 처리하여 학생들의 성적을 저장하고, 이를 기반으로 최소 점수 차이를 찾는 알고리즘을 실행한다.
    1. 첫 번째 줄에서 `n`(학생의 수)과 `m`(각 학생의 점수 개수)을 읽어온다.
    2. 이후 학생들의 성적 데이터를 2차원 배열 `students`에 저장한다.
- 각 학생의 점수를 오름차순으로 정렬한 뒤, 모든 학생의 현재 최소 점수를 추적하기 위해 `indexes` 배열을 초기화한다.
- 최소 점수 차이를 계산하기 위해 아래의 단계를 반복한다.
    1. 현재 각 학생의 최소 점수 중 가장 작은 값(`minScore`)과 가장 큰 값(`maxScore`)을 찾는다.
    2. `maxScore - minScore`가 현재 저장된 최소 점수 차이(`res`)보다 작으면 `res`를 갱신한다.
    3. `minScore`를 제공하는 학생의 점수를 다음 점수로 갱신하기 위해 해당 학생의 `indexes` 값을 증가시킨다.
    4. 만약 특정 학생의 점수가 모두 처리되었다면 반복을 종료한다.
- 계산된 최소 점수 차이를 출력한다. <br/><br/>

```
3 4
12 16 67 43
7 17 68 48
14 15 77 54
```

### 최종 결과 ( 2680 ms )

```java
import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br;
    static int n, m, res;
    static int[] indexes;
    static int[][] students;
    public static void main(String[] args) throws Exception {

//      br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        res = Integer.MAX_VALUE;

        fillBoard();
        makeIndexArray();
        selectPlayer();

        System.out.println(res);

        br.close();
    }

    private static void fillBoard() throws Exception {

        students = new int[n][m];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                students[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void makeIndexArray() {

        indexes = new int[n];
        for (int i = 0; i < n; i++) {
            Arrays.sort(students[i]);
            indexes[i] = 0;
        }
    }

    private static void selectPlayer() {

        while (true) {

            int minScore = students[0][indexes[0]];
            int maxScore = students[0][indexes[0]];
            int minIdx = 0;

            for (int i = 1; i < n; i++) {
                if (minScore > students[i][indexes[i]]) {
                    minScore = students[i][indexes[i]];
                    minIdx = i;
                }

                if (maxScore < students[i][indexes[i]]) {
                    maxScore = students[i][indexes[i]];
                }
            }

            if (maxScore - minScore < res) {
                res = maxScore - minScore;
            }

            if (++indexes[minIdx] >= m) break;
        }
    }
}
```
