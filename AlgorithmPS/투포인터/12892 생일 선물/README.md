# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30"> [12892] 생일 선물

📌 → https://www.acmicpc.net/problem/12892 <br/><br/>

**주의할 점**

**핵심 로직**

- 프로그램은 입력 데이터를 처리하여 각 정보의 위치(p)와 가치(v)를 저장하고, 이를 기반으로 슬라이딩 윈도우 기법을 이용해 최적의 구간을 찾는다.
    
    1. 첫 번째 줄에서 n(정보의 개수), d(최대 거리 차이)를 읽어온다.
    
    2. n개의 정보(p, v)를 객체로 리스트 `infos`에 저장한 후, 위치(p)를 기준으로 오름차순 정렬한다.
    
- 슬라이딩 윈도우를 이용해 최적의 구간을 찾는다.
    
    1. left와 right 두 포인터를 사용해 윈도우 내 위치 차이가 d 미만인 범위를 유지하며, 해당 구간의 가치(v)의 합을 계산한다.
    
    2. right 포인터를 확장하며 조건에 맞는 범위의 합을 누적하고, 최대값을 계속 갱신한다.
    
    3. 조건을 벗어난 경우 left 포인터를 한 칸 이동시키며 범위를 유지하고, 빠지는 값은 합에서 제거한다.
    
- 계산된 최대 가치의 합을 출력한다. <br/><br/>

```
4 2
13 10
10 20
11 30
12 40
```

### 최종 결과 ( 544 ms )

```java
import java.util.*;
import java.io.*;

public class Main {

    static class Data implements Comparable<Data> {

        int p, v;

        Data(int p, int v) {
            this.p = p;
            this.v = v;
        }

        @Override
        public int compareTo(Data o) {
            return Integer.compare(this.p, o.p);
        }
    }

    static BufferedReader br;
    static int n, d;
    static long res;
    static List<Data> infos;
    public static void main(String[] args) throws Exception {

//      br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        infos = new ArrayList<>();

        fillData();
        slidingWindow();

        System.out.println(res);

        br.close();
    }

    private static void fillData() throws Exception {

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            infos.add(new Data(p, v));
        }

        Collections.sort(infos);
    }

    private static void slidingWindow() {

        int left = 0;
        int right = 0;
        long sum = 0;
        while (true) {

            while (right < n && infos.get(right).p - infos.get(left).p < d) {
                sum += infos.get(right).v;
                right++;
            }

            res = Math.max(res, sum);
            if (right == n) break;

            sum -= infos.get(left).v;
            left++;
        }
    }

```
