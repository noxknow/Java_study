# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/13.svg" width="30"> [15573] 채굴

📌 → https://www.acmicpc.net/problem/15573 <br/><br/>

**핵심 로직**

- 지도의 광물 강도를 기준으로 최소 조건을 만족하는 최대 강도를 구하기 위해 이분 탐색을 사용한다.
- `binarySearch` 메서드는 강도의 범위를 설정한 뒤, 중간값을 기준으로 조건을 만족하는지 확인하며 탐색 범위를 조정한다.
    - 중간값이 조건을 만족하면 오른쪽 경계를 줄여 더 작은 값으로 탐색을 시도하고, 그렇지 않으면 왼쪽 경계를 늘려 강도를 증가시킨다.
- `checkMinerals` 메서드는 BFS를 사용해 현재 기준 강도에서 조건을 만족하는지 확인한다.
    - 각 행과 열의 가장자리를 기준으로 시작점을 설정하고, BFS를 수행하며 강도가 기준 이하인 위치를 방문 처리한다.
    - BFS가 끝난 후 방문한 위치의 개수가 `k` 이상인지 확인해 조건 만족 여부를 반환한다.
- BFS를 구현하기 위해 `Node` 클래스를 정의하여 현재 위치를 저장하며, 방문 처리를 위해 `visited` 배열을 사용한다.
- 강도 값의 최소값과 최대값은 입력받은 배열에서 계산하며, 이를 이분 탐색의 초기 경계값으로 사용한다.
- 최종적으로 조건을 만족하는 최소 강도를 출력한다. <br/><br/>

```
5 5 10
3 3 3 3 3
3 2 2 2 3
3 2 2 2 3
3 2 2 2 3
3 2 2 2 3
```

### 최종 결과 (1580 ms)

```java
import java.util.*;
import java.io.*;

public class Main {

    static class Node{

        int x, y;

        Node (int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static int n, m, k, minStrength, maxStrength;
    static int[][] minerals;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        minStrength = Integer.MAX_VALUE;
        maxStrength = Integer.MIN_VALUE;

        minerals = new int[n][m];
        for (int i = 0; i < n; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                minerals[i][j] = Integer.parseInt(st1.nextToken());
                maxStrength = Math.max(maxStrength, minerals[i][j]);
                minStrength = Math.min(minStrength, minerals[i][j]);
            }
        }

        int res = binarySearch();

        System.out.println(res);

        br.close();
    }

    private static int binarySearch() {

        int left = minStrength;
        int right = maxStrength;
        while (left <= right) {

            int mid = (left + right) / 2;

            if (checkMinerals(mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private static boolean checkMinerals(int target) {

        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            if (minerals[i][0] <= target) {
                q.offer(new Node(i, 0));
                visited[i][0] = true;
            }
            if (minerals[i][m - 1] <= target) {
                q.offer(new Node(i, m - 1));
                visited[i][m - 1] = true;
            }
        }

        for (int i = 1; i < m - 1; i++) {
            if (minerals[0][i] <= target) {
                q.offer(new Node(0, i));
                visited[0][i] = true;
            }
        }

        int cnt = q.size();

        while (!q.isEmpty()) {

            Node curNode = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = curNode.x + dx[i];
                int ny = curNode.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (visited[nx][ny]) continue;

                if (minerals[nx][ny] <= target) {
                    q.offer(new Node(nx, ny));
                    visited[nx][ny] = true;
                    cnt++;
                }
            }
        }

        return cnt >= k;
    }
}

```
