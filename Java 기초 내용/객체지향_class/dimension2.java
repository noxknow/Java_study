package Practice;
import java.util.Arrays;

// 2차원 배열

public class dimension2 {
    public static int[] test() {
        int num1 = 100;
        int num2 = 200;
        return new int[] {num1,num2};
    }
    public static void main(String[] args) {
        int[] result = test();

        System.out.println(result); // 주소 값
        System.out.println(result[0] + result[1]); // 300
        System.out.println(result[0] + " " + result[1]); // 100 200
        System.out.println(Arrays.toString(result)); // [100, 200]

    }
}



/*
String[][] arr = {{"한국", "일본"}, {"태국", "중국"}};
System.out.println(Arrays.toString(arr[0]));

위 처럼 Arrays.toString(arr[0]) 해야 [한국, 일본] 나오고
그냥 arr[0] 하면 배열이 나오지 않고 주소만 나온다.

----------------------------------------------------------------

int[] ar = {1,2,3,4,5};
int[] ar2 = {1,2,3,4,5,6,7,8,9};

System.arraycopy(ar,2,ar2,4,3); 
// --> ar2의 인덱스 4번 부터 3개를 ar의 인덱스 2번부터 3개로 교체하라
System.out.println(Arrays.toString(ar2)); // ar2 = {1,2,3,4,3,4,5,8,9}

Arrays.toString(): [1,3,4,3,5] , for 문 쓰는 경우 : 1 3 4 3 5
import java.util.Arrays; 필수
 */