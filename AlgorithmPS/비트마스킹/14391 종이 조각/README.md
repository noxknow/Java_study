# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/13.svg" width="30"> [14391] 종이 조각

📌 → https://www.acmicpc.net/problem/14391 <br/><br/>

**핵심 로직**

- 주어진 문제에서는 가능한 비트 마스크를 사용해 종이를 가로와 세로로 자르는 방법을 모두 탐색한다.
- `bitMask`는 2차원 배열의 각 원소에 대해 가로 또는 세로로 찢는 여부를 나타내는 이진수로, 모든 가능한 경우를 확인하기 위해 `1 << (rows * cols)` 크기만큼 순차적으로 탐색한다.
- `bitMask`의 비트 값이 0일 경우, 해당 칸은 가로로 찢은 것이므로, 가로 방향으로 숫자를 이어붙인다. 그렇지 않으면 그 전에 이어붙였던 숫자를 더하고 `num`을 초기화하여 새로운 숫자를 만들 준비를 한다.
- 세로 방향으로도 마찬가지로, `bitMask`의 비트 값이 1일 경우, 해당 칸은 세로로 찢은 것이므로 세로 방향으로 숫자를 이어붙인다. 세로 방향에서도 마찬가지로, 숫자를 이어붙이거나 더하고 `num`을 초기화하여 새로운 숫자를 만들 준비를 한다.
- 가로와 세로에서 나온 모든 숫자들을 더한 후, 현재 `bitMask`에 해당하는 경우에서 나온 합을 구하고, 그 합이 최대값보다 크면 최대값을 갱신한다.
- 모든 가능한 `bitMask` 값을 다 탐색하여 최댓값을 구한 후, 이를 반환한다. <br/><br/>

```
2 3
123
312
```

### 최종 결과 (156 ms)

```java
import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static int rows, cols;
    static int[][] papers;
    public static void main(String[] args) throws Exception {

//      br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new FileReader("input.txt"));
        st = new StringTokenizer(br.readLine());

        rows = Integer.parseInt(st.nextToken());
        cols = Integer.parseInt(st.nextToken());

        papers = new int[rows][cols];
        fillPapers();

        System.out.println(getMaxSum());

        br.close();
    }

    private static void fillPapers() throws Exception {

        for (int i = 0; i < rows; i++) {
            String str = br.readLine();
            for (int j = 0; j < cols; j++) {
                papers[i][j] = str.charAt(j) - '0';
            }
        }
    }

    private static int getMaxSum() {

        int maxSum = Integer.MIN_VALUE;
        int bitMastMax = 1 << (rows * cols); //bitmask 의 최대값 + 1 ex) 2 * 2 라면 비트는 0001 0000 즉, 16

        for (int bitMask = 0; bitMask < bitMastMax; bitMask++) {
            
            int sum = 0;
            int num;
            int bitIdx = 1 << (rows * cols - 1);

            // 가로 검사 -> 가로로 0이 쭉 이어져 나오는 경우 sum 에 더해준다.
            // (row * cols) + col => 해당 인덱스의 비트 위치
            for (int row = 0; row < rows; row++) {
                num = 0;
                for (int col = 0; col < cols; col++) {

                    if ((bitMask & (bitIdx >> (row * cols) + col)) == 0) { // 비트가 0이면 -> 해당 숫자는 가로
                        num *= 10;
                        num += papers[row][col];
                    }
                    else {
                        sum += num;
                        num = 0;
                    }
                }

                sum += num;
            }

            // 세로 검사 -> 세로로 1이 쭉 이어져 나오는 경우 sum 에 더해준다.
            for (int col = 0; col < cols; col++) {
                num = 0;
                for (int row = 0; row < rows; row++) {

                    if ((bitMask & (bitIdx >> (row * cols) + col)) != 0) { // 비트가 1이면 -> 해당 숫자는 세로
                        num *= 10;
                        num += papers[row][col];
                    }
                    else {
                        sum += num;
                        num = 0;
                    }
                }

                sum += num;
            }

            maxSum = Math.max(maxSum, sum);
        }

        return maxSum;
    }
}
```
