import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static long[] blockD;
    static long[] blockY;
    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        n = Integer.parseInt(br.readLine());
        blockD = new long[n];
        blockY = new long[n];

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            blockD[i] = Long.parseLong(st1.nextToken());
            blockY[i] = Long.parseLong(st2.nextToken());
        }

        binarySearch();
        
        br.close(); 
    }

    private static void binarySearch() {

        long left = 0;
        long right = (long) Math.pow(10, 12) - (n / 2);
        long minBlockCount = countBlock(left);
        long maxBlockCount = countBlock(right);

        while (left < right) {

            long mid = (left + right) / 2;

            if (minBlockCount < maxBlockCount) {
                right = mid;
                maxBlockCount = countBlock(right);
            } else {
                left = mid + 1;
                minBlockCount = countBlock(left);
            }
        }

        System.out.println(maxBlockCount);
    }

    private static long countBlock(long target) {

        long totalBlock = 0;
        long h = target;
        for (int i = n / 2; i >= 0; i--) {
            totalBlock += Math.abs(blockD[i] - h);
            totalBlock += Math.abs(blockY[i] - h);
            h++;
        }

        h = target + 1;
        for (int i = n / 2 + 1; i < n; i++) {
            totalBlock += Math.abs(blockD[i] - h);
            totalBlock += Math.abs(blockY[i] - h);
            h++;
        }

        return totalBlock;
    }
}
