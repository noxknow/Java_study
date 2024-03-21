    import java.io.*;
    import java.util.*;

    public class Main {

        static int r, c, cnt;
        static char[][] pipeMap;
        static boolean finished;
        static int[] dx = {-1,0,1};
        static int[] dy = {1,1,1};
        public static void main(String[] args) throws IOException {

            // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            StringTokenizer st = new StringTokenizer(br.readLine());

            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            pipeMap = new char[r][c];

            for (int i = 0; i < r; i++) {
                String word = br.readLine();
                for (int j = 0; j < c; j++) {
                    pipeMap[i][j] = word.charAt(j);
                }
            }

            for (int i = 0; i < r; i++) {
                finished = false;
                pipeMap[i][0] = '@';
                dfs(i, 0);
            }

            System.out.println(cnt);

            br.close();
        }

        private static void dfs(int x, int y) {

            if (y == c - 1) {
                finished = true;
                cnt++;
                return;
            }

            for (int i = 0; i < 3; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= r || ny < 0 || ny >= c || pipeMap[nx][ny] == 'x' || pipeMap[nx][ny] == '@') continue;

                if (finished) continue;

                pipeMap[nx][ny] = '@';
                dfs(nx, ny);
            }
        }
    }
