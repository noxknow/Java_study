# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/16.svg" width="30"> [1981] 배열에서 이동 

📌 → https://www.acmicpc.net/problem/1981 <br/><br/>

**핵심 로직**

1. `maxNum`과 `minNum`을 사용해 배열의 최대값과 최소값을 계산한 후, 이 둘의 차이를 이분 탐색의 초기 범위로 설정한다.
2. `binarySearch` 메서드에서는 이분 탐색을 사용해 최소 차이를 구하며, `mid`를 기준으로 배열 내의 가능한 경로를 탐색한다.
3. 배열의 특정 값이 범위 `[s, e]`에 포함되는지 확인하고, `bfs`를 통해 해당 범위 내에서 시작점에서 도착점까지 이동 가능한지 검사한다.
4. `bfs` 메서드에서는 너비 우선 탐색(Queue)을 활용하여 경로를 탐색하며, 방문 여부를 저장하는 `visited` 배열로 중복 탐색을 방지한다.
5. 조건을 만족하는 경로가 있을 경우 탐색 범위를 줄이기 위해 `right` 값을 감소시키며, 결과값 `res`를 갱신한다.
6. 조건을 만족하지 못할 경우 탐색 범위를 넓히기 위해 `left` 값을 증가시킨다.
7. 최종적으로 이분 탐색이 종료된 후 최소 차이(`res`) 값을 출력한다. <br/><br/>

```
5
1 1 3 6 8
1 2 2 5 5
4 4 0 3 3
8 0 2 3 4
4 3 0 2 1
```

### 최종 결과 (492 ms)

```java
import java.util.*;
import java.io.*;

public class Main {

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n, maxNum, minNum, res;
    static boolean[][] visited;
    static int[][] maps;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        n = Integer.parseInt(br.readLine());
        maxNum = Integer.MIN_VALUE;
        minNum = Integer.MAX_VALUE;
        res = Integer.MAX_VALUE;

        maps = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
                maxNum = Math.max(maxNum, maps[i][j]);
                minNum = Math.min(minNum, maps[i][j]);
            }
        }

        binarySearch();

        System.out.println(res);

        br.close();
    }

    private static void binarySearch() {

        int left = 0;
        int right = maxNum - minNum;

        while (left <= right) {

            int mid = (left + right) / 2;
            boolean flag = false;

            for (int i = minNum; i + mid <= maxNum; i++) {
                int s = i;
                int e = i + mid;

                if (maps[0][0] >= s && maps[0][0] <= e) {
                    if (bfs(s, e)) {
                        flag = true;
                        break;
                    }
                }
            }

            if (flag) {
                right = mid - 1;
                res = Math.min(res, mid);
            } else {
                left = mid + 1;
            }
        }
    }

    private static boolean bfs(int s, int e) {

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0));
        visited = new boolean[n][n];
        visited[0][0] = true;

        while (!q.isEmpty()) {

            Node curNode = q.poll();

            if (curNode.x == n - 1 && curNode.y == n - 1) return true;

            for (int i = 0; i < 4; i++) {
                int nx = curNode.x + dx[i];
                int ny = curNode.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (visited[nx][ny]) continue;

                if (maps[nx][ny] >= s && maps[nx][ny] <= e) {
                    q.add(new Node(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }

        return false;
    }
}

```
