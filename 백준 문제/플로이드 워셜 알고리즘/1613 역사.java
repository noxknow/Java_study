import java.util.*;
import java.io.*;

public class Main {
    static int n, m, a, b, s;
	static int[][] prob;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

		prob = new int[n+1][n+1];
		for (int i = 1; i < n+1; i++) {
			for (int j = 1; j < n+1; j++) {
				prob[i][j] = 0;
			}
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			prob[a][b] = -1;
			prob[b][a] = 1;
		}

		for (int k = 1; k < n+1; k++) {
			for (int i = 1; i < n+1; i++) {
				for (int j = 1; j < n+1; j++) {
					if (prob[i][j] == 0) {
						if (prob[i][k] + prob[k][j] == -2) {
							prob[i][j] = -1;
						}
						else if (prob[i][k] + prob[k][j] == 2) {
							prob[i][j] = 1;
						}
					}
				}
			}
		}

		s = Integer.parseInt(br.readLine());
		for (int i = 0; i < s; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			System.out.println(prob[a][b]);
		}
    }
}
