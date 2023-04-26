import java.util.ArrayList;

public class Solution {
    public int[] solution(int[] arr, int[][] queries) {
        int[] res = new int[queries.length];
        
        for (int i = 0; i < queries.length; i++) {
            int s = queries[i][0];
            int e = queries[i][1];
            int k = queries[i][2];
            
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int j = s; j <= e; j++) {
                if (arr[j] > k) {
                    list.add(arr[j]);
                }
            }
            
            if (list.size() == 0) {
                res[i] = -1;
            } else {
                int min = list.get(0);
                for (int j = 1; j < list.size(); j++) {
                    if (list.get(j) < min) {
                        min = list.get(j);
                    }
                }
                res[i] = min;
            }
        }
        
        return res;
    }
}
