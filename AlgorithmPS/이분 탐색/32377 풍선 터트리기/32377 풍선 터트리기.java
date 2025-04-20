import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br;
    static long n,maxTime, target, beforeTarget;
    static long[] times;
    public static void main(String[] args) throws Exception {

//      br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Long.parseLong(st.nextToken());
        times = new long[3];

        for (int i = 0; i < 3; i++) {
            times[i] = Long.parseLong(st.nextToken());
            maxTime = Math.max(maxTime, times[i]);
        }

        target = binarySearch();

        getLastWinner();

        br.close();
    }

    private static long binarySearch() {

        long left = 1;
        long right = maxTime * n;

        while (left <= right) {

            long mid = (left + right) / 2;
            long cnt = 0;

            for (int i = 0; i < 3; i++) {
                cnt += (mid / times[i]);
            }

            if (cnt >= n) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private static void getLastWinner() {

        beforeTarget = 0;
        for (int i = 0; i < 3; i++) {
            beforeTarget += ((target - 1) / times[i]);
        }

        for (int i = 0; i < 3; i++) {
            if (target % times[i] == 0) {
                beforeTarget++;
                if (beforeTarget == n) {
                    if (i == 0) {
                        System.out.println("A win");
                    } else if (i == 1) {
                        System.out.println("B win");
                    } else {
                        System.out.println("C win");
                    }
                    break;
                }
            }
        }
    }
}
