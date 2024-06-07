# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30"> [13904] 과제

📌 → https://www.acmicpc.net/problem/13904 <br/><br/>

핵심 로직

1. 마감기한이 가장 긴 날짜부터 거꾸로 탐색한다면 매 선택마다 최적의 해를 고를 수 있다.
2. 날짜와 점수를 저장하는 Scroe 클래스를 만들고 Score 타입을 리스트를 통해 저장한다.
3. 마감기한이 가장 긴 날짜부터 차레로 탐색을 시작하고 가능한 큰 점수를 계속해서 res에 더해준다.
    1. 이때, 모든 탐색이 완료된 후 list에서 사용했던 점수와 날짜는 제거해준다. <br/><br/>

```
7
4 60
4 40
1 20
2 50
3 30
4 10
6 5
```

### 최종 결과 (184 ms)

```java
import java.io.*;
import java.util.*;

public class Main {

    static class Score {

        int d, w;

        Score(int d, int w) {
            this.d = d;
            this.w = w;
        }
    }

    static int n, d, w, maxDay, res;
    static List<Score> scores;
    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        n = Integer.parseInt(br.readLine());
        maxDay = Integer.MIN_VALUE;

        scores = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            d = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            scores.add(new Score(d, w));
            maxDay = Math.max(maxDay, d);
        }

        maxScore();

        System.out.println(res);

        br.close();
    }

    private static void maxScore() {

        for (int i = maxDay; i >= 1; i--) {
            Score ans = new Score(0, 0);

            for (Score score : scores) {
                if (score.d >= i) {
                    if (ans.w < score.w) {
                        ans = score;
                    }
                }
            }

            res += ans.w;
            scores.remove(ans);
        }
    }
}
```
