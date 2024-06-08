# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30"> [1062] 가르침

📌 → https://www.acmicpc.net/problem/1062 <br/><br/>

**주의할 점**

1. 기존의 코드에서는 입력값으로 주어진 단어에 대해서 방문여부를 조사했기 때문에 시간 초과가 발생하게 되었다. 이 부분을 26개의 글자에 대한 방문체크로 변경하면 시간 초과 문제가 해결된다. <br/><br/>

**핵심 로직**

1. 모든 글자에 대해서 방문여부를 표시하기 위해 visited 배열을 글자의 수 26개만큼 배열을 만들어준다.
2. “anta”와 “tica”의 경우는 모든 단어에 포함되어있는 글자 이기 때문에 visited배열을 true로 만들어준다.
3. k가 5보다 작다면 anta와 tica도 못 만들기 때문에 0을 출력하도록 하고, 5이상이라면 조합을 통해 가능한 글자를 시도한다.
4. 방문배열에 포함되어있지 않은 글자가 단어에 포함되어있다면 boolean을 통해서 정답에 포함할지 체크한다. <br/><br/>

```
3 6
antarctica
antahellotica
antacartica
```

### 최종 결과 (392 ms)

```java
import java.io.*;
import java.util.*;

public class Main {

    static int n, k, res;
    static boolean[] visited = new boolean[26];
    static List<String> strGroup;
    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        res = Integer.MIN_VALUE;

        strGroup = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            strGroup.add(str);
        }

        visited['a' - 'a'] = true;
        visited['n' - 'a'] = true;
        visited['t' - 'a'] = true;
        visited['i' - 'a'] = true;
        visited['c' - 'a'] = true;

        if (k < 5) {
            System.out.println(0);
        } else {
            combination(0, 0);

            System.out.println(res);
        }

        br.close();
    }

    private static void combination(int idx, int cnt) {

        if (cnt + 5 == k) {
            checkStr();
            return;
        }

        for (int i = idx; i < 26; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            combination(i + 1, cnt + 1);
            visited[i] = false;
        }
    }

    private static void checkStr() {
        int localRes = 0;

        for (String str : strGroup) {
            boolean valid = true;

            for (int i = 0; i < str.length(); i++) {
                if (!visited[(str.charAt(i)) - 'a']) {
                    valid = false;
                    break;
                }
            }

            if (valid) localRes++;
        }

        res = Math.max(res, localRes);
    }
}
```

### 시간 초과

```java
import java.io.*;
import java.util.*;

public class Main {

    static int n, k, res;
    static List<String> strGroup;
    static List<Character> words;
    static List<Character> validateWords;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        res = Integer.MIN_VALUE;

        strGroup = new ArrayList<>();
        words = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            strGroup.add(str);

            for (int j = 0; j < str.length(); j++) {
                if (!words.contains(str.charAt(j))) {
                    words.add(str.charAt(j));
                }
            }
        }

        if (k < 5) {
            System.out.println(0);
        } else {
            validateWords = new ArrayList<>();
            combination(0);
            
            System.out.println(res);
        }

        br.close();
    }

    private static void combination(int start) {

        if (validateWords.size() == k) {
            checkStr();
            return;
        }

        for (int i = start; i < words.size(); i++) {
            validateWords.add(words.get(i));
            combination(i + 1);
            validateWords.remove(words.get(i));
        }
    }

    private static void checkStr() {
        int localRes = 0;

        for (String str : strGroup) {
            boolean valid = true;

            for (int i = 0; i < str.length(); i++) {
                if (!validateWords.contains(str.charAt(i))) {
                    valid = false;
                    break;
                }
            }

            if (valid) localRes++;
        }

        res = Math.max(res, localRes);
    }
}
```
