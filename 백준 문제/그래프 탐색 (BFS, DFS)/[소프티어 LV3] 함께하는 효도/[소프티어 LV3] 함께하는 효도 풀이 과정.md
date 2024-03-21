📌 → https://softeer.ai/practice/7727 <br/><br/>

**주의할 점**

1. 처음엔 visited로 체크도 했었는데 만약 시작지점 좌우로 둘 다 큰 수가 있고 그 다음 값들은 작은 경우 3개를 다 밟지 않고 오른쪽을 갔다 다시 돌아와서 왼쪽을 들르며 2개만 밟는 경우도 체크해야해서 visited가 필요하지 않다고 생각했다.
2. 입력값으로 받는 x, y의 좌표에서 1씩 빼줘야 인덱스 번호에 맞게 들어간다. <br/><br/>

**핵심 로직**

1. x좌표와 y좌표 그리고 열매량 정보를 가지고 있는 Node 클래스를 만들어준다.
2. harvest 2차원 배열에 입력값들을 채우고 친구수에 따른 출발 위치를 Node 객체들의 리스트에 추가해준다.
    1. 이때, 열매량은 이미 Node 객체에 포함시켰으니 그 자리의 열매량을 0으로 만든다.
3. backTracking 함수를 부르고 cnt가 3초를 다 돌았고, idx가 친구 수보다 적을 때 다음 친구에 대한 backTracking을 실행한다.
    1. 들어가는 값은 다음 친구의 노드와 현재까지의 열매량을 넣어준다.
4. 들어간 친구 역시 3초가 지났고 idx가 m과 같아졌다면 결과값에 현재까지의 최대 열매량을 넣고 재귀를 반복하며 최대의 열매 수확량이 res에 저장되고 이를 출력한다. <br/><br/>

```
4 2
20 26 185 80
100 20 25 80
20 20 88 99
15 32 44 50
1 2
2 3
```

### 최종 결과 (최대 99 ms)

```java
import java.io.*;
import java.util.*;

public class Main {

    static class Node {

        int x, y, fruit;

        Node(int x, int y, int fruit) {
            this.x = x;
            this.y = y;
            this.fruit = fruit;
        }
    }

    static int n, m, res;
    static List<Node> nodes;
    static int[][] harvest;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        res = Integer.MIN_VALUE;
        nodes = new ArrayList<>();
        harvest = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                harvest[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            
            Node startNode = new Node(x - 1, y - 1, harvest[x - 1][y - 1]);
            nodes.add(startNode);
            harvest[x - 1][y - 1] = 0;
        }
        
        backTracking(nodes.get(0), 1, 0, 0);

        System.out.println(res);

        br.close();
    }

    private static void backTracking(Node curNode, int idx, int cnt, int maxFruit) {

        if (cnt == 3) {
            if (idx < m) {
                backTracking(nodes.get(idx), idx + 1, 0, maxFruit + curNode.fruit);
                return;
            }

            res = Math.max(res, maxFruit + curNode.fruit);
            return;
        };

        for (int i = 0; i < 4; i++) {
            int nx = curNode.x + dx[i];
            int ny = curNode.y + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

            Node nextNode = new Node(nx, ny, curNode.fruit + harvest[nx][ny]);
            int tmp = harvest[nx][ny];
            harvest[nx][ny] = 0;
            backTracking(nextNode, idx, cnt + 1, maxFruit);
            harvest[nx][ny] = tmp;
        }
    }
}
```

### 틀린 풀이

→ idx가 0과 1로 바뀔 때 위치 이동하는 노드 역시 nodes.get(0) 와 nodes.get(1) 로 바뀌면서 진행해야 하지만 그 부분이 불가능했다.

```java
import java.io.*;
import java.util.*;

public class Main {

    static class Node {

        int x, y, fruit;

        Node(int x, int y, int fruit) {
            this.x = x;
            this.y = y;
            this.fruit = fruit;
        }
    }

    static int n, m, res, ans;
    static List<Node> nodes;
    static int[][] harvest;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        res = Integer.MIN_VALUE;
        nodes = new ArrayList<>();
        harvest = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                harvest[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            
            Node startNode = new Node(x - 1, y - 1, harvest[x - 1][y - 1]);
            nodes.add(startNode);
            
            if (i >= 1) {
                ans += harvest[x - 1][y - 1];
            }
            
            harvest[x - 1][y - 1] = 0;
        }
        
        backTracking(nodes.get(0), 0, 0, 0);

        System.out.println(res + ans);

        br.close();
    }

    private static void backTracking(Node curNode, int idx, int cnt, int maxFruit) {

        if (cnt == m * 3) {
            res = Math.max(res, maxFruit);
            return;
        };
        

        for (int i = 0; i < 4; i++) {
            int nx = curNode.x + dx[i];
            int ny = curNode.y + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

            if (idx == 0) {
                Node nextNode = new Node(nx, ny, curNode.fruit + harvest[nx][ny]);
                int tmp = harvest[nx][ny];
                harvest[nx][ny] = 0;
                backTracking(nextNode, 1, cnt + 1, nextNode.fruit);
                harvest[nx][ny] = tmp;
            } else if (idx == 1) {
                Node nextNode = new Node(nx, ny, curNode.fruit + harvest[nx][ny]);
                int tmp = harvest[nx][ny];
                harvest[nx][ny] = 0;
                backTracking(nextNode, 0, cnt + 1, nextNode.fruit);
                harvest[nx][ny] = tmp;
            }
        }
    }
}
```
