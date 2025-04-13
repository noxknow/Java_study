import java.util.*;
import java.io.*;

public class Main {

    static class Node {

        int start, end;

        Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    static int n, m, ans;
    static int[] parent;
    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            if (find(v1) == find(v2)) {
                System.out.println(i + 1);
                return;
            } else {
                union(v1, v2);
            }
        }

        System.out.println(0);

        br.close();
    }

    private static void union(int x, int y) {

        x = find(x);
        y = find(y);

        if (x != y) {
            parent[y] = x;
        }
    }

    private static int find(int x) {

        if (x == parent[x]) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }
}
