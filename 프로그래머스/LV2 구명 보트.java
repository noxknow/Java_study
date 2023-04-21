import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        int min = 0;
        
        for(int max=people.length-1; max >= min; max--) {
            if(limit >= people[min] + people[max]) {
                min++;
            }
            answer++;
        }
        
        return answer;
    }
}
