import java.util.*;
import java.io.*;

public class Main {
    static int n, m, a, b, cost;
	static int[][] bus_cost;
	static int INF = 1000000000;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
		bus_cost = new int[n+1][n+1];

		for (int i = 1; i < n+1; i++) {
			for (int j = 1; j < n+1; j++) {
				if (i == j) {
					bus_cost[i][j] = 0;
				}
				else {
					bus_cost[i][j] = INF;
				}
			}
		}

		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			bus_cost[a][b] = Math.min(cost, bus_cost[a][b]);
		}

		for (int k = 1; k < n+1; k++) {
			for (int i = 1; i < n+1; i++) {
				for (int j = 1; j < n+1; j++) {
					bus_cost[i][j] = Math.min(bus_cost[i][j], bus_cost[i][k] + bus_cost[k][j]);
				}
			}
		}

		for (int i = 1; i < n+1; i++) {
			for (int j = 1; j < n+1; j++) {
				if (bus_cost[i][j] == INF) {
					System.out.print(0 + " ");
				}
				else {
					System.out.print(bus_cost[i][j] + " ");
				}
			}
			System.out.println();
		}
    }
}
