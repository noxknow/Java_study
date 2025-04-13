# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/14.svg" width="30"> [1508] 레이스

📌 → https://www.acmicpc.net/problem/1508 <br/><br/>


**핵심 로직**

- 레이스 트랙 길이 N, 심판 수 M, 가능한 위치 수 K를 입력받고 K개의 위치를 저장한다.
- 이진 탐색을 통해 가장 가까운 두 심판의 거리를 최대화한다. 초기값으로 최소 거리 min을 1, 최대 거리 max를 N으로 설정한다.
- 중간값 mid를 계산하여 심판 배치 가능성을 확인한다.
- 특정 거리 target를 기준으로 심판을 배치한다. 첫 번째 위치에 심판을 배치하고 이후 위치에 대해 target 거리 이상일 때 심판을 배치한다.
- 가장 최근의 유효한 배치 결과를 출력하며, '1'은 심판이 있는 위치, '0'은 없는 위치를 나타낸다. 여러 해가 가능할 경우, 사전순으로 가장 늦는 배치를 선택한다. <br/><br/>

```
1000 5 10
6 9 33 59 100 341 431 444 565 857
```

### 최종 결과 (104 ms)

```java
import java.util.*;
import java.io.*;

public class Main {

    static int n, m, k;
    static int[] positions;
    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        positions = new int[k];
        for (int i = 0; i < k; i++) {
            positions[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(binarySearch());

        br.close();
    }

    private static String binarySearch() {

        String res = "";
        int min = 1;
        int max = n;

        while (min <= max) {

            int mid = (min + max) / 2;
            String ans = possible(mid);

            if (ans.isEmpty()) {
                max = mid - 1;
            } else {
                min = mid + 1;
                res = ans;
            }
        }

        return res;
    }

    private static String possible(int target) {

        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        sb.append(1);
        cnt += 1;
        int before = positions[0];

        for (int i = 1; i < positions.length; i++) {

            if (cnt == m) {
                sb.append(0);
            } else {
                if (positions[i] - before >= target) {
                    sb.append(1);
                    cnt += 1;
                    before = positions[i];
                } else {
                    sb.append(0);
                }
            }
        }

        if (cnt == m) {
            return sb.toString();
        }

        return "";
    }
}
```
