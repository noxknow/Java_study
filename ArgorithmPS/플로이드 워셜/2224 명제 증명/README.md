# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30"> [2224] 명제 증명

📌 → https://www.acmicpc.net/problem/2224 <br/><br/>

**핵심 로직**

1. 영문자 대문자와 소문자의 개수 52개와 둘 사이의 공백 6개를 포함한 총 58개의 크기를 갖는 2차원 배열을 만들어준다.
2. 이때, 전건과 후건이 같은 경우는 0으로 처리하고 다른 배열은 1e9의 값으로 초기화해준다.
3. 입력받은 값에 대해 2차원 배열에 대입해주고 이때, 전건과 후건은 순서가 있기 때문에 단방향으로만 값을 넣어준다.
4. 플로이드-워셜 방식을 활용해서 모든 정점쌍간의 최단 경로를 구한 후 그 중 1e9와 0이 아닌 부분만 validRelationships 리스트에 넣어준다.
5. 출력 양식에 따라 validRelationships 의 size와 문자열을 출력한다. <br/><br/>

```
2
A => b
b => C
```

### 최종 결과 (340 ms)

```java
import java.io.*;
import java.util.*;

public class Main {

    static int x;
    static int[][] relationships;
    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        x = Integer.parseInt(br.readLine());
        relationships = new int[58][58];
        for (int i = 0; i < 58; i++) {
            for (int j = 0; j < 58; j++) {
                relationships[i][j] = (int) 1e9;

                if (i == j) {
                    relationships[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < x; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " => ");
            int p = convertChar(st.nextToken().charAt(0));
            int q = convertChar(st.nextToken().charAt(0));

            if (p != q) {
                relationships[p][q] = 1;
            }
        }

        for (int k = 0; k < 58; k++) {
            for (int i = 0; i < 58; i++) {
                for (int j = 0; j < 58; j++) {
                    relationships[i][j] = Math.min(relationships[i][j], relationships[i][k] + relationships[k][j]);
                }
            }
        }

        List<String> validRelationships = new ArrayList<>();
        for (int i = 0; i < 58; i++) {
            for (int j = 0; j < 58; j++) {
                if (relationships[i][j] != (int) 1e9 && relationships[i][j] != 0) {
                    char a = (char) (i + 'A');
                    char b = (char) (j + 'A');
                    validRelationships.add(a + " => " + b);
                }
            }
        }

        System.out.println(validRelationships.size());

        for (String relationship : validRelationships) {
            System.out.println(relationship);
        }

        br.close();
    }

    private static int convertChar(char c) {
        if (c >= 'A' && c <= 'Z') {
            return c - 'A';
        } else {
            return c - 'a' + 32;
        }
    }
}
```
