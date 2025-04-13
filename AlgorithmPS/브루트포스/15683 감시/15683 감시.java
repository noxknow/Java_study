import java.io.*;
import java.util.*;

public class Main {

    static class Node {

        int x, y, command;

        Node(int x, int y, int command) {
            this.x = x;
            this.y = y;
            this.command = command;
        }
    }

    public static int n, m, res;
    public static int[][] officeMap;
    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        res = Integer.MAX_VALUE;

        officeMap = new int[n][m];
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                officeMap[i][j] = Integer.parseInt(st.nextToken());

                if (officeMap[i][j] != 0 && officeMap[i][j] != 6) {
                    nodes.add(new Node(i, j, officeMap[i][j]));
                }
            }
        }

        dfs(officeMap, 0, nodes);
        System.out.println(res);

        br.close();
    }

    private static void dfs(int[][] curMap, int cnt, List<Node> nodes) {

        if (cnt == nodes.size()) {
            res = Math.min(res, checkBlindSpot(curMap));
            return;
        }

        int[][] tmpMap;
        Node node = nodes.get(cnt);
        if (node.command == 1) {
            tmpMap = copyMap(curMap);
            checkRight(tmpMap, node.x, node.y);
            dfs(tmpMap, cnt + 1, nodes);

            tmpMap = copyMap(curMap);
            checkLeft(tmpMap, node.x, node.y);
            dfs(tmpMap, cnt + 1, nodes);

            tmpMap = copyMap(curMap);
            checkUp(tmpMap, node.x, node.y);
            dfs(tmpMap, cnt + 1, nodes);

            tmpMap = copyMap(curMap);
            checkDown(tmpMap, node.x, node.y);
            dfs(tmpMap, cnt + 1, nodes);
        } else if (node.command == 2) {
            tmpMap = copyMap(curMap);
            checkRight(tmpMap, node.x, node.y);
            checkLeft(tmpMap, node.x, node.y);
            dfs(tmpMap, cnt + 1, nodes);

            tmpMap = copyMap(curMap);
            checkUp(tmpMap, node.x, node.y);
            checkDown(tmpMap, node.x, node.y);
            dfs(tmpMap, cnt + 1, nodes);
        } else if (node.command == 3) {
            tmpMap = copyMap(curMap);
            checkUp(tmpMap, node.x, node.y);
            checkRight(tmpMap, node.x, node.y);
            dfs(tmpMap, cnt + 1, nodes);

            tmpMap = copyMap(curMap);
            checkLeft(tmpMap, node.x, node.y);
            checkUp(tmpMap, node.x, node.y);
            dfs(tmpMap, cnt + 1, nodes);

            tmpMap = copyMap(curMap);
            checkLeft(tmpMap, node.x, node.y);
            checkDown(tmpMap, node.x, node.y);
            dfs(tmpMap, cnt + 1, nodes);

            tmpMap = copyMap(curMap);
            checkDown(tmpMap, node.x, node.y);
            checkRight(tmpMap, node.x, node.y);
            dfs(tmpMap, cnt + 1, nodes);
        } else if (node.command == 4) {
            tmpMap = copyMap(curMap);
            checkLeft(tmpMap, node.x, node.y);
            checkUp(tmpMap, node.x, node.y);
            checkRight(tmpMap, node.x, node.y);
            dfs(tmpMap, cnt + 1, nodes);

            tmpMap = copyMap(curMap);
            checkDown(tmpMap, node.x, node.y);
            checkLeft(tmpMap, node.x, node.y);
            checkUp(tmpMap, node.x, node.y);
            dfs(tmpMap, cnt + 1, nodes);

            tmpMap = copyMap(curMap);
            checkLeft(tmpMap, node.x, node.y);
            checkDown(tmpMap, node.x, node.y);
            checkRight(tmpMap, node.x, node.y);
            dfs(tmpMap, cnt + 1, nodes);

            tmpMap = copyMap(curMap);
            checkUp(tmpMap, node.x, node.y);
            checkRight(tmpMap, node.x, node.y);
            checkDown(tmpMap, node.x, node.y);
            dfs(tmpMap, cnt + 1, nodes);
        } else if (node.command == 5) {
            tmpMap = copyMap(curMap);
            checkUp(tmpMap, node.x, node.y);
            checkRight(tmpMap, node.x, node.y);
            checkDown(tmpMap, node.x, node.y);
            checkLeft(tmpMap, node.x, node.y);
            dfs(tmpMap, cnt + 1, nodes);
        }
    }

    private static void checkRight(int[][] tmpMap, int x, int y) {

        for (int i = y + 1; i < m; i++) {
            if (tmpMap[x][i] == 6) return;
            if (tmpMap[x][i] != 0) continue;

            tmpMap[x][i] = -1;
        }
    }

    private static void checkLeft(int[][] tmpMap, int x, int y) {

        for (int i = y - 1; i >= 0; i--) {
            if (tmpMap[x][i] == 6) return;
            if (tmpMap[x][i] != 0) continue;

            tmpMap[x][i] = -1;
        }
    }

    private static void checkUp(int[][] tmpMap, int x, int y) {

        for (int i = x - 1; i >= 0; i--) {
            if (tmpMap[i][y] == 6) return;
            if (tmpMap[i][y] != 0) continue;

            tmpMap[i][y] = -1;
        }
    }

    private static void checkDown(int[][] tmpMap, int x, int y) {

        for (int i = x + 1; i < n; i++) {
            if (tmpMap[i][y] == 6) return;
            if (tmpMap[i][y] != 0) continue;

            tmpMap[i][y] = -1;
        }
    }

    private static int checkBlindSpot(int[][] curMap) {
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (curMap[i][j] == 0) {
                    cnt++;
                }
            }
        }

        return cnt;
    }

    private static int[][] copyMap(int[][] curMap) {

        int[][] tmpMap = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                tmpMap[i][j] = curMap[i][j];
            }
        }

        return tmpMap;
    }
}
