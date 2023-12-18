import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt")); 
        String[] st = br.readLine().split(" ");

        int n = Integer.parseInt(st[0]);
        int x = Integer.parseInt(st[1]);
        char[] arr = new char[n];

        for (int i=0; i<n; i++) {
            arr[i] = 'A';
        }

        if (n > x || 26 * n < x) {
            System.out.println("!");
        } else {
            x -= n;
            for (int i=n-1; i>=0; i--) {
                int temp = Math.min(x, 25);
                arr[i] += temp;
                x -= temp;
            }

            System.out.println(arr);
        }
    }
}
