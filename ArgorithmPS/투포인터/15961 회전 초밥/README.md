# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30"> [15961] 회전 초밥

📌 → https://www.acmicpc.net/problem/15961 <br/><br/>

**주의할 점**

**핵심 로직**

- 초밥 벨트에서 k개의 연속된 초밥을 선택할 때, 가장 많은 종류의 초밥을 먹을 수 있는 경우를 계산한다.
- 프로그램은 입력 데이터를 처리하여 초밥 정보를 저장하고, 이를 기반으로 슬라이딩 윈도우 기법을 이용해 최적의 초밥 조합을 찾는다.
    1. 첫 번째 줄에서 n(회전 초밥 접시 수), d(초밥 종류 수), k(연속해서 먹을 접시 수), c(쿠폰 번호)를 읽어온다.
    2. n개의 초밥 정보를 배열 sushiDishes에 저장한 뒤, 회전 초밥의 특성을 고려해 배열을 확장한다.
- 슬라이딩 윈도우를 이용해 최적의 초밥 조합을 찾는다.
    1. k개의 초밥을 초기 선택하여 초밥 종류 수를 계산한다.
    2. 한 칸씩 윈도우를 이동시키면서 첫 번째 초밥을 제외하고, 새로운 초밥을 추가하며 종류 수를 갱신한다.
    3. 각 단계에서 최대 종류 수를 갱신하며, 쿠폰으로 제공되는 초밥을 포함하여 최대값을 계산한다.
- 계산된 최대 초밥 종류 수를 출력한다. <br/><br/>

```
8 30 4 30
7
9
7
30
2
7
9
25
```

### 최종 결과 ( 488 ms )

```java
import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br;
    static int n, d, k, c, res;
    static int[] sushiDishes;
    static int[] eaten;
    public static void main(String[] args) throws Exception {

//      br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        fillDishes();
        slide();

        System.out.println(res);

        br.close();
    }

    private static void fillDishes() throws Exception {

        sushiDishes = new int[n + k - 1];

        for (int i = 0; i < n; i++) {
            sushiDishes[i] = Integer.parseInt(br.readLine());
        }

        for (int i = n; i < n + k - 1; i++) {
            sushiDishes[i] = sushiDishes[i - n];
        }
    }

    private static void slide() {

        initialize();

        int start = 0;
        int cnt = res;
        for (int i = k; i < sushiDishes.length; i++) {

            eaten[sushiDishes[start]]--;

            if (eaten[sushiDishes[start]] == 0) {
                cnt--;
            }

            if (eaten[sushiDishes[i]] == 0) {
                cnt++;
            }

            eaten[sushiDishes[i]]++;
            res = Math.max(res, cnt);
            start++;
        }
    }

    private static void initialize() {

        eaten = new int[d + 1];
        res = 1;
        eaten[c]++;

        for (int i = 0; i < k; i++) {
            if (eaten[sushiDishes[i]] == 0) {
                res++;
            }

            eaten[sushiDishes[i]]++;
        }
    }
}
```
