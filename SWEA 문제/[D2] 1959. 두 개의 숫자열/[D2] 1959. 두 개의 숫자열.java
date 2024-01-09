import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    static int n, m;
    static List<Integer> testCase1, testCase2;
    public static void main(String[] args) throws IOException {
        
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt")); 

        int t = Integer.parseInt(br.readLine());

        for (int i=1; i<=t; i++) {
            String[] st = br.readLine().split(" ");
            n = Integer.parseInt(st[0]);
            m = Integer.parseInt(st[1]);

            testCase1 = Arrays.stream(br.readLine().split(" "))
                                          .map(Integer::parseInt)
                                          .collect(Collectors.toList());
            testCase2 = Arrays.stream(br.readLine().split(" "))
                                          .map(Integer::parseInt)
                                          .collect(Collectors.toList());

            List<Integer> maxScoreGroup = calculateMax();
            int result = Collections.max(maxScoreGroup);

            System.out.println("#" + i + " " + result);
        }
    }

    private static List<Integer> calculateMax() {
        List<Integer> maxScoreGroup = new ArrayList<>();

        if (n > m) {
            for (int i=0; i<=n-m; i++) {
                int maxScore = 0;
                for (int j=0; j<m; j++) {
                    int addScore = testCase1.get(j+i) * testCase2.get(j);
                    maxScore += addScore;
                }

                maxScoreGroup.add(maxScore);
            }

            return maxScoreGroup;
        }

        for (int i=0; i<=m-n; i++) {
            int maxScore = 0;
            for (int j=0; j<n; j++) {
                int addScore = testCase1.get(j) * testCase2.get(j+i);
                maxScore += addScore;
            }

            maxScoreGroup.add(maxScore);
        }

        return maxScoreGroup;
    }
}
