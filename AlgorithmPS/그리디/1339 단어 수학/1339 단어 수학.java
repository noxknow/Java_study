import java.io.*;
import java.util.*;

public class Main {

    static int[] words;
    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        int n = Integer.parseInt(br.readLine());
        words = new int[26];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < str.length(); j++) {
                char word = str.charAt(j);
                words[word - 'A'] += (int) Math.pow(10, str.length() - 1 - j);
            }
        }

        Arrays.sort(words);

        int value = 9;
        int idx = 25;
        int res = 0;
        while (words[idx] != 0) {
            res += words[idx] * value; // Arrays.sort 로 0이 아닌 수는 가장 마지막부터 들어있다.
            idx--;
            value--;
        }

        System.out.println(res);

        br.close();
    }
}
