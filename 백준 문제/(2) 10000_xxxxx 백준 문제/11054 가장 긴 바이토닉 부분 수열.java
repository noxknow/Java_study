import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        List<Integer> arr = new ArrayList<>();
        while(st.hasMoreTokens()) {
            int num = Integer.parseInt(st.nextToken());
            arr.add(num);
        }
        // System.out.println(arr.get(0));

        List<Integer> dp = new ArrayList<>();
        List<Integer> dp1 = new ArrayList<>(Collections.nCopies(n, 1));
        List<Integer> dp2 = new ArrayList<>(Collections.nCopies(n, 1));
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr.get(i) > arr.get(j) ) {
                    dp1.set(i, Math.max(dp1.get(i), dp1.get(j)+1));
                }
            }
        }

        Collections.reverse(arr);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr.get(i) > arr.get(j) ) {
                    dp2.set(i, Math.max(dp2.get(i), dp2.get(j)+1));
                }
            }
        }
        Collections.reverse(dp2);

        for (int i = 0; i < n; i++) {
            dp.add(dp1.get(i) + dp2.get(i) - 1);
        }

        System.out.println(Collections.max(dp));
    }
}
