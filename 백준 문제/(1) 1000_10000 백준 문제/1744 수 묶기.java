import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Main {
    static List<Integer> plus = new ArrayList<>();
    static List<Integer> minus = new ArrayList<>();
    static int res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine());
            if (x <= 0) {
                minus.add(x);
            } else if (x == 1) {
                res += 1;
            } else {
                plus.add(x);
            }
        }

        Collections.sort(plus, Collections.reverseOrder());
        Collections.sort(minus);
        if (plus.size()%2 != 0) {
            plus.add(1);
        }
        if (minus.size()%2 != 0) {
            minus.add(1);
        }
        for (int i = 0; i < plus.size(); i+=2) {
            res += plus.get(i)*plus.get(i+1);
        }
        for (int i = 0; i < minus.size(); i+=2) {
            res += minus.get(i)*minus.get(i+1);
        }
        System.out.println(res);
    }
}
