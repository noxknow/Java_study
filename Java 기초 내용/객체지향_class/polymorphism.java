// package Practice;
// /*
// polymorphism : 다형성(다양한 형태, 특성을 가진다.)
// 자바와 같은 oop에서는 부모 클래스를 상속받은 자식 클래스의 인스턴스가 부모의 객체로도 
// 사용되고, 뿐만 아니라 자식 클래스의 객체로도 사용될 수 있는 다양한 상황을 의미한다.

// 즉, 하위 클래스의 인스턴스(객체)는 보다 상위 클래스의 인스턴스로도 사용될 수 있다.
// 반대는 안된다.

// ex) 앵무새는 상위인 새라고 말할 수 있지만, 새는 종류가 많아서 모든 새가 앵무새는 아니다.
// Bird aaa = new Parrot(o) --> Bird(상위, 부모), Parrot(하위, 자식)
// Parrot bbb = new Bird(x) 
//  */

// class Person {
//     String str1 = "난 부모 클래스";
//     void method1() {System.out.println("에이에이");}
//     void ppp() {System.out.println("ppp");}
// }

// class Student extends Person {
//     String str2 = "난 자식 클래스";
//     void method1() {System.out.println("오버라이드 -> AA");}
//     void sss() {System.out.println("sss");}
//     void x() {
//         method1(); // 오버라이드 -> AA
//         super.method1(); // 부모의 원본 메서드 에이에이를 호출해준다.
//     }
// }

// public class polymorphism {
//     public static void main(String[] args) {

//         Student s1 = new Student(); // 정상 (부모 + 자식 클래스의 모든 자원 사용 가능)

//         System.out.println(s1.str1); // 난 부모 클래스
//         System.out.println(s1.str2); // 난 자식 클래스
//         s1.method1(); // 오버라이드 -> AA
//         s1.sss(); // sss
//         s1.ppp(); // ppp
//         // 자식 클래스에서 오버라이딩된 부모의 원본 메서드를 호출하고 싶을 때.
//         s1.x(); // 오버라이드 -> AA, 에이에이

//         Person s2 = new Student(); // 가능 (부모의 자원만 쓸 수 있다.)

//         System.out.println(s2.str1); // 가능
//         // System.out.println(s2.str2); 불가능 Err
//         s2.ppp(); // ppp
//         // s2.sss(); 불가능 Err
//         s2.method1(); // 오버라이드 - AA (오버라이딩 한거는 자식의 메서드로 실행)
//         // 예외로 자식의 메서드를 호출하고 싶다면 캐스트 해줘야 한다.
//         ((Student)s2).sss(); // sss
 
//         Person p1  = new Person(); // 정상 (부모 본인 자원)

//         p1.method1(); // 에이에이
//         // p1.sss(); 불가능 Err

//         // Student p2 = new Person(); // Err

//     }
// }
