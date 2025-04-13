# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/13.svg" width="30"> [1022] 소용돌이 예쁘게 출력하기

📌 → https://www.acmicpc.net/problem/1022 <br/><br/>

**주의할 점**

1. row와 column이 절대값이 5000보다 작다고 하니 최대로 나올수 있는 칸의 수는 10000*10000 1억 이므로 주어지는 시간 2초안에 계산이 가능하다.
    
    1억개 이므로 int배열로 충분하지만 전부 구하기 위해서 1억개의 int공간을 만든다면 메모리를 초과하게 되기 때문에 계산만하고 결과로 보여줄 부분만 map 배열에 넣는다. <br/><br/>
    

**핵심 로직**

1. 결과를 담을 배열을 선언한다.
2. 배열에 값을 채우기 위해 필요한 변수를 선언 후 범위 안에 들어온다면 값을 채운다.
    1. 이때, while문은 배열의 모든 가장자리를 채웠다면 반복문을 벗어나도록 한다.
3. 위 혹은 아래로 이동했을 때 그 행에 들어가는 숫자가 1개씩 늘어가기 때문에 dnum을 통해 이를 표현한다.
4. 상용로그를 활용하여 최댓값의 자릿수를 구한 후 배열의 자릿수와 비교하며 공백을 추가하고 출력한다. <br/><br/>

```
-3 -3 2 0
```

### 최종 결과 (260 ms)

```java
import java.util.*;
import java.io.*;

public class Main {

    static int r1, c1, r2, c2, maxNum;
    static int[][] map;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};
    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r1 = Integer.parseInt(st.nextToken());
        c1 = Integer.parseInt(st.nextToken());
        r2 = Integer.parseInt(st.nextToken());
        c2 = Integer.parseInt(st.nextToken());

        map = new int[r2 - r1 + 1][c2 - c1 + 1];

        fill();
        printResult();

        br.close();
    }

    private static void fill() {

        int x = 0, y = 0, dir = 0;
        int num = 1, dnum = 1, cnt = 0; // dnum은 위 혹은 아래로 이동했을 때 그 행에 들어가는 숫자가 1개씩 늘어가는걸 의미

        while (!isFinished()) {

            if (x >= r1 && x <= r2 && y >= c1 && y <= c2) {
                map[x - r1][y - c1] = num;
            }
            num++;
            cnt++;

            x = x + dx[dir];
            y = y + dy[dir];
            if (cnt == dnum) {
                cnt = 0;
                if (dir == 1 || dir == 3) dnum++;
                dir = (dir + 1) % 4;
            }
        }

        maxNum = num - 1;
    }

    private static void printResult() {

        int maxLen = (int) Math.log10(maxNum), len;

        for (int i = 0; i <= r2 - r1; i++) {
            for (int j = 0; j <= c2 - c1; j++) {
                len = maxLen - (int) Math.log10(map[i][j]);
                for (int k = 0; k < len; k++) {
                    System.out.print(" ");
                }
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean isFinished() {
        return map[0][0] != 0 && map[r2 - r1][0] != 0 && map[0][c2 - c1] != 0 && map[r2 - r1][c2 - c1] != 0;
    }
}
```
