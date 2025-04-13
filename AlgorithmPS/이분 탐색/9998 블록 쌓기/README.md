# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/13.svg" width="30"> [20366] 같이 눈사람 만들래? <br/><br/>

📌 → https://www.acmicpc.net/problem/9998 <br/><br/>

**핵심 로직**

1. `BufferedReader`를 통해 입력 데이터를 읽어들이고, `n`과 `blockD`, `blockY` 배열을 초기화한다. 입력은 파일에서 읽으며, 두 줄로 이루어진 숫자 데이터를 `StringTokenizer`를 사용해 파싱하여 `blockD`와 `blockY` 배열에 저장한다.
2. `binarySearch` 메서드를 호출하여 최소 블록 이동 횟수를 계산하는 과정을 시작한다. 이진 탐색 범위는 0부터 10^{12} - n/2 까지로 설정되며, 초기 `minBlockCount`와 `maxBlockCount`를 계산한다. ( V자 형태이고 인접한 블록이 한 개씩만 차이가 나야하기 때문에 가운데 열에 올 수 있는 최대의 높이가 위와 같이 된다. )
3. 이진 탐색 루프에서 `mid` 값을 계산하고, `countBlock` 메서드를 호출해 `mid` 값을 기준으로 블록 이동 횟수를 비교한다. `minBlockCount`와 `maxBlockCount` 값을 바탕으로 `left`와 `right` 범위를 조정한다.
4. 탐색이 종료된 후 `maxBlockCount` 값을 출력한다. 이는 블록 배열을 특정 목표 높이로 정렬하는 데 필요한 최소 총 블록 이동 횟수를 의미한다.
5. `countBlock` 메서드는 주어진 목표 높이 `target`에 대해 블록들을 정렬하는 데 필요한 이동 횟수를 계산한다. 중앙값을 기준으로 왼쪽과 오른쪽 배열을 각각 `target`부터 증가하는 높이에 맞춘다. 이 과정에서 `Math.abs`를 사용해 각 블록의 이동 횟수를 합산한다.
6. 전체 코드의 구조는 이진 탐색을 통해 정렬 목표 높이를 효율적으로 찾고, `countBlock`으로 이동 비용을 계산하여 최적의 결과를 도출하는 방식으로 설계되어 있다. <br/><br/>

```
5
2 3 0 1 4
3 3 2 3 1
```

### 최종 결과 (536 ms)

```java
import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static long[] blockD;
    static long[] blockY;
    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        n = Integer.parseInt(br.readLine());
        blockD = new long[n];
        blockY = new long[n];

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            blockD[i] = Long.parseLong(st1.nextToken());
            blockY[i] = Long.parseLong(st2.nextToken());
        }

        binarySearch();
        
        br.close(); 
    }

    private static void binarySearch() {

        long left = 0;
        long right = (long) Math.pow(10, 12) - (n / 2);
        long minBlockCount = countBlock(left);
        long maxBlockCount = countBlock(right);

        while (left < right) {

            long mid = (left + right) / 2;

            if (minBlockCount < maxBlockCount) {
                right = mid;
                maxBlockCount = countBlock(right);
            } else {
                left = mid + 1;
                minBlockCount = countBlock(left);
            }
        }

        System.out.println(maxBlockCount);
    }

    private static long countBlock(long target) {

        long totalBlock = 0;
        long h = target;
        for (int i = n / 2; i >= 0; i--) {
            totalBlock += Math.abs(blockD[i] - h);
            totalBlock += Math.abs(blockY[i] - h);
            h++;
        }

        h = target + 1;
        for (int i = n / 2 + 1; i < n; i++) {
            totalBlock += Math.abs(blockD[i] - h);
            totalBlock += Math.abs(blockY[i] - h);
            h++;
        }

        return totalBlock;
    }
}
```
