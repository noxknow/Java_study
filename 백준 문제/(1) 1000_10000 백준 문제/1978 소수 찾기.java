import java.io.*;
import java.util.*;
public class Main {
    static int count;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        count = 0;
        int n = Integer.parseInt(br.readLine());
        primeNumber();  
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (arr[num] != 0) count++;  
        }
        System.out.print(count);
    }

    static void primeNumber() {
        arr = new int[1001];  
        int n = 1000;
        for (int i = 2; i <= n; i++) {
            arr[i] = i;
        }

        for (int i = 2; i <= n; i++) {
            if (arr[i] == 0) continue;
            for (int j = i+i; j <= n; j += i) {
                arr[j] = 0;
            }
        }
    }
}
