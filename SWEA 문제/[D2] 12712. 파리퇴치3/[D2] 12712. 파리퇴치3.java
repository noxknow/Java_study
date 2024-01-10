import java.io.*;
import java.util.*;

public class Solution {

    static int[][] sdouku;
    static int n, m;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int i = 1; i <= t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            sdouku = new int[n*3][n*3];

            for (int j = n; j < 2 * n; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = n; k < 2 * n; k++) {
                    sdouku[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            int maxPlusFormKill = plusForm();
            int maxCrossFormKill = crossForm();

            System.out.println("#" + i + " " + Math.max(maxPlusFormKill, maxCrossFormKill));
        }
    }
    
    private static int plusForm() {
        int totalKill = 0;
        List<Integer> totalKillGroup = new ArrayList<>();

        for (int i = n; i < 2 * n; i++) {
            for (int j = n; j < 2 * n; j++) {
                totalKill = sdouku[i][j];
                for (int k = 1; k < m; k++) {
                    totalKill += sdouku[i + k][j] + sdouku[i - k][j] + sdouku[i][j + k] + sdouku[i][j - k];
                }

                totalKillGroup.add(totalKill);
            }
        }

        int maxTotalKill = Collections.max(totalKillGroup);

        return maxTotalKill;
    }
    
    private static int crossForm() {
        int totalKill = 0;
        List<Integer> totalKillGroup = new ArrayList<>();

        for (int i = n; i < 2 * n; i++) {
            for (int j = n; j < 2 * n; j++) {
                totalKill = sdouku[i][j];
                for (int k = 1; k < m; k++) {
                    totalKill += sdouku[i + k][j + k] + sdouku[i - k][j + k] + sdouku[i + k][j - k] + sdouku[i - k][j - k];
                }

                totalKillGroup.add(totalKill);
            }
        }
        
        int maxTotalKill = Collections.max(totalKillGroup);
        
        return maxTotalKill;
    }
}
