import java.util.*;
import java.io.*;

public class Main {

    static class Jewelry implements Comparable<Jewelry> {

        int m, v;

        Jewelry(int m, int v) {
            this.m = m;
            this.v = v;
        }

        @Override
        public int compareTo(Jewelry o) {
            if (this.m == o.m) {
                return o.v - this.v;
            }

            return this.m - o.m;
        }
    }

    static int n, k;
    static int[] bags;
    static Jewelry[] jewelries;
    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        jewelries = new Jewelry[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            jewelries[i] = new Jewelry(m, v);
        }

        Arrays.sort(jewelries);

        bags = new int[k];
        for (int i = 0; i < k; i++) {
            int c = Integer.parseInt(br.readLine());
            bags[i] = c;
        }

        Arrays.sort(bags);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        long ans = 0;
        for (int i = 0, j = 0; i < k; i++) {

            while (j < n && jewelries[j].m <= bags[i]) {
                pq.offer(jewelries[j++].v);
            }

            if (!pq.isEmpty()) {
                ans += pq.poll();
            }
        }

        System.out.println(ans);

        br.close();
    }
}
