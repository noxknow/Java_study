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
