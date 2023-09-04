<aside>
📌 스택을 사용한 경우 <br/><br/>

→ 이 경우에서 Stack객체가 Character타입이고, ‘==’으로 비교하는 것 역시 문자열이 아닌 char형이기 때문에 큰 따옴표가 아닌 작은 따옴표를 써야한다.

```java
import java.util.*;

class Solution {

    boolean solution(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i) == '(') {
                stack.push('(');
            } else if(s.charAt(i) == ')') {
                if(stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();  
    }
}
```
<br/>

-> **풀이 방법은 맞지만, 효율성이 안좋다.** 

```java
class Solution {
    boolean solution(String s) {
        boolean answer = true;
        
        String[] arr = s.split("");
        int sum = 0;
        for(String a : arr) {
            if(a.equals("(")) { // 문자열의 비교는 equals를 사용해야 한다.
                sum += 1;
            } else {
                sum -= 1;
            } if(sum < 0) {
                answer = false;
                break;
            }
        }
        if (sum > 0) {
            answer = false;
        } else if(sum == 0) {
            answer = true;
        }

        return answer;
    }
}
```
<br/>

-> **위에것보다 효율이 좋긴하지만 스택을 사용하지 않음**

```java
class Solution {

    boolean solution(String s) {
        int openCount = 0;
        int closeCount = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                openCount++;
            } else if (s.charAt(i) == ')') {
                closeCount++;
            }
            if (openCount < closeCount) {
                return false;
            }
        }
        if (openCount == closeCount) {
            return true;
        }
        return false;
    }
}
```

두 코드 모두 올바르게 괄호의 짝을 검사하지만, 첫 번째 코드는 문자열을 하나씩 검사하는 반면, 두 번째 코드는 문자열을 split 함수를 통해 분할한 후, 분할된 문자열 배열을 반복문으로 하나씩 검사한다.

따라서, 입력 문자열의 길이가 길어질수록 첫 번째 코드의 실행 시간이 더 빠르며, 두 번째 코드는 입력 문자열의 길이에 따라 실행 시간이 더 오래 걸릴 수 있다.

</aside>
