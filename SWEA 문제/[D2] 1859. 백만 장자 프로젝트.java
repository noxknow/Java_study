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

// StringBuilder 쓰는 경우

// import java.util.Scanner;

// class Solution {
//     public static void main(String args[]) throws Exception {
//         Scanner sc = new Scanner(System.in);
//         int T = sc.nextInt();
// 		StringBuilder sb = new StringBuilder();
//         for (int t = 1; t <= T; t++) {
//             int s = sc.nextInt();
//             int[] arr = new int[s];
//             int max_num = 0; 
//             long answer = 0;
//             for (int i = 0; i < s; i++) {
//                 arr[i] = sc.nextInt();
//             }
//             for (int j = s - 1; j > -1; j--) {
//                 if (arr[j] > max_num) {
//                     max_num = arr[j];
//                 } else {
//                     answer += max_num - arr[j];
//                 }
//             }
//             sb.append("#"+t+" "+answer+"\n"); 
//         }
//         System.out.print(sb.toString());
//         sc.close();
//     }
// }

// BufferedReader 로 읽어 와서, StringTokenizer 를 사용하는 경우

// import java.util.Scanner;
// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.util.StringTokenizer;

// class Solution {
//     public static void main(String args[]) throws Exception {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         int T = Integer.parseInt(br.readLine());
//         for (int t = 1; t <= T; t++) {
//             int s = Integer.parseInt(br.readLine());
//             int[] arr = new int[s];
//             int max_num = 0; 
//             long answer = 0;
            
//             String line = br.readLine();
// 						StringTokenizer st = new StringTokenizer(line," ");
//             for (int i = 0; st.hasMoreTokens(); i++) {
//                 arr[i] = Integer.parseInt(st.nextToken());
//             }
//             for (int j = s - 1; j > -1; j--) {
//                 if (arr[j] > max_num) {
//                     max_num = arr[j];
//                 } else {
//                     answer += max_num - arr[j];
//                 }
//             }
//             System.out.printf("#%d %d\n", t, answer);
//         }
//     }
// }
