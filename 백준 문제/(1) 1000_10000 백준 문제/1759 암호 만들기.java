import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static List<String> word_list = new ArrayList<>();
    static List<Character> vowel = new ArrayList<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
    // static String vowel[] = {"a","e","i","o","u"}; // new String[] {"a","e","i","o","u"};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            word_list.add(st.nextToken());
        }
        Collections.sort(word_list);    
        dfs(0,0);
    }

    public static void dfs(int cnt, int idx) {
        if (cnt == n) {
            int vo = 0, co = 0;
            for (int i = 0; i < sb.length(); i++) {
                char ch = sb.charAt(i);
                if (vowel.contains(ch)) {
                    vo += 1;
                } else {
                    co += 1;
                }
            }

            if (vo>=1 && co>=2) {
                System.out.println(sb);
            }

            return;
        }

        for (int i = idx; i < m; i++) {
            sb.append(word_list.get(i));
            dfs(cnt+1, i+1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
