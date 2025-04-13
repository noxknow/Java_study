import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br;
    static int n, d, k, c, res;
    static int[] sushiDishes;
    static int[] eaten;
    public static void main(String[] args) throws Exception {

//      br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        fillDishes();
        slide();

        System.out.println(res);

        br.close();
    }

    private static void fillDishes() throws Exception {

        sushiDishes = new int[n + k - 1];

        for (int i = 0; i < n; i++) {
            sushiDishes[i] = Integer.parseInt(br.readLine());
        }

        for (int i = n; i < n + k - 1; i++) {
            sushiDishes[i] = sushiDishes[i - n];
        }
    }

    private static void slide() {

        initialize();

        int start = 0;
        int cnt = res;
        for (int i = k; i < sushiDishes.length; i++) {

            eaten[sushiDishes[start]]--;

            if (eaten[sushiDishes[start]] == 0) {
                cnt--;
            }

            if (eaten[sushiDishes[i]] == 0) {
                cnt++;
            }

            eaten[sushiDishes[i]]++;
            res = Math.max(res, cnt);
            start++;
        }
    }

    private static void initialize() {

        eaten = new int[d + 1];
        res = 1;
        eaten[c]++;

        for (int i = 0; i < k; i++) {
            if (eaten[sushiDishes[i]] == 0) {
                res++;
            }

            eaten[sushiDishes[i]]++;
        }
    }
}
