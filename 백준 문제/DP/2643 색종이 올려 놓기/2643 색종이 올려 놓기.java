import java.io.*;
import java.util.*;

public class Main {

    static class Coordinate implements Comparable<Coordinate> {

        int x, y;

        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Coordinate o) {

            if (this.x == o.x) {
                return o.y - this.y;
            }

            return o.x - this.x;
        }
    }

    static List<Coordinate> coordinates;

    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        int n = Integer.parseInt(br.readLine());
        int res = Integer.MIN_VALUE;
        int[] dp = new int[n + 1];
        coordinates = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (x > y) {
                coordinates.add(new Coordinate(x, y));
            } else {
                coordinates.add(new Coordinate(y, x)); // y가 더 큰 경우 90도 돌려서 가로로 취급한다.
            }
        }

        Collections.sort(coordinates);

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (coordinates.get(i).x <= coordinates.get(j).x && coordinates.get(i).y <= coordinates.get(j).y) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            res = Math.max(res, dp[i]);
        }

        System.out.println(res);

        br.close();
    }
}
