📌 → https://school.programmers.co.kr/learn/courses/30/lessons/181188?language=java <br/><br/>

**핵심 로직**

1. 처음 요격 미사일의 시작값을 0으로 초기화한다.
2. 끝점으로 오름차순 정렬된 미사일의 시작점을 요격 미사일의 값과 비교한다.
    
    2-1. 미사일의 시작점 값이 요격기 값 이상일 때, 요격기의 값을 해당 미사일의 끝점으로 업데이트 시키고 요격기의 개수를 추가한다.
    
3. 미사일 개수만큼 2의 과정을 반복한다.

개구간이므로 다음 미사일의 시작점이 before 값 이상이라면 미사일을 하나 추가한다.

(폐구간이라면 초과일 때만) <br/><br/>

```
5
10 -1
10 1 -1
4 1 -1
4 3 1 -1
3 3 -1
```

### 최종 결과 (최대 418.35ms, 183MB)

```java
import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        
        Arrays.sort(targets, (o1, o2) -> {
            if(o1[1] == o2[1]) {
                return o1[0] - o2[0];
            }
            
            return o1[1] - o2[1];
        });
        
        int before = 0;
        for(int[] target : targets){
            if(before <= target[0]){
                before = target[1];
                answer++;
            }
        }
        
        return answer;
    }
}
```
