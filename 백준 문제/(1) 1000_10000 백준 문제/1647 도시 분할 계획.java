import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static List<Edge> arr = new ArrayList<>();
    static int[] parent;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			arr.add(new Edge(start, end, cost));
		}

        parent = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}

        Collections.sort(arr);

        int max_ans = 0;
        for (int i = 0; i < arr.size(); i++) {
			Edge edge = arr.get(i);
			if (find(edge.start) != find(edge.end)) {
				union(edge.start, edge.end);
				ans += edge.cost;
                max_ans = edge.cost;
			}
		}
        System.out.println(ans - max_ans);
    }

    private static void union(int x, int y) {
        x = find(x);
		y = find(y);

		if (x != y) {
			parent[y] = x;
		}
    }

    private static int find(int x) {
        if (x == parent[x]) {
            return x;
        }

        parent[x] = find(parent[x]);
        return parent[x];
    }
}

class Edge implements Comparable<Edge> {
    int start;
	int end;
	int cost;

	Edge(int start, int end, int cost) {
		this.start = start;
		this.end = end;
		this.cost = cost;
	}

	@Override
	public int compareTo(Edge o) {
		return this.cost - o.cost;
	}
}
