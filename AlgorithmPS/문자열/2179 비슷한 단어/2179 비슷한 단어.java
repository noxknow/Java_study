import java.io.*;
import java.util.*;

public class Main {

    public static String ansA, ansB;
    public static List<String> words;
    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        int n = Integer.parseInt(br.readLine());
        int res = Integer.MIN_VALUE;

        words = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            words.add(br.readLine());
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                String wordA = words.get(i);
                String wordB = words.get(j);
                int lenPrefix = Math.min(wordA.length(), wordB.length());
                int cnt = 0;

                for (int k = 0; k < lenPrefix; k++) {
                    if (wordA.charAt(k) == wordB.charAt(k)) {
                        cnt++;
                    } else {
                        break;
                    }
                }

                if (res < cnt) {
                    res = cnt;
                    ansA = wordA;
                    ansB = wordB;
                }
            }
        }

        System.out.println(ansA);
        System.out.println(ansB);

        br.close();
    }
}
