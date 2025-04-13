import java.io.*;
import java.util.*;

public class Main {

    static int res;
    static List<Integer> numbers;
    static List<Character> ops;
    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        int n = Integer.parseInt(br.readLine());
        numbers = new ArrayList<>();
        ops = new ArrayList<>();
        res = Integer.MIN_VALUE;

        String words = br.readLine();
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                numbers.add(Character.getNumericValue(words.charAt(i)));
            } else {
                ops.add(words.charAt(i));
            }
        }

        dfs(numbers.get(0), 0);

        System.out.println(res);

        br.close();
    }

    private static void dfs(int curValue, int idx) {

        if (idx == ops.size()) {
            res = Math.max(res, curValue);
            return;
        }

        int tempValue = calC(curValue, ops.get(idx), numbers.get(idx + 1));
        dfs(tempValue, idx + 1);

        if (idx + 1 < ops.size()) {
            tempValue = calC(curValue, ops.get(idx), calC(numbers.get(idx + 1), ops.get(idx + 1), numbers.get(idx + 2)));
            dfs(tempValue, idx + 2);
        }
    }

    private static int calC(int n1, char op, int n2) {
        if (op == '+') {
            return n1 + n2;
        } else if (op == '-') {
            return n1 - n2;
        } else {
            return n1 * n2;
        }
    }
}
