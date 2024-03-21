import java.io.*;
import java.util.*;

public class Main {

    static class Seat implements Comparable<Seat> {
        int x, y, favoriteCnt, emptyCnt;

        public Seat(int x, int y, int favoriteCnt, int emptyCnt) {
            this.x = x;
            this.y = y;
            this.favoriteCnt = favoriteCnt;
            this.emptyCnt = emptyCnt;
        }

        // 다른 좌석과 비교
        @Override
        public int compareTo(Seat other) {
            if (favoriteCnt != other.favoriteCnt) return other.favoriteCnt - this.favoriteCnt;
            if (emptyCnt != other.emptyCnt) return other.emptyCnt - this.emptyCnt;
            if (x != other.x) return this.x - other.x;

            return this.y - other.y;
        }

        /*
        @Override
        public int compareTo(Student o) {
            if(o.cnt == this.cnt) {
                if(o.emptyCnt == this.emptyCnt) {
                    if (o.x == this.x) {
                        return this.y - o.y;
                    }
                    return this.x - o.x;
                }
                return o.emptyCnt - this.emptyCnt;
            }
            return o.cnt - this.cnt;
        }
         */
    }

    static int n, student, res;
    static int[][] position;
    static int[][] favorite;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        n = Integer.parseInt(br.readLine());
        favorite = new int[n * n + 1][4];
        position = new int[n][n];

        for (int i = 0; i < n * n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            student = Integer.parseInt(st.nextToken());

            for (int j = 0; j < 4; j++) {
                favorite[student][j] = Integer.parseInt(st.nextToken());
            }

            Seat seat = findPosition();
            position[seat.x][seat.y] = student;
        }

        res = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                student = position[i][j];
                int[] counts = calculateCnt(i, j, student);

                if (counts[0] > 0) {
                    res += (int) Math.pow(10, counts[0] - 1);
                }
            }
        }

        System.out.println(res);

        br.close();
    }

    private static Seat findPosition() {
        Seat seat = null;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (position[r][c] != 0) continue;

                int[] counts = calculateCnt(r, c, student);
                Seat curSeat = new Seat(r, c, counts[0], counts[1]);

                if (seat == null) {
                    seat = curSeat;
                    continue;
                }

                // 양수를 반환해야 현재 위치가 더 좋다는 의미 ( other.favoriteCnt - this.favoriteCnt 가 양수란 의미는 현재의 favoriteCnt 이 더 크다는 의미 )
                if (seat.compareTo(curSeat) > 0) {
                    seat = curSeat;
                }
            }
        }

        return seat;
    }

    private static int[] calculateCnt(int x, int y, int student) {
        int favoriteCnt = 0;
        int cnt = 0;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

            for (int j = 0; j < 4; j++) {
                if (position[nx][ny] == favorite[student][j]) {
                    favoriteCnt++;
                    break;
                }
            }

            if (position[nx][ny] == 0) cnt++;
        }

        return new int[] {favoriteCnt, cnt};
    }
}
