# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30"> [2110] 공유기 설치

📌 → https://www.acmicpc.net/problem/2110 <br/><br/>

**핵심 로직**

1. '최대로 가질 수 있는' 최소 거리이기 때문에 Upper Bound 형식
2. 이분 탐색을 위해 배열을 정렬해준다.
3. mid 거리에 대해 설치 가능한 공유기 개수가 c 개수에 못미치면 거리를 좁혀야 하기 때문에 hi 를 줄인다.
4. 설치 가능한 공유기 개수가 M 개수보다 크거나 같으면 거리를 벌리면서 최소거리가 가질 수 있는 최대 거리를 찾아낸다.
5. 현재 탐색하는 집의 위치와 직전에 설치했던 집의 위치간 거리가 최소 거리(distance)보다 크거나 같을 때 공유기 설치 개수를 늘려주고 마지막 설치 위치를 갱신해준다.
6. Upper Bound는 탐색 값을 초과하는 첫 번째 값을 가리키므로, 1을 빼준 값이 조건식을 만족하는 최댓값이 된다. <br/><br/>

```
5 3
1
2
8
4
9
```

### 최종 결과 (264 ms)

```java
import java.util.*;
import java.io.*;

public class Main {

    static int n, c;
    static int[] house;
    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        house = new int[n];
        for (int i = 0; i < n; i++) {
            house[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(house);

        binarySearch();

        br.close();
    }

    private static void binarySearch() {

        int lo = 1;
        int hi = house[n - 1] - house[0] + 1;

        while (lo < hi) {

            int mid = ( lo + hi ) / 2;

            if (countRouter(mid) < c) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        System.out.println(lo - 1);
    }

    private static int countRouter(int dist) {

        int lastLocate = house[0];
        int count = 1;
        for (int i = 1; i < house.length; i++) {

            int locate = house[i];

            if (locate - lastLocate >= dist) {
                count++;
                lastLocate = locate;
            }
        }

        return count;
    }
}
```
