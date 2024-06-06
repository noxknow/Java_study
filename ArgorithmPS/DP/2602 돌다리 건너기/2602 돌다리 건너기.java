import java.io.*;
import java.util.*;

public class Main {

    static String target, devil, angel;
    static int[][][] dp;
    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        target = br.readLine();
        devil = br.readLine();
        angel = br.readLine();

        int bridgeLen = devil.length();
        dp = new int[target.length() + 1][bridgeLen + 1][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < bridgeLen; j++) {
                for (int k = 0; k < target.length(); k++) {
                    dp[k][j][i] = -1;
                }
            }
        }

        int dev_start = crossBridge(0,0,0);
        int ang_start = crossBridge(0,0,1);

        System.out.println(dev_start + ang_start);

        br.close();
    }

    private static int crossBridge(int idx, int curStone, int turn) {

        if (idx == target.length()) return 1;
        if (dp[idx][curStone][turn] != -1) return dp[idx][curStone][turn];

        int res = 0;
        if (turn == 0) {
            for (int i = curStone; i < devil.length(); i++) {
                if (devil.charAt(i) == target.charAt(idx)) {
                    res += crossBridge(idx + 1, i + 1, 1);
                }
            }
        } else {
            for (int i = curStone; i < angel.length(); i++) {
                if (angel.charAt(i) == target.charAt(idx)) {
                    res += crossBridge(idx + 1, i + 1, 0);
                }
            }
        }

        return dp[idx][curStone][turn] = res;
    }
}
