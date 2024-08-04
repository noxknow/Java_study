# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/13.svg" width="30"> [2473] 세 용액 

📌 → https://www.acmicpc.net/problem/2473 <br/><br/>

**핵심 로직**

1. 투포인터를 사용하면서 좁혀나간다. 
    a. 이때, Long으로 값 받는 것 주의
2. left의 경우는 for문을 활용해 하나씩 늘려가고 mid와 right을 투포인터로 해결한다. <br/><br/>

```
5
-2 6 -97 -6 98
```

### 최종 결과 ( 223 ms )

```java
import java.util.*;
import java.io.*;

public class Main {

    static int n, ansL, ansM, ansR;
    static long minValue = Long.MAX_VALUE;
    static long[] numbers;
    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        n = Integer.parseInt(br.readLine());

        numbers = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(numbers);

        for (int i = 0; i < n - 2; i++) {
            int left = i;
            int mid = i + 1;
            int right = n - 1;

            while (mid < right) {
                long sum = numbers[left] + numbers[mid] + numbers[right];

                if (minValue > Math.abs(numbers[left] + numbers[mid] + numbers[right])) {
                    minValue = Math.abs(numbers[left] + numbers[mid] + numbers[right]);
                    ansL = left;
                    ansM = mid;
                    ansR = right;
                }

                if (sum >= 0) {
                    right--;
                } else {
                    mid++;
                }
            }
        }

        System.out.println(numbers[ansL] + " " + numbers[ansM] + " " + numbers[ansR]);

        br.close();
    }
}
```
