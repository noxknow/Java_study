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
