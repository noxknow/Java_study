package Collections;
/*
 * 무작위로 숫자 출력하기 (0 제외)
 * Math.random() --> 반환 타입 double
 */
public class random_ {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(((int)(Math.random() * 10)+1) + " ");
        }
        System.out.println();
    }
}
