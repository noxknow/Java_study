package Practice;

public class summethod {

    public static int sum(int a) {
        a += 400;
        System.out.println(a);
        return a;
    }
    public static void main(String[] args) {
        int a = 100;
        a = sum(a);

        System.out.println(a);
    }
}
