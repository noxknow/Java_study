import java.util.*;
import java.io.*;

public class Main {

    static class Data implements Comparable<Data> {

        int p, v;

        Data(int p, int v) {
            this.p = p;
            this.v = v;
        }

        @Override
        public int compareTo(Data o) {
            return Integer.compare(this.p, o.p);
        }
    }

    static BufferedReader br;
    static int n, d;
    static long res;
    static List<Data> infos;
    public static void main(String[] args) throws Exception {

//      br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        infos = new ArrayList<>();

        fillData();
        slidingWindow();

        System.out.println(res);

        br.close();
    }

    private static void fillData() throws Exception {

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            infos.add(new Data(p, v));
        }

        Collections.sort(infos);
    }

    private static void slidingWindow() {

        int left = 0;
        int right = 0;
        long sum = 0;
        while (true) {

            while (right < n && infos.get(right).p - infos.get(left).p < d) {
                sum += infos.get(right).v;
                right++;
            }

            res = Math.max(res, sum);
            if (right == n) break;

            sum -= infos.get(left).v;
            left++;
        }
    }
}
