import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br;
    static int n, k, minValue, maxValue, a, b;
    static int[] boards;
    public static void main(String[] args) throws Exception {

//      br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        minValue = Integer.MAX_VALUE;
        maxValue = Integer.MIN_VALUE;
        a = 0;
        b = 0;

        prefixSum();
        twoPointer();

        if (a == minValue) a = 0;

        System.out.println(a + " " + b);

        br.close();
    }

    private static void prefixSum() throws Exception {

        boards = new int[1000001];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            boards[left]++;
            boards[right]--;

            minValue = Math.min(minValue, left);
            maxValue = Math.max(maxValue, right);
        }

        for (int i = minValue + 1; i <= maxValue; i++) {
            boards[i] += boards[i - 1];
        }
    }

    private static void twoPointer() {

        int start = minValue;
        int end = minValue;
        int sum = 0;
        while (end <= maxValue) {

            if (sum < k) {
                sum += boards[end++];
            } else if (sum == k) {
                a = start;
                b = end;
                break;
            } else {
                sum -= boards[start++];
            }
        }
    }
}
