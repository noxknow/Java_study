import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n+1];
        for (int i=1; i<=n; i++) {
            arr[i] = arr[i-1] + Integer.parseInt(st.nextToken());
        }
        for (int j=0; j<m; j++) {
            st = new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
            System.out.println(arr[b] - arr[a-1]);
        }
    }
}
