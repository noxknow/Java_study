import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> arr = new ArrayList<>();
        for(int i=0; i<10; i++) {
            StringTokenizer st =  new StringTokenizer(br.readLine());
            int minus = Integer.parseInt(st.nextToken());
            int plus = Integer.parseInt(st.nextToken());
            if (i == 0) {
                arr.add(plus-minus);
            } else {
                arr.add(arr.get(i-1) + plus - minus);
            }
        }
        System.out.print(Collections.max(arr)); 
    }
}
