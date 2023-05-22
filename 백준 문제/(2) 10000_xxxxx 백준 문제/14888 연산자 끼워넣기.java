import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int n;
    static int[] num_list;
    static int[] op_cnt = new int[4];
    static int maximum = Integer.MIN_VALUE;
    static int minimum = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        num_list = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num_list[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            op_cnt[i] = Integer.parseInt(st.nextToken());
        }

        dfs(1, num_list[0], op_cnt[0], op_cnt[1], op_cnt[2], op_cnt[3]);
        System.out.println(maximum);
        System.out.println(minimum);
    }

    public static void dfs(int depth, int total,int plus, int minus, int multiply, int divide) {
        if (depth == n) {
            maximum = Math.max(maximum, total);
            minimum = Math.min(minimum, total);
            return;
        }
        if (plus != 0) {
            dfs(depth + 1, total + num_list[depth], plus - 1, minus, multiply, divide);
        }
        if (minus != 0) {
            dfs(depth + 1, total - num_list[depth], plus, minus - 1, multiply, divide);
        }
        if (multiply != 0) {
            dfs(depth + 1, total * num_list[depth], plus, minus, multiply - 1, divide);
        }
        if (divide != 0) {
            dfs(depth + 1, (int)(total / num_list[depth]), plus, minus, multiply, divide - 1);
        }
    }
}
