class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int sum = brown + yellow;
        
        for(int i=3; i<(sum/2)+1; i++) {
            int j = sum/i;
            if(sum%i == 0 && j>=3) {
                int row = Math.max(i, j);
                int col = Math.min(i, j);
                int center = (row-2) * (col-2);
                
                if (center == yellow) {
                    answer[0] = row;
                    answer[1] = col;
                    return answer;
                }
            }
        }
        return answer;
    }
}
