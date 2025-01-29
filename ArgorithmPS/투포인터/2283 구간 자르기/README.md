# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/14.svg" width="30"> [2238] 구간 자르기

📌 → https://www.acmicpc.net/problem/2283 <br/><br/>

**주의할 점**

- sum == k 가 되어서 a와 b의 값이 나오더라도 a가 최솟값과 같다면 a가 0일때가 되어야 a가 가장 작을 수 있다. <br/><br/>

**핵심 로직**

- 주어진 구간 데이터를 이용해 합이 k가 되는 최소 범위를 찾는다.
- 프로그램은 입력 데이터를 처리하여 구간 정보를 저장하고, 이를 기반으로 최적의 구간을 찾는 알고리즘을 실행한다.
    1. 첫 번째 줄에서 n(구간의 개수)과 k(목표 합)를 읽어온다.
    2. 이후 각 구간의 시작점과 끝점을 입력받아 구간별 변화량을 기록한다.
- 누적 합을 이용해 각 위치의 값을 계산한다.
    1. 구간 변화량을 기록한 boards 배열을 사용하여, 특정 지점까지의 누적 합을 구한다.
    2. 최소 구간 시작점(minValue)과 최대 구간 끝점(maxValue)을 찾는다.
- 두 개의 포인터를 사용하여 합이 k가 되는 최소 범위를 찾는다.
    1. 시작점(start)과 끝점(end)을 minValue로 설정하고, 현재 합(sum)을 0으로 초기화한다.
    2. sum이 k보다 작으면 end를 증가시키며 누적 합을 더한다.
    3. sum이 k와 같으면 해당 범위를 a, b로 저장하고 탐색을 종료한다.
    4. sum이 k보다 크면 start를 증가시키며 누적 합을 줄인다.
- 만약 찾은 구간의 시작점 a가 minValue와 같다면, a를 0으로 조정한다.
- 최종적으로 찾은 구간 a와 b를 출력한다. <br/><br/>

```
4 25
0 10
3 15
0 8
3 10
```

### 최종 결과 ( 164 ms )

```java
import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br;
    static int n, k, minValue, maxValue, a, b;
    static int[] boards;
    public static void main(String[] args) throws Exception {

//      br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        minValue = Integer.MAX_VALUE;
        maxValue = Integer.MIN_VALUE;
        a = 0;
        b = 0;

        prefixSum();
        twoPointer();

        if (a == minValue) a = 0;

        System.out.println(a + " " + b);

        br.close();
    }

    private static void prefixSum() throws Exception {

        boards = new int[1000001];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            boards[left]++;
            boards[right]--;

            minValue = Math.min(minValue, left);
            maxValue = Math.max(maxValue, right);
        }

        for (int i = minValue + 1; i <= maxValue; i++) {
            boards[i] += boards[i - 1];
        }
    }

    private static void twoPointer() {

        int start = minValue;
        int end = minValue;
        int sum = 0;
        while (end <= maxValue) {

            if (sum < k) {
                sum += boards[end++];
            } else if (sum == k) {
                a = start;
                b = end;
                break;
            } else {
                sum -= boards[start++];
            }
        }
    }
}
```
