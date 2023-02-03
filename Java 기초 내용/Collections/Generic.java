package Collections;
/*
 * generic : 무언가를 총칭하는, 포괄하는
 * 변수를 받을 때 String 타입과 int 타입을 섞어서 받을 때가 있는데 그때마다 
 * class의 타입을 바꿔야 하는 일이 발생한다. 그때 최상위 타입인 Object로 받게 된다면
 * 문제가 없다.
 
 * 이때 단점 --> string으로 받았지만 object타입으로 리턴되기 때문에 String str = 
(String)s1.getObj(); 해줘야 한다. int num = (int)s2.getObj();

이렇게 때문에 generic이란 문법이 나오게 되었다.
 */
class Sample {
    // Field
    private Object obj;

    //Constructor
    Sample(Object x) {
        this.obj = x;
    }

    // Method
    public Object getObj() {
        return obj;
    }

    void printinfo() {
        System.out.println(obj.getClass().getName()); // 객체가 속하는 클래스의 정보를 출력하는 메서드
    }
}

class Person {
    public Object obj;

    Person (Object obj) {this.obj = obj;}
}


class Student {
    public int grade;

    Student(int grade) {this.grade = grade;}
}

class Teacher {}

public class Generic {
    public static void main(String[] args) {

        Sample s1 = new Sample("안녕하세요~");
        System.out.println(s1.getObj()); // 안녕하세요~
        s1.printinfo(); // java.lang.String
        System.out.println("------------------------------------");

        Sample s2 = new Sample(100); // 위에 String과 int가 번갈아서 나오기 때문에 다 받아주기 위한 Object타입을 사용
        System.out.println(s2.getObj()); // 100
        s2.printinfo(); // java.lang.Integer
        System.out.println("------------------------------------");

        Sample s3 = new Sample(new Object());
        System.out.println(s3.getObj()); // 객체로 주었기 때문에 객체 주소 java.lang.Object@372f7a8d
        s3.printinfo(); // java.lang.Object

        // Object str = s1.getObj(); // 반환타입이 Object
        // System.out.println(str.length()); // Err
        String str = (String)s1.getObj(); // 형 변환
        System.out.println(str.length()); // 6

        // Object num = s2.getObj();
        // System.out.println(num + 100); // Err
        int num = (int)s2.getObj(); // 형 변환
        System.out.println(num + 100); // 200


        System.out.println("------------------------------------");
        // 객체 생성
        Person p1 = new Person(new Student(1));
        System.out.println(p1.obj); // 홍길동

        // 형 변환 (Cast)
        // Teacher t1 = (Teacher)p1.obj; // 이러면 컴파일은 가능하지만 실행단계 classcast에서 오류가 발생한다.
    }
}
