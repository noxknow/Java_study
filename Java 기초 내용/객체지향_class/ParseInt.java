package Practice;

public class ParseInt {
    public static void main(String[] args) {
        // 정수형 연산
        int a = 1;
        int b = 2;
        int c = a + b;
        System.out.println("a+b = "+ c);

        // 문자열 연산
        String a1 = "1";
        String b1 = "2";
        String c1 = a1 + b1;
        System.out.println("a1 + b1 = " + c1);

        // 문자열 -> 숫자로 변환 연산
        int a2 = Integer.parseInt(a1);
        int b2 = Integer.parseInt(b1);
        System.out.println(a2 + b2);

        // 진수 변환
        System.out.println("------------------------");

        System.out.println(Integer.parseInt("1001", 2)); // 9
        System.out.println(Integer.parseInt("1004", 8)); // 516
        System.out.println(Integer.parseInt("A", 16)); // 10

        System.out.println("------------------------");
    }
}
