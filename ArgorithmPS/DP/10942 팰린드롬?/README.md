# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/12.svg" width="30"> [10942] 팰린드롬? 

📌 → https://www.acmicpc.net/problem/10942 <br/><br/>

**핵심 로직**

1. 수열의 인덱스 중 S와 E에서 탐색을 시작한다.
2. palindromes[S] == palindromes[E]이 true이면 , S+1, E-1의 탐색을 시작한다.
3. (E-S+1)이 홀수인 경우 S==E, 짝수인 경우 S>E가 되면 탐색 종료한다. <br/><br/>

dp는 메모이제이션을 위해 사용되며 num[S] == num[E]이 true이면 dp[S][E]에 값을 저장함으로써 한 번 탐색한 것은 두 번 또 시키지않게 탐색 시간을 줄여준다. <br/><br/>

첫 번째, 재귀함수를 사용하여 구현하기
두 번째, 반복문을 사용하여 구현하기 <br/><br/>

```
7
1 2 1 3 1 2 1
4
1 3
2 5
3 3
5 7
```

### 최종 결과 (892 ms)

```java
import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static int[] palindromes;
    static int[][] dp;
    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        n = Integer.parseInt(br.readLine());
        palindromes = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            palindromes[i] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;

            boolean flag = checkPalindrome(s, e) == 1;

            if (flag) {
                sb.append("1\n");
            } else {
                sb.append("0\n");
            }
        }

        System.out.println(sb);

        br.close();
    }

    private static int checkPalindrome(int s, int e) {

        if (s >= e) return 1;

        if (dp[s][e] != -1) return dp[s][e];

        if (palindromes[s] == palindromes[e]) return dp[s][e] = checkPalindrome(s + 1, e - 1);

        return 0;
    }
}
```

### 반복문 풀이 ( 788 ms )

```java
import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

public class Main {

	static int[] num;
	static boolean[][] dp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
	
		num = new int[n+1];
		dp = new boolean[n+1][n+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<n+1; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		for(int i = 1; i <= n; i++){
			dp[i][i] = true;
		}
		
		for(int i=1; i<=n-1; i++) {
			if(num[i] == num[i+1]) dp[i][i+1] =true;
		}
		checkPalin(n);
		
		int t = Integer.parseInt(br.readLine());
		for(int i=0; i<t; i++) {
		 st = new StringTokenizer(br.readLine());
		 int start = Integer.parseInt(st.nextToken());
		 int end = Integer.parseInt(st.nextToken());
		 
		 if(dp[start][end]) {
			sb.append("1\n");
		 }
		 else {
			 sb.append("0\n");
		 }
		}
		 
		 System.out.println(sb);
	}
	
	static void checkPalin(int n) {
	
		for(int i=2; i<n; i++) {
			for(int j=1; j<=n-i; j++) {
				if(num[j] == num[j+i] && dp[j+1][j+i-1]) {
					dp[j][j+i] = true;
				}
			}
		}
		
	}
}
```
