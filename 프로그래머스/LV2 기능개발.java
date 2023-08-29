```java
import java.util.*;

class Solution {
    
    public List solution(int[] progresses, int[] speeds) {
        
        List<Integer> answer = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        
        for(int i=0; i<progresses.length; i++){
            
            q.add((int)Math.ceil((100.0-progresses[i])/speeds[i]));        
        }
        
        while (!q.isEmpty()) {
            
            int days = q.poll();
            int cnt = 1;
            
            while (!q.isEmpty() && q.peek() <= days) {
                
                q.poll();
                cnt++;
            }
            
            answer.add(cnt);
        }
        
        return answer;
    }
}
```
