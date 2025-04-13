import java.util.*;
import java.io.*;

public class Main {

    static int n, count;
    static boolean[] visit, done;
    static int[] graph;
    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {

            n = Integer.parseInt(br.readLine());
            visit = new boolean[n+1];
            done = new boolean[n+1];
            count = 0;

            graph = new int[n + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                int num = Integer.parseInt(st.nextToken());
                graph[i] = num;
            }

            for(int i=1; i<= n; i++){
                if(!done[i]){
                    dfs(i);
                }
            }

            System.out.println(n - count);
        }

        br.close();
    }

    public static void dfs(int n) {

        if (visit[n]) {
            done[n] = true;
            count++;
        } else {
            visit[n] = true;
        }

        if (!done[graph[n]]) {
            dfs(graph[n]);
        }

        visit[n] = false;
        done[n] = true;
    }
}
