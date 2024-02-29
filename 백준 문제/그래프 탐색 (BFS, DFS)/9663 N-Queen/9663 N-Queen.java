import java.io.*;
import java.util.*;

public class Main {

    static int n, res;
    static int[][] chessMap;
    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        n = Integer.parseInt(br.readLine());
        chessMap = new int[n][n];
        res = 0;

        for (int i = 0; i < n; i++) {
            chessMap[0][i] = 1;
            backTracking(1);
            chessMap[0][i] = 0;
        }

        System.out.println(res);

        br.close();
    }

    private static void backTracking(int depth) {

        if (depth == n) {
            res++;
            return;
        }

        for (int i = 0; i < n; i++) {
            if (isValid(depth, i)) {
                chessMap[depth][i] = 1;
                backTracking(depth + 1);
                chessMap[depth][i] = 0;
            }
        }
    }

    private static boolean isValid(int depth, int col) {

        // 세로 방향 검사 -> 4행으로 예를 들면, 0번 행 부터 3번행 까지 4번째 행에서 두고자 하는 곳의 세로 방향에 위치하는지 확인하는 로직
        for (int i = 0; i < depth; i++) {
            if (chessMap[i][col] == 1) return false;
        }

        // \ 방향 검사
        int px = depth - 1;
        int py = col - 1;
        while (px >= 0 && py >= 0) {
            if (chessMap[px--][py--] == 1) {
                return false;
            }
        }

        // / 방향 검사
        px = depth - 1;
        py = col + 1;
        while (px >= 0 && py < n) {
            if (chessMap[px--][py++] == 1)
                return false;
        }

        return true;
    }
}
