import java.util.*;
import java.io.*;

public class Main {

    static int n, ansL, ansM, ansR;
    static long minValue = Long.MAX_VALUE;
    static long[] numbers;
    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        n = Integer.parseInt(br.readLine());

        numbers = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(numbers);

        for (int i = 0; i < n - 2; i++) {
            int left = i;
            int mid = i + 1;
            int right = n - 1;

            while (mid < right) {
                long sum = numbers[left] + numbers[mid] + numbers[right];

                if (minValue > Math.abs(numbers[left] + numbers[mid] + numbers[right])) {
                    minValue = Math.abs(numbers[left] + numbers[mid] + numbers[right]);
                    ansL = left;
                    ansM = mid;
                    ansR = right;
                }

                if (sum >= 0) {
                    right--;
                } else {
                    mid++;
                }
            }
        }

        System.out.println(numbers[ansL] + " " + numbers[ansM] + " " + numbers[ansR]);

        br.close();
    }
}
