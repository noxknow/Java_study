<aside>
📌 StringTokenizer 와 StringBuilder 를 사용한 경우

> StringTokenizer는 레거시이다...  split을 쓰길 권장한다...!!
> 
- 구분자가 1개 이거나 길이가 길지 않다면 tokenizer가 빠르지만, 아니라면 split이 빠르다.
- `new StringTokenizer(s, " ", true);` → 이것의 의미는 구분자 까지 token에 포함한다는 의미이다.

```java
import java.util.*;

class Solution {
    public String solution(String s) {
        s = s.toLowerCase();
        
        StringTokenizer st = new StringTokenizer(s, " ", true);
        StringBuilder sb = new StringBuilder();
        while(st.hasMoreTokens()) {
            String words = st.nextToken();
            if(words.equals(" ")) {
                sb.append(words);
            } else {
                sb.append(words.substring(0, 1).toUpperCase() + words.substring(1));
            }
        }
        return sb.toString();
    }
}
```

**삼항연산자 이용한 경우**

> 공백 다음에 나오는 문자에 무조건 toUpperCase를 적용한다는 의미 (flag는 공백 이후 true가 된다.)
> 

```java
class Solution {
    public String solution(String s) {
			String answer = "";
			String[] words = s.toLowerCase().split("");
// [3, p, e, o, p, l, e,  , u, n, f, o, l, l, o, w, e, d,  , m, e]
			
			boolean flag = true;
			
			for (String word : words) {
				answer += flag ? word.toUpperCase() : word;
				flag = word.equals(" ") ? true : false;
			}

			return answer;
		}
}
```

</aside>
