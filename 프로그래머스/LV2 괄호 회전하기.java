import java.util.*;

class Solution {
    public int solution(String s) {
        
        String[] words = s.split("");
        
        int answer = 0;
        
        for (int i=0; i<words.length; i++) {
            answer = check(words, answer);
            rotate(words);
        }
        
        return answer;
    }
    
    public static String[] rotate(String[] words) {
        
        String word = words[0];
        
        for (int i=0; i<words.length-1; i++) {
            words[i] = words[i+1];
        }
        
        words[words.length-1] = word;
        
        return words;
    }
    
    public static int check(String[] words, int answer) {
        
        Stack<String> stack = new Stack<>();
        
        for (int j = 0; j < words.length; j++) {
            String str = words[j];

            if (stack.isEmpty()) {
                stack.push(str);
            } else if (str.equals(")") && stack.peek().equals("(")) {
                stack.pop();
            } else if (str.equals("}") && stack.peek().equals("{")) {
                stack.pop();
            } else if (str.equals("]") && stack.peek().equals("[")) {
                stack.pop();
            } else {
                stack.push(str);
            }
        }
        
        if (stack.isEmpty()) { answer++; }
        
        return answer;
    }
}
