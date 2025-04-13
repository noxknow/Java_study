import java.io.*;
import java.util.*;

public class Main {

    static int x;
    static int[][] relationships;
    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        x = Integer.parseInt(br.readLine());
        relationships = new int[58][58];
        for (int i = 0; i < 58; i++) {
            for (int j = 0; j < 58; j++) {
                relationships[i][j] = (int) 1e9;

                if (i == j) {
                    relationships[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < x; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " => ");
            int p = convertChar(st.nextToken().charAt(0));
            int q = convertChar(st.nextToken().charAt(0));

            if (p != q) {
                relationships[p][q] = 1;
            }
        }

        for (int k = 0; k < 58; k++) {
            for (int i = 0; i < 58; i++) {
                for (int j = 0; j < 58; j++) {
                    relationships[i][j] = Math.min(relationships[i][j], relationships[i][k] + relationships[k][j]);
                }
            }
        }

        List<String> validRelationships = new ArrayList<>();
        for (int i = 0; i < 58; i++) {
            for (int j = 0; j < 58; j++) {
                if (relationships[i][j] != (int) 1e9 && relationships[i][j] != 0) {
                    char a = (char) (i + 'A');
                    char b = (char) (j + 'A');
                    validRelationships.add(a + " => " + b);
                }
            }
        }

        System.out.println(validRelationships.size());

        for (String relationship : validRelationships) {
            System.out.println(relationship);
        }

        br.close();
    }

    private static int convertChar(char c) {
        if (c >= 'A' && c <= 'Z') {
            return c - 'A';
        } else {
            return c - 'a' + 32;
        }
    }
}
