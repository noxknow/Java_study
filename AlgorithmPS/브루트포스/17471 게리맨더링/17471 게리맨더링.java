import java.io.*;
import java.util.*;

public class Main {

    static int n, res;
    static int[] peoples;
    static List<List<Integer>> area;
    static boolean[] visited;
    static boolean[] check;
    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        n = Integer.parseInt(br.readLine());
        area = new ArrayList<>();
        res = Integer.MAX_VALUE;

        peoples = new int[n + 1];
        visited = new boolean[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        area.add(new ArrayList<>());
        for (int i = 1; i <= n; i++) {
            peoples[i] = Integer.parseInt(st.nextToken());
            area.add(new ArrayList<>());
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());

            for (int j = 0; j < count; j++) {
                int to = Integer.parseInt(st.nextToken());
                area.get(i).add(to);
            }
        }

        divide(1);

        System.out.println(res == Integer.MAX_VALUE ? -1 : res);

        br.close();
    }

    private static void divide(int idx) {
        if (idx == n + 1) {
            List<Integer> sectorA = new ArrayList<>();
            List<Integer> sectorB = new ArrayList<>();

            for (int i = 1; i <= n; i++) {
                if (visited[i]) {
                    sectorA.add(i);
                } else {
                    sectorB.add(i);
                }
            }

            if (sectorA.isEmpty() || sectorB.isEmpty()) return;

            if (bfs(sectorA) && bfs(sectorB)) {
                peopleDiff();
            }

            return;
        }

        visited[idx] = true;
        divide(idx + 1);
        visited[idx] = false;
        divide(idx + 1);
    }

    private static boolean bfs(List<Integer> sector) {

        Queue<Integer> q = new LinkedList<>();
        List<Integer> nodes = new ArrayList<>();
        check = new boolean[n + 1];

        nodes.add(sector.get(0));
        check[sector.get(0)] = true;
        q.offer(sector.get(0));

        while (!q.isEmpty()) {
            int curNode = q.poll();

            for (int nextNode : area.get(curNode)) {
                if (sector.contains(nextNode) && !check[nextNode]) {
                    check[nextNode] = true;
                    q.offer(nextNode);
                    nodes.add(nextNode);
                }
            }
        }

        return nodes.size() == sector.size();
    }

    private static void peopleDiff() {

        int sectorA = 0, sectorB = 0;

        for (int i = 1; i <= n; i++) {
            if (visited[i]) {
                sectorA += peoples[i];
            } else {
                sectorB += peoples[i];
            }
        }

        res = Math.min(res, Math.abs(sectorA - sectorB));
    }
}
