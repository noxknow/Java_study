<aside>
📌 <br/><br/>

```java
import java.util.*;

class Solution {
    
    public int[] solution(int n, long left, long right) {
        
        int[] answer = new int[(int)(right - left) + 1];
        
        for(int i = 0; i < answer.length; i++){
            
            int row = (int)((i + left) / n) + 1; 
            int col = (int)((i + left) % n) + 1; 
            
            answer[i] = Math.max(row, col);
        }
        
        return answer;
    }
}
```
<br/>
→ 내 코드 <br/><br/>

1.메모리초과 -> n이 10^7 이면, n^2 이 int범위를 넘어가서 Overflow

2.String, list ,StringBuilder는 Long타입(left,right)를 지원하지않음

```java
import java.util.*;

class Solution {
    
    public int[] solution(int n, long left, long right) {
        
        int[] answer = {};
        int[][] numList = new int[n][n];
        
        for (int i=0; i<n; i++) {
            for (int a=0; a<=i; a++) {
                
                numList[a][i] = i+1;
                numList[i][a] = i+1;
            }
        }
				
				// [1, 2, 3, 2, 2, 3, 3, 3, 3]
				int[] numList = new int[n * n];

        for (int i = 0; i < n; i++) {
            for (int a = 0; a <= i; a++) {
                numList[a * n + i] = i + 1;
                numList[i * n + a] = i + 1;
            }
        }
        
        return answer;
    }
}
```

</aside>
