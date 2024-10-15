import java.util.*;
import java.io.*;

public class Main {

    static class Connector {

        int pos, fuel;

        Connector (int pos, int fuel) {
            this.pos = pos;
            this.fuel = fuel;
        }
    }

    static int n, s;
    static boolean[] visited;
    static List<Connector> connectors;
    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken()) - 1;
        visited = new boolean[n];

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        connectors = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int pos = Integer.parseInt(st1.nextToken());
            int fuel = Integer.parseInt(st2.nextToken());

            connectors.add(new Connector(pos, fuel));
        }

        twoPointer();

        br.close();
    }

    private static void twoPointer() {

        Queue<Integer> q = new LinkedList<>();
        int mlPos = connectors.get(s).pos;
        int mxPos = connectors.get(s).pos;
        int mlIdx = s;
        int mxIdx = s;

        visited[s] = true;
        q.add(s);
        while (!q.isEmpty()) {

            int curConnect = q.poll();
            mlPos = Math.min(mlPos, connectors.get(curConnect).pos - connectors.get(curConnect).fuel);
            mxPos = Math.max(mxPos, connectors.get(curConnect).pos + connectors.get(curConnect).fuel);

            for (int left = mlIdx - 1; left >= 0; left--) {
                if (connectors.get(left).pos < mlPos) break;
                if (visited[left]) continue;

                mlIdx = Math.min(mlIdx, left);
                visited[left] = true;
                q.add(left);
            }

            for (int right = mxIdx + 1; right < n; right++) {
                if (connectors.get(right).pos > mxPos) break;
                if (visited[right]) continue;                                       

                mxIdx = Math.max(mxIdx, right);
                visited[right] = true;
                q.add(right);
            }
        }

        for (int i = mlIdx; i <= mxIdx; i++) {
            System.out.print(i + 1 + " ");
        }
    }
}
