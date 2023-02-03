package Collections;
import java.util.ArrayList;

public class ArrayList_ {
    public static void main(String[] args) {
        ArrayList<String> ar = new ArrayList<String> ();

        // 추가 --> add()
        ar.add("홍길동");
        ar.add("이순신");
        ar.add("강감찬");
        ar.add("을지문덕");
        ar.add("김유신");
        System.out.println(ar.get(3)); // 을지문덕

        String str = ar.get(0); // 형 변환 없이 바로 사용가능하다.
        System.out.println(str.length()); // 3
        System.out.println("------------------------------------");

        // 수정 --> set(인덱스, 수정 값)
        ar.set(3,"징기스칸");
        System.out.println(ar.get(3)); // 징기스칸
        System.out.println("------------------------------------");

        // 삭제 --> 하나만 삭제 / 한꺼번에 삭제 --> remove(인덱스)
        ar.remove(3);
        System.out.println(ar.get(2)); // 강감찬
        System.out.println(ar.get(3)); // 김유신 (3번째의 징기스칸이 삭제되면서 4번째가 올라온다.)
        System.out.println("------------------------------------");

        // 출력
        for (String str1 : ar) {
            System.out.print(str1 + " "); // 홍길동 이순신 강감찬 김유신
        }
        System.out.println();
        System.out.println("------------------------------------");

        for (int i = 0; i < ar.size(); i++) {
            System.out.printf("%d번 학생의 이름은 %s 입니다.%n",i+1,ar.get(i));
            // System.out.println(ar.get(i));
        }

        // 한꺼번에 삭제 --> removeAll(배열명)
        ar.removeAll(ar);
        System.out.println(ar.size()); // 0
    }
}
