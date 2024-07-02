import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static int[] heights;
    static int[] afterHeights;
    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        heights = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        afterHeights = new int[n + 2];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int diffHeight = Integer.parseInt(st.nextToken());

            afterHeights[s] += diffHeight;
            afterHeights[e + 1] -= diffHeight; // 1 ~ n 이기 때문에 + 1
        }

        for (int i = 1; i <= n; i++) {
            afterHeights[i] += afterHeights[i - 1];
        }

        for (int i = 1; i <= n; i++) {
            System.out.print(heights[i] + afterHeights[i] + " ");
        }

        br.close();
    }
}
