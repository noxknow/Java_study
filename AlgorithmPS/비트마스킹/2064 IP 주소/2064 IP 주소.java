import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br;
    static int n;
    static StringBuilder networkAddress, subnetMask;
    static String[][] ips;
    public static void main(String[] args) throws Exception {

//      br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new FileReader("input.txt"));

        // split() 메서드에서 "."은 일반 문자열이 아니라 정규 표현식으로 해석
        // 정규식에서 "."는 아무 문자(newline 제외)를 의미
        // 따라서, 단순히 점(.)을 기준으로 나누려고 했더라도, 예상치 못한 동작을 할 수 있다
        n = Integer.parseInt(br.readLine());
        ips = new String[n][4];
        for (int i = 0; i < n; i++) {
            ips[i] = br.readLine().split("\\.");
        }

        subNetting();
				
				// 마지막 "." 제거
        networkAddress.deleteCharAt(networkAddress.length()-1);
        subnetMask.deleteCharAt(subnetMask.length()-1);

        System.out.print(networkAddress+"\n"+subnetMask);

        br.close();
    }

    private static void subNetting() {

        boolean flag = false;
        networkAddress = new StringBuilder();
        subnetMask = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            int min = Integer.parseInt(ips[0][i]);
            int max = Integer.parseInt(ips[0][i]);

            for (int j = 1; j < n; j++) {
                min = min & (Integer.parseInt(ips[j][i]));
                max = max | (Integer.parseInt(ips[j][i]));
            }

            if (!flag) {
                networkAddress.append(min).append(".");
                subnetMask.append(255 - (max - min)).append(".");
            } else {
                networkAddress.append(0).append(".");
                subnetMask.append(0).append(".");
            }

            if (min != max) flag = true;
        }
    }
}
