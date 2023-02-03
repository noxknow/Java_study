// package Practice;

// class Person {
    
//     // 속성
//     int age;
//     String name;

//     // 생성자
//     Person() {} // 받는 인자값이 없는 경우
//     Person(int age, String name){  // 받는 인자값이 두개인 경우
//         this.age = age;
//         this.name = name;
//     }
    
//     // 메서드
//     void printperson(){
//         System.out.println("나이 : " + age + ", 이름 : " + name);
//     }
// }

// public class constructor_ {
//     public static void main(String[] args) {
//         Person p1 = new Person(20, "홍길동");

//         System.out.println(p1); // 주소
//         System.out.println(p1.age); // 20
//         System.out.println(p1.name); // 홍길동
//         p1.printperson(); // 객체 p1이 호출 하는 것

//         Person p2 = new Person(30, "이순신");
//         p2.printperson(); // 객체 p2가 호출 하는 것
//     }
// }

/*
make에서와 다른 점은 make는 public class안에 함수를 만든거고, 이 경우는 밖에 새로운
클래스를 만든 경우이다. make 방식으로 해도 할 수는 있지만, 프로그램의 규모가 커지고,
여러 사람이 협업하는 경우에는 constructor(생성자)를 이용한 클래스 방식이 더 체계적
이고, 효율적이다.
 */