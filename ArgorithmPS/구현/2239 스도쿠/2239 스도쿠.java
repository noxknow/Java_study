import java.io.*;
import java.util.*;

public class Main {

    static int[][] board;
    public static void main(String[] args) throws IOException {

        // BufferedReader br = new BufferedReader(new FileReader("input.txt")); 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 

        board = new int[9][9];
        for (int i = 0; i < 9; i++) {
            String input = br.readLine();
            for (int j = 0; j < 9; j++) {
                board[i][j] = input.charAt(j) - '0';
            }
        }

        playSdoku();
    }

    public static void playSdoku() {
    
        int[] idx = findZeroIdx();

        if (idx[0] == -1) { 
            printSdoku(); 
            System.exit(0);
        }

        for (int i = 1; i <= 9; i++) {
            if (isValidPos(idx, i)) {
                board[idx[0]][idx[1]] = i;
                playSdoku();
                board[idx[0]][idx[1]] = 0; // 만약 valid가 false라면 될 수 없는 값이기 때문에 다시 0이었던 값들을 0으로 다시 되돌리는 역할
            }
        }
    }

    public static int[] findZeroIdx() {

        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if (board[i][j] == 0) { return new int[]{i, j}; }
            }
        }

        return new int[]{-1, -1};
    }

    public static boolean isValidPos(int[] idx, int num) {

        int r = idx[0];
        int c = idx[1];

        // 가로 세로 검사
        for (int i = 0; i < 9; i++) {
            if (board[r][i] == num || board[i][c] == num) { return false; }
        }

        // 3x3 블록 검사
        int sr = (r/3) * 3;
        int sc = (c/3) * 3;
        for (int i = sr; i < sr+3; i++) {
            for(int j = sc; j < sc+3; j++) {
                if (board[i][j] == num) { return false; }
            }
        }

        return true;
    }

    public static void printSdoku() {

        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                sb.append(board[i][j]);
            }

            sb.append("\n");
        }

        System.out.print(sb.toString());
    }
}
