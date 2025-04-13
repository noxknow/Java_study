# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30"> [17406] 배열 돌리기 4

📌 → https://www.acmicpc.net/problem/17406 <br/><br/>

**주의할 점**

1. 내부에 있는 배열도 회전해야 하기 때문에 s 값을 반복문을 통해 바꿔줘야 한다. <br/><br/>

**핵심 로직**

1. 회전 연산의 순서를 임의로 변경해야 하기 때문에 회전 연산 값들을 저장하고 있는 배열을 만들어준다.
2. permutation 함수를 만들어 임의의 순서로 회전연산이 실행될 수 있도록 한다.
    1. 이때, 회전연산의 순서가 다 정해진다면 배열을 회전시키고 다른 순서로 다시 회전할 수 있도록 return 해준다.
3. 회전의 경우 외부와 내부가 모두 회전할 수 있도록 반복문을 사용하고, 사라지는 값들을 저장해두면서 한칸씩 회전시켜 빈자리에 저장해둔 사라지는 값을 대입해준다.
4. 결과값을 구하기 위해 각 행의 합을 구하고 가장 작은 값이 res에 저장될 수 있도록 한다.
5. res 값을 출력해준다. <br/><br/>

```
5 6 2
1 2 3 2 5 6
3 8 7 2 1 3
8 2 3 1 4 5
3 4 5 1 1 1
9 3 2 1 4 3
3 4 2
4 2 1
```

### 최종 결과 (364 ms)

```java
import java.io.*;
import java.util.*;

public class Main {

    static int n, m, k;
    static int res = Integer.MAX_VALUE;
    static int[] arr;
    static boolean[] visited;
    static int[][] arrayMap;
    static int[][] rotation;
    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arrayMap = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arrayMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        rotation = new int[k][3];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());

            rotation[i][0] = Integer.parseInt(st.nextToken()) - 1;
            rotation[i][1] = Integer.parseInt(st.nextToken()) - 1;
            rotation[i][2] = Integer.parseInt(st.nextToken());
        }

        arr = new int[k];
        visited = new boolean[k];
        permutation(0);

        System.out.println(res);

        br.close();
    }

    private static void permutation(int idx) {
        if (idx == k) {
            rotate();
            return;
        }

        for (int i = 0; i < k; i++) {
            if (!visited[i]) {
                visited[i] = true;
                arr[idx] = i; // 회전 연산의 순서를 위한 배열
                permutation(idx + 1);
                visited[i] = false;
            }
        }
    }

    private static void rotate() {
        int[][] tmp = copyMap();

        for (int i = 0; i < k; i++) {
            int r = rotation[arr[i]][0];
            int c = rotation[arr[i]][1];
            int S = rotation[arr[i]][2];

            for (int s = 1; s <= S; s++) { // 내부에 있는 사각형의 회전
                // 위쪽
                int upTmp = tmp[r - s][c + s];
                for (int y = c + s; y > c - s; y--) {
                    tmp[r - s][y] = tmp[r - s][y - 1];
                }

                // 오른쪽
                int rightTmp = tmp[r + s][c + s];
                for (int x = r + s; x > r - s; x--) {
                    tmp[x][c + s] = tmp[x - 1][c + s];
                }
                tmp[r - s + 1][c + s] = upTmp;

                //아래
                int leftTmp = tmp[r + s][c - s];
                for (int y = c - s; y < c + s; y++) {
                    tmp[r + s][y] = tmp[r + s][y + 1];
                }
                tmp[r + s][c + s - 1] = rightTmp;

                //왼쪽
                for (int x = r - s; x < r + s; x++) {
                    tmp[x][c - s] = tmp[x + 1][c - s];
                }
                tmp[r + s - 1][c - s] = leftTmp;
            }
        }

        getResult(tmp);
    }

    private static int[][] copyMap() {
        int[][] tmp = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                tmp[i][j] = arrayMap[i][j];
            }
        }
        
        return tmp;
    }

    private static void getResult(int[][] tmp) {

        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < m; j++) {
                sum += tmp[i][j];
            }

            res = Math.min(res, sum);
        }
    }
}
```
