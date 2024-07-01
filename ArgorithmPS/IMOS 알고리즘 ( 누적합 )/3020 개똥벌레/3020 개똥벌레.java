import java.io.*;
import java.util.*;

public class Main {

    static int n, h;
    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        int[] stalactites = new int[h + 1];
        int[] stalagmites = new int[h + 1];
        for (int i = 0; i < n / 2; i++) {
            int stalactitesHeight = Integer.parseInt(br.readLine());
            int stalagmitesHeight = Integer.parseInt(br.readLine());

            stalactites[stalactitesHeight]++;
            stalagmites[h - stalagmitesHeight + 1]++;
        }

        for (int i = h - 1; i >= 1; i--) {
            stalactites[i] += stalactites[i + 1];
        }

        for (int i = 2; i <= h; i++) {
            stalagmites[i] += stalagmites[i - 1];
        }

        int minObstacles = Integer.MAX_VALUE;
        int minCount = 0;
        for (int i = 1; i <= h; i++) {
            int obstacles = stalactites[i] + stalagmites[i];

            if (obstacles < minObstacles) {
                minObstacles = obstacles;
                minCount = 1;
            } else if (obstacles == minObstacles) {
                minCount++;
            }
        }

        System.out.println(minObstacles + " " + minCount);

        br.close();
    }
}
