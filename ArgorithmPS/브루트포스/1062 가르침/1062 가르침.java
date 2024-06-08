import java.io.*;
import java.util.*;

public class Main {

    static int n, k, res;
    static boolean[] visited = new boolean[26];
    static List<String> strGroup;
    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        res = Integer.MIN_VALUE;

        strGroup = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            strGroup.add(str);
        }

        visited['a' - 'a'] = true;
        visited['n' - 'a'] = true;
        visited['t' - 'a'] = true;
        visited['i' - 'a'] = true;
        visited['c' - 'a'] = true;

        if (k < 5) {
            System.out.println(0);
        } else {
            combination(0, 0);

            System.out.println(res);
        }

        br.close();
    }

    private static void combination(int idx, int cnt) {

        if (cnt + 5 == k) {
            checkStr();
            return;
        }

        for (int i = idx; i < 26; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            combination(i + 1, cnt + 1);
            visited[i] = false;
        }
    }

    private static void checkStr() {
        int localRes = 0;

        for (String str : strGroup) {
            boolean valid = true;

            for (int i = 0; i < str.length(); i++) {
                if (!visited[(str.charAt(i)) - 'a']) {
                    valid = false;
                    break;
                }
            }

            if (valid) localRes++;
        }

        res = Math.max(res, localRes);
    }
}
