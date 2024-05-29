import java.io.*;
import java.util.*;

public class Main {

    static class Node {

        int x, y, p;

        Node(int x, int y, int p) {
            this.x = x;
            this.y = y;
            this.p = p;
        }
    }

    static int n, l, r, res;
    static boolean[][] visited;
    static int[][] populationMap;
    static int[][] groupNum;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        populationMap = new int[n][n];
        int groupId = 1;
        res = -1;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                populationMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (groupId != n * n + 1) { // groupId를 1부터 시작해서 + 1 해야 출
            groupId = 1;
            visited = new boolean[n][n];
            groupNum = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (groupNum[i][j] == 0) {
                        groupNum[i][j] = groupId;
                        visited[i][j] = true;
                        bfs(new Node(i, j, populationMap[i][j]), groupId);
                        groupId++;
                    }
                }
            }

            res++;
        }

        System.out.println(res);

        br.close();
    }

    private static void bfs(Node curNode, int groupId) {

        Queue<Node> q = new LinkedList<>();
        List<Node> groups = new ArrayList<>();
        int totalPopulation = curNode.p;
        int groupCnt = 1;

        q.add(curNode);
        groups.add(curNode);

        while (!q.isEmpty()) {
            Node tmpNode = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = tmpNode.x + dx[i];
                int ny = tmpNode.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (visited[nx][ny]) continue;

                int diffPopulation = Math.abs(populationMap[nx][ny] - populationMap[tmpNode.x][tmpNode.y]);

                if (diffPopulation >= l && diffPopulation <= r) {
                    visited[nx][ny] = true;
                    groupNum[nx][ny] = groupId;
                    totalPopulation += populationMap[nx][ny];
                    groupCnt++;
                    q.add(new Node(nx, ny, populationMap[nx][ny]));
                    groups.add(new Node(nx, ny, groupNum[nx][ny]));
                }
            }
        }

        calC(groups, totalPopulation, groupCnt);
    }

    private static void calC(List<Node> groups, int totalPopulation, int groupCnt) {

        int avgPopulation = totalPopulation / groupCnt;
        for (Node group : groups) {
            populationMap[group.x][group.y] = avgPopulation;
        }
    }
}
