import java.util.*;
import java.io.*;

public class Main {

    static int testCase, n, m, k;
    static int[] moneys;
    static StringBuilder res;
    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        testCase = Integer.parseInt(br.readLine());
        res = new StringBuilder();
        for (int t = 1; t <= testCase; t++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st1.nextToken());
            m = Integer.parseInt(st1.nextToken());
            k = Integer.parseInt(st1.nextToken());

            moneys = new int[n];
            int sum = 0;
            int cnt = 0;
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int i = 0; i < n + (m - 1); i++) {
                if (i < n) {
                    moneys[i] = Integer.parseInt(st2.nextToken());
                    sum += moneys[i];
                } else {
                    sum += moneys[i - n];
                }

                if (i >= m - 1) {
                    if (sum < k) {
                        cnt++;
                    }
                    sum -= moneys[i - (m - 1)];
                }

                if (n == m && i == n - 1) break;
            }

            res.append(cnt).append("\n");
        }

        System.out.println(res);

        br.close();
    }
}
