import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt")); 

        int n = Integer.parseInt(br.readLine());
        int[] timeGroup = new int[n];
        int result = 0;

        for (int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());
            timeGroup[i] = time;

            for (int j=0; j<count; j++) {
                int preWork = Integer.parseInt(st.nextToken()) - 1;
                timeGroup[i] = Math.max(timeGroup[i], timeGroup[preWork] + time);
            }

            result = Math.max(result, timeGroup[i]);
        }

        System.out.println(result);
    }
}
