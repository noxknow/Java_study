package Practice;
/*
인터페이스(표준, 규격) --> 사용자간 또는 컴퓨터간 통신이 가능하도록 해주는 디바이스나 프로그램
(인터페이스는 추상 메서드 말고 일반 메서드, 필드, 생성자 를 가질 수 없다.)

추상 클래스와 비슷하게 어떤 클래스가 인터페이스를 상속 받는다면 인터페이스에 있는 메서드를
구현해야 한다. (비슷하지만 추상 클래스가 더 엄격하다.) 
class --> extends(상속),       interface --> implements(구현)

class는 단일 상속, interface는 다중 상속이 가능 하다.
 */

class Person {
    String name;
    int age;
    int weight;

    Person( String name, int age, int weight ) {
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    void wash() {System.out.println("씻다.");}
    void study() {System.out.println("공부하다.");}
    void play() {System.out.println("놀다.");}
}

interface Allowance{
    // 변수는 안되나 상수는 가능
    // 인터페이스내 모든 멤버 필드(변수)는 public static final 이여야 한다. --> 생략 가능
    public static final String aaa = "코리아";
    int bb = 100;

    // public abstract 여야 하기 때문에 --> 이것도 생략 가능
    abstract void in(int price, String name); // --> 선언만 되어있는 경우가 추상 메서드
    abstract void out(int price, String name); // 만약 sysout으로 바디를 채운다면 일반 메서드로 인식한다.
}

interface Train {
    abstract void train(int pay, String name);
}

class Student extends Person implements Allowance, Train {

    Student(String name, int age, int weight) {
        super(name, age, weight);
    }

    public void in(int price, String name) {System.out.printf("%s한테 %d원 용돈%n",name,price);}
    public void out(int price, String name) {System.out.printf("%d원을 %s에서 지출%n",price,name);}
    public void train(int pay, String name) {System.out.printf("%s가 %d원 입금%n",name,pay);}
}

public class Interface {
    public static void main(String[] args) {
        Student s1 = new Student("홍길동", 20, 85);

        s1.wash();
        s1.study(); // 공부하다.
        s1.play();
        s1.in(1000, "엄마"); // 엄마한테 1000원 용돈
        s1.out(5000, "편의점"); // 5000원을 편의점에서 지출
        s1.train(50000, "아빠"); // 아빠가 50000원 입금

        System.out.println(Allowance.aaa); // 코리아 (객체생성없이 가능하다.)
        System.out.println(Allowance.bb); // 100
    }
}
