import java.io.*;
import java.util.*;

public class Main {

    static int w, h, answer;
    static char[][] buildingMap;
    static Queue<int[]> person;
    static Queue<int[]> fire;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        for (int i=0; i<t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            buildingMap = new char[h][w];
            person = new LinkedList<>();
            fire = new LinkedList<>();

            for (int j=0; j<h; j++) {
                String line = br.readLine();
                for (int k = 0; k < w; k++) {
                    buildingMap[j][k] = line.charAt(k);

                    if (buildingMap[j][k] == '@') {
                        person.add(new int[]{j, k, 0});
                    } 
                    if (buildingMap[j][k] == '*') {
                        fire.add(new int[]{j, k, 0});
                    }
                }
            }

            answer = 0;
            bfs();

            if (answer == 0) sb.append("IMPOSSIBLE\n");
            else sb.append(answer + "\n");
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static void bfs() {

        while (!person.isEmpty()) {
            int size = fire.size();
            for (int i = 0; i < size; i++) {
                int[] tmp = fire.poll();

                for (int j = 0; j < 4; j++) {
                    int nx = tmp[0] + dx[j];
                    int ny = tmp[1] + dy[j];

                    if (range(nx, ny) && (buildingMap[nx][ny] == '.' || buildingMap[nx][ny] == '@')) {
                        buildingMap[nx][ny] = '*';
                        fire.offer(new int[] { nx, ny, 0 });
                    }
                }

            }

            size = person.size();
            for (int k=0; k<size; k++) {
                int[] tmp = person.poll();
                
                for (int j=0; j<4; j++) {
                    int nx = tmp[0] + dx[j];
                    int ny = tmp[1] + dy[j];

                    if (!range(nx, ny)) {
                        answer = tmp[2] + 1;
                        return;
                    }

                    if (buildingMap[nx][ny] == '.') {
                        buildingMap[nx][ny] = '@';
                        person.offer(new int[] {nx, ny, tmp[2] + 1});
                    }
                }
            }
        }
    }

    private static boolean range(int x, int y) {
        return x>=0 && y>=0 && x<h && y<w;
    }
}
