# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/13.svg" width="30"> [20366] 같이 눈사람 만들래? <br/><br/>

📌 → https://www.acmicpc.net/problem/20366 <br/><br/>

**핵심 로직**

1. 두 스노우맨의 높이 차이가 가장 적은 경우를 찾기 위해 완전탐색과 투포인터를 결합해 접근한다.
2. 외부 반복문에서는 두 스노우맨 중 하나를 구성할 두 눈사람의 크기를 고정한다.
    1. 현재 선택된 두 눈사람 크기를 합산해 `snowMan1`을 구성한다.
3. 내부 투포인터를 이용해 나머지 두 눈사람으로 `snowMan2`를 구성한다.
    1. 투포인터의 시작과 끝은 배열의 처음과 끝에서 시작하며, 고정된 눈사람과 중복되지 않도록 처리한다.
    2. 두 눈사람의 크기 차이를 계산해 `res`를 갱신하고, `snowMan1`과 `snowMan2`의 상대적 크기를 비교해 투포인터의 위치를 조정한다.
        - `snowMan2`가 크다면 `end`를 줄이고, 작다면 `start`를 늘린다.
        - 두 눈사람 크기가 같으면 차이가 0이므로 바로 종료한다.
4. 모든 경우를 탐색한 후 최소값 `res`를 출력한다. <br/><br/>

```
5
3 5 2 5 9
```

### 최종 결과 ( 224 ms )

```java
import java.util.*;
import java.io.*;

public class Main {

    static int n, res;
    static int[] diameters;
    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        n = Integer.parseInt(br.readLine());
        res = Integer.MAX_VALUE;

        diameters = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            diameters[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(diameters);

        twoPointer();

        br.close(); 
    }

    private static void twoPointer() {

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int snowMan1 = diameters[i] + diameters[j];
                int start = 0;
                int end = n - 1;

                while (start < end) {

                    if (start == i || start == j) {
                        start++;
                        continue;
                    }

                    if (end == i || end == j) {
                        end--;
                        continue;
                    }

                    int snowMan2 = diameters[start] + diameters[end];
                    res = Math.min(res, Math.abs(snowMan2 - snowMan1));

                    if (snowMan1 < snowMan2) {
                        end--;
                    } else if (snowMan1 > snowMan2) {
                        start++;
                    } else {
                        System.out.println(0);
                        return;
                    }
                }
            }
        }

        System.out.println(res);
    }
}
```
