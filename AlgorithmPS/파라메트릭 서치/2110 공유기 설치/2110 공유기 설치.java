import java.util.*;
import java.io.*;

public class Main {

    static int n, c;
    static int[] house;
    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        house = new int[n];
        for (int i = 0; i < n; i++) {
            house[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(house);

        binarySearch();

        br.close();
    }

    private static void binarySearch() {

        int lo = 1;
        int hi = house[n - 1] - house[0] + 1;

        while (lo < hi) {

            int mid = ( lo + hi ) / 2;

            if (countRouter(mid) < c) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        System.out.println(lo - 1);
    }

    private static int countRouter(int dist) {

        int lastLocate = house[0];
        int count = 1;
        for (int i = 1; i < house.length; i++) {

            int locate = house[i];

            if (locate - lastLocate >= dist) {
                count++;
                lastLocate = locate;
            }
        }

        return count;
    }
}
