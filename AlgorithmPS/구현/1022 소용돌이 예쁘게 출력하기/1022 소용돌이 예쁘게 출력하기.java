import java.util.*;
import java.io.*;

public class Main {

    static int r1, c1, r2, c2, maxNum;
    static int[][] map;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};
    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r1 = Integer.parseInt(st.nextToken());
        c1 = Integer.parseInt(st.nextToken());
        r2 = Integer.parseInt(st.nextToken());
        c2 = Integer.parseInt(st.nextToken());

        map = new int[r2 - r1 + 1][c2 - c1 + 1];

        fill();
        printResult();

        br.close();
    }

    private static void fill() {

        int x = 0, y = 0, dir = 0;
        int num = 1, dnum = 1, cnt = 0; // dnum은 위 혹은 아래로 이동했을 때 그 행에 들어가는 숫자가 1개씩 늘어가는걸 의미

        while (!isFinished()) {

            if (x >= r1 && x <= r2 && y >= c1 && y <= c2) {
                map[x - r1][y - c1] = num;
            }
            num++;
            cnt++;

            x = x + dx[dir];
            y = y + dy[dir];
            if (cnt == dnum) {
                cnt = 0;
                if (dir == 1 || dir == 3) dnum++;
                dir = (dir + 1) % 4;
            }
        }

        maxNum = num - 1;
    }

    private static void printResult() {

        int maxLen = (int) Math.log10(maxNum), len;

        for (int i = 0; i <= r2 - r1; i++) {
            for (int j = 0; j <= c2 - c1; j++) {
                len = maxLen - (int) Math.log10(map[i][j]);
                for (int k = 0; k < len; k++) {
                    System.out.print(" ");
                }
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean isFinished() {
        return map[0][0] != 0 && map[r2 - r1][0] != 0 && map[0][c2 - c1] != 0 && map[r2 - r1][c2 - c1] != 0;
    }
}
