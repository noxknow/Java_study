package Collections;
import java.util.ArrayList;

public class ArrayList_2 {
    public static void main(String[] args) {
        ArrayList<Integer[]> ar = new ArrayList<Integer[]> (); // 2차원 배열 만들 때

        ar.add(new Integer[] {11,12,13,14});
        ar.add(new Integer[] {21,22,23,24});
        ar.add(new Integer[] {31,32,33,34});

        for (int i = 0; i < ar.size(); i++) { // 아래 .size() 처럼 프레임 워크의 길이라 ar.size()
            System.out.println(ar.get(i)[0]); // 11 21 31
        }

        // System.out.println(ar.get(0).size()); Err (컬렉션 프레임워크의 길이를 알고 싶을 때)
        // System.out.println(ar.get(0).length()); Err (문자열의 길이를 알고 싶을 때)
        System.out.println(ar.get(0).length); // 4 (배열(int[], integer[], String[])의 길이를 알고 싶을 때)

        // 바깥쪽 배열은 size, 안쪽 배열은 length 
        for (int i = 0; i < ar.size(); i++) {
            for (int j = 0; j < ar.get(i).length; j++) {
                System.out.print(ar.get(i)[j] + " ");
            }
            System.out.println();
        }
    }
}
