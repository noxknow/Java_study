# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/13.svg" width="30"> [2064] IP 주소

📌 → https://www.acmicpc.net/problem/2064 <br/><br/>

**핵심 로직**

- 주어진 문제에서는 입력된 IP 주소들을 통해 공통 네트워크 주소와 서브넷 마스크를 계산한다.
- IP 주소는 4개의 8비트 숫자로 구성되며, 점(`.`)으로 구분된다. 이를 처리하기 위해 `split("\\.")` 메서드를 사용하여 입력된 문자열을 숫자 배열로 분리한다.
- `subNetting()` 메서드는 네트워크 주소와 서브넷 마스크를 계산하는 핵심 로직을 포함한다.
- 먼저 `networkAddress`와 `subnetMask`를 빈 `StringBuilder` 객체로 초기화한다. 이후, 각 IP 주소의 각 8비트 값(즉, 각 옥텟)을 비교한다.
- 각 옥텟에서 `min` 값은 모든 IP 주소들의 해당 옥텟 값에 대해 **AND 연산**을 수행하여 최소값을 계산한다. 반대로 `max` 값은 **OR 연산**을 통해 최대값을 계산한다.
- 네트워크 주소는 각 옥텟의 `min` 값을 그대로 사용하고, 서브넷 마스크는 `255 - (max - min)`을 계산하여 생성한다.
- `flag` 변수는 각 옥텟에서 최소값과 최대값이 다를 경우를 감지하며, 이후 옥텟들은 네트워크 주소와 서브넷 마스크에서 `0`으로 채워진다.
- 마지막으로 생성된 `StringBuilder`에서 마지막 문자(`.`)를 제거하여 형식을 맞춘 후 출력한다. <br/><br/>

```
3
194.85.160.177
194.85.160.183
194.85.160.178
```

### 최종 결과 (152 ms)

```java
import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br;
    static int n;
    static StringBuilder networkAddress, subnetMask;
    static String[][] ips;
    public static void main(String[] args) throws Exception {

//      br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new FileReader("input.txt"));

        // split() 메서드에서 "."은 일반 문자열이 아니라 정규 표현식으로 해석
        // 정규식에서 "."는 아무 문자(newline 제외)를 의미
        // 따라서, 단순히 점(.)을 기준으로 나누려고 했더라도, 예상치 못한 동작을 할 수 있다
        n = Integer.parseInt(br.readLine());
        ips = new String[n][4];
        for (int i = 0; i < n; i++) {
            ips[i] = br.readLine().split("\\.");
        }

        subNetting();
				
				// 마지막 "." 제거
        networkAddress.deleteCharAt(networkAddress.length()-1);
        subnetMask.deleteCharAt(subnetMask.length()-1);

        System.out.print(networkAddress+"\n"+subnetMask);

        br.close();
    }

    private static void subNetting() {

        boolean flag = false;
        networkAddress = new StringBuilder();
        subnetMask = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            int min = Integer.parseInt(ips[0][i]);
            int max = Integer.parseInt(ips[0][i]);

            for (int j = 1; j < n; j++) {
                min = min & (Integer.parseInt(ips[j][i]));
                max = max | (Integer.parseInt(ips[j][i]));
            }

            if (!flag) {
                networkAddress.append(min).append(".");
                subnetMask.append(255 - (max - min)).append(".");
            } else {
                networkAddress.append(0).append(".");
                subnetMask.append(0).append(".");
            }

            if (min != max) flag = true;
        }
    }
}
```
