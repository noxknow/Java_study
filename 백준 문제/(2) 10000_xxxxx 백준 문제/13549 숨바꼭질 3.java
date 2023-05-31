import java.util.*;
import java.io.*;

public class Main {
    static int n, k;
    static LinkedList<Integer> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        q.add(n);
        int MAX = 100001;
        int[] visited = new int[MAX];
        int[] distance = new int[MAX];
        visited[n] = 1;

        while (!q.isEmpty()) {
            int tmp = q.poll();
            if (tmp * 2 <= MAX - 1 && visited[tmp * 2] == 0) {
                q.addFirst(tmp * 2);
                visited[tmp * 2] = 1;
                distance[tmp * 2] = distance[tmp];
            }
            if (tmp + 1 <= MAX - 1 && visited[tmp + 1] == 0) {
                q.add(tmp + 1);
                visited[tmp + 1] = 1;
                distance[tmp + 1] = distance[tmp] + 1;
            }
            if (tmp - 1 >= 0 && visited[tmp - 1] == 0) {
                q.add(tmp - 1);
                visited[tmp - 1] = 1;
                distance[tmp - 1] = distance[tmp] + 1;
            }
        }

        System.out.println(distance[k]);
    }
}
