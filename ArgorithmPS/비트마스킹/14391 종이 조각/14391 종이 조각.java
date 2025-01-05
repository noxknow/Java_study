import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static int rows, cols;
    static int[][] papers;
    public static void main(String[] args) throws Exception {

//      br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new FileReader("input.txt"));
        st = new StringTokenizer(br.readLine());

        rows = Integer.parseInt(st.nextToken());
        cols = Integer.parseInt(st.nextToken());

        papers = new int[rows][cols];
        fillPapers();

        System.out.println(getMaxSum());

        br.close();
    }

    private static void fillPapers() throws Exception {

        for (int i = 0; i < rows; i++) {
            String str = br.readLine();
            for (int j = 0; j < cols; j++) {
                papers[i][j] = str.charAt(j) - '0';
            }
        }
    }

    private static int getMaxSum() {

        int maxSum = Integer.MIN_VALUE;
        int bitMastMax = 1 << (rows * cols); //bitmask 의 최대값 + 1 ex) 2 * 2 라면 비트는 0001 0000 즉, 16

        for (int bitMask = 0; bitMask < bitMastMax; bitMask++) {
            
            int sum = 0;
            int num;
            int bitIdx = 1 << (rows * cols - 1);

            // 가로 검사 -> 가로로 0이 쭉 이어져 나오는 경우 sum 에 더해준다.
            // (row * cols) + col => 해당 인덱스의 비트 위치
            for (int row = 0; row < rows; row++) {
                num = 0;
                for (int col = 0; col < cols; col++) {

                    if ((bitMask & (bitIdx >> (row * cols) + col)) == 0) { // 비트가 0이면 -> 해당 숫자는 가로
                        num *= 10;
                        num += papers[row][col];
                    }
                    else {
                        sum += num;
                        num = 0;
                    }
                }

                sum += num;
            }

            // 세로 검사 -> 세로로 1이 쭉 이어져 나오는 경우 sum 에 더해준다.
            for (int col = 0; col < cols; col++) {
                num = 0;
                for (int row = 0; row < rows; row++) {

                    if ((bitMask & (bitIdx >> (row * cols) + col)) != 0) { // 비트가 1이면 -> 해당 숫자는 세로
                        num *= 10;
                        num += papers[row][col];
                    }
                    else {
                        sum += num;
                        num = 0;
                    }
                }

                sum += num;
            }

            maxSum = Math.max(maxSum, sum);
        }

        return maxSum;
    }
}
