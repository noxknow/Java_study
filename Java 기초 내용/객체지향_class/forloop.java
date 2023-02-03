package Practice;
import java.util.Scanner;

public class forloop {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("행의 갯수를 입력하고 [Enter] 치세요 = ");
            int R = sc.nextInt(); // 위에서 정수를 입력 받으면 R에 정수를 받는다.
            System.out.print("열의 갯수를 입력하고 [Enter] 치세요 = ");
            int C = sc.nextInt();

            char[][] gameMap = new char[R][C];
            String[] arr = new String[R];

            for (int i = 0; i < R; i++) {
                System.out.print((i+1) + "번째 행에 입력할 문자를" + C + "개 입력하고 [Enter] = ");
                arr[i] = sc.next(); // 정수를 입력받을땐 nextInt이지만 여기선 아니니
                for (int j = 0; j < C; j++) {
                    gameMap[i][j] = arr[i].charAt(j);
                }
            }

            System.out.println(arr[0]);

            System.out.println("-----------------");
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                   System.out.print(gameMap[i][j]);;
                }
                System.out.println();
            }
        }

    }
}
