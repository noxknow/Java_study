import java.util.Scanner;

class Solution {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int t = 1; t <= T; t++) {
            int s = sc.nextInt();
            int[] arr = new int[s];
            int max_num = 0; 
            long answer = 0;
            for (int i = 0; i < s; i++) {
                arr[i] = sc.nextInt();
            }
            for (int j = s - 1; j > -1; j--) {
                if (arr[j] > max_num) {
                    max_num = arr[j];
                } else {
                    answer += max_num - arr[j];
                }
            }
            System.out.printf("#%d %d\n", t, answer);
        }
        sc.close();
    }
}
