class Solution {
    public int solution(String s) {
        
        String[] num = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};  
        
        //숫자가 0~9까지 10개로 정해져 있으므로 i<10
        for(int i=0; i<10; i++){
            s = s.replace(num[i], Integer.toString(i));
        }
        
        int answer = Integer.parseInt(s);
        
        return answer;
    }
}

/*
Integer.toString() vs String.valueOf() 의 차이 (둘은 똑같지만 조금의 차이가 있다.)

만약 입력된 문자열이 Null이라면 두 함수의 대응점이 다릅니다. String.valueOf()는 우선 "null"이라는 문자열로 처리합니다. 
하지만 Integer.toString()은 "Null PointerException"이라는 오류를 발생시킵니다. 
만약 null 조차도 오류가 아닌 문자열로 처리해야 한다면 String.valueOf()를 그 외라면 Integer.toString()을 사용하면 됩니다.
*/
