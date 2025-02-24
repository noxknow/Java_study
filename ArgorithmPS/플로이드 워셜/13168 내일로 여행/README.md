# <img src="https://d2gd6pc034wcta.cloudfront.net/tier/13.svg" width="30"> [13168] 내일로 여행

📌 → https://www.acmicpc.net/problem/13168 <br/><br/>

**핵심 로직**

- **장소 정보를 저장하고 번호를 부여한다.**
    - `removeDuplicate()` 메서드를 통해 중복된 장소를 제거하고, `places` 배열에 저장한다.
    - 각 장소에 고유한 번호를 부여하여 `numberingPlaces` 맵에 저장한다.
- **이동 비용을 저장하는 배열을 초기화한다.**
    - `costs` 배열과 `costWithTickets` 배열을 생성하고 `initialize()` 메서드에서 초기화한다.
    - 같은 장소 간 비용은 `0`, 나머지 장소 간 비용은 `INF`로 설정한다.
- **입력된 이동 정보를 기반으로 그래프를 구성한다.**
    - `connectPlace()` 메서드에서 입력을 읽어 출발지와 도착지를 `numberingPlaces`를 통해 번호로 변환한다.
    - 기본 이동 비용(`costs`)과 정기권이 적용된 비용(`costWithTickets`)을 비교하며 최소 비용을 저장한다.
    - `calculateTicket()` 메서드를 이용하여 교통수단별 정기권 할인율을 반영한다.
- **플로이드-워셜 알고리즘을 사용하여 모든 장소 간 최단 비용을 계산한다.**
    - `floydWarshall()` 메서드를 통해 모든 장소에서 모든 장소로 가는 최소 비용을 갱신한다.
    - 중간 경유지를 고려하여 비용을 갱신하면서 최단 경로를 찾는다.
- **여행 경로의 총 비용을 계산하고, 정기권 적용 여부를 비교한다.**
    - `compareResult()` 메서드에서 여행 경로에 따라 이동 비용을 합산한다.
    - 정기권을 사용했을 때(`sumWithTickets + r`)와 사용하지 않았을 때(`sum`)의 비용을 비교하여 `"Yes"` 또는 `"No"`를 출력한다. <br/><br/>

```
14 40000
Boseong Busan Changwon Cheonan Chuncheon Daegu Daejeon Gwangju Jeonju Jinju Masan Seoul Suncheon Yeosu
9
Seoul Jeonju Suncheon Yeosu Suncheon Boseong Jinju Busan Seoul
23
KTX Seoul Busan 20000
KTX Seoul Gwangju 12000
KTX Gwangju Yeosu 8000
KTX Seoul Jinju 17000
ITX-Saemaeul Seoul Cheonan 7000
ITX-Cheongchun Seoul Chuncheon 3000
V-Train Chuncheon Daegu 10000
Subway Seoul Cheonan 2000
ITX-Saemaeul Cheonan Daejeon 4000
ITX-Saemaeul Daejeon Daegu 10000
ITX-Saemaeul Daegu Busan 7000
Mugunghwa Daejeon Daegu 6000
Mugunghwa Daejeon Gwangju 5000
Mugunghwa Gwangju Jeonju 1500
Mugunghwa Jeonju Suncheon 4000
Mugunghwa Suncheon Yeosu 1500
S-Train Busan Changwon 3000
S-Train Changwon Masan 1000
S-Train Masan Jinju 2000
S-Train Jinju Suncheon 2000
S-Train Suncheon Boseong 1000
Bus Busan Jinju 1500
Bus Suncheon Jinju 4000
```

### 최종 결과 (360 ms)

```java
import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br;
    static int n, r, m, k, placeSize;
    static int INF = 987654321;
    static String[] places;
    static String[] trip;
    static Map<String, Integer> numberingPlaces;
    static double[][] costs;
    static double[][] costWithTickets;
    public static void main(String[] args) throws Exception {

//      br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st1 = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st1.nextToken());
        r = Integer.parseInt(st1.nextToken());

        removeDuplicate(br.readLine().split(" "));

        m = Integer.parseInt(br.readLine());
        trip = new String[m];
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            trip[i] = st2.nextToken();
        }

        k = Integer.parseInt(br.readLine());
        connectPlace();
        floydWarshall();
        compareResult();

        br.close();
    }

    private static void removeDuplicate(String[] arr) {

        Set<String> setPlaces = new HashSet<>(Arrays.asList(arr));
        places = setPlaces.toArray(String[]::new);
        placeSize = places.length;

        numberingPlaces = new HashMap<>();
        for (int i = 0; i < placeSize; i++) {
            numberingPlaces.put(places[i], i);
        }
    }

    private static void connectPlace() throws Exception {

        costs = new double[placeSize][placeSize];
        costWithTickets = new double[placeSize][placeSize];
        initialize();
        for (int i = 0; i < k; i++) {
            String[] input = br.readLine().split(" ");
            String type = input[0];
            String start = input[1];
            String end = input[2];
            double cost = Double.parseDouble(input[3]);
            double costWithTicket = calculateTicket(type, cost);

            int u = numberingPlaces.get(start);
            int v = numberingPlaces.get(end);
            costs[u][v] = Math.min(costs[u][v], cost);
            costs[v][u] = Math.min(costs[v][u], cost);
            costWithTickets[u][v] = Math.min(costWithTickets[u][v], costWithTicket);
            costWithTickets[v][u] = Math.min(costWithTickets[v][u], costWithTicket);
        }
    }

    private static double calculateTicket(String type, double cost) {

        Set<String> free = new HashSet<>(List.of("Mugunghwa", "ITX-Saemaeul", "ITX-Cheongchun"));
        Set<String> discount = new HashSet<>(List.of("S-Train", "V-Train"));

        if (free.contains(type)) {
            return 0;
        } else if (discount.contains(type)) {
            return 0.5 * cost;
        }

        return cost;
    }

    private static void initialize() {

        for (int i = 0; i < placeSize; i++) {
            for (int j = 0; j < placeSize; j++) {
                if (i != j) {
                    costs[i][j] = INF;
                    costWithTickets[i][j] = INF;
                }
            }
        }
    }

    private static void floydWarshall() {

        for (int k = 0; k < placeSize; k++) {
            for (int u = 0; u < placeSize; u++) {
                for (int v = 0; v < placeSize; v++) {
                    costs[u][v] = Math.min(costs[u][v], costs[u][k] + costs[k][v]);
                    costWithTickets[u][v] = Math.min(costWithTickets[u][v], costWithTickets[u][k] + costWithTickets[k][v]);
                }
            }
        }
    }

    private static void compareResult() {

        double sum = 0;
        double sumWithTickets = 0;
        for (int i = 0; i < trip.length - 1; i++) {
            int u = numberingPlaces.get(trip[i]);
            int v = numberingPlaces.get(trip[i + 1]);

            sum += costs[u][v];
            sumWithTickets += costWithTickets[u][v];
        }

        System.out.println(sumWithTickets + r < sum ? "Yes" : "No");
    }
}
```
