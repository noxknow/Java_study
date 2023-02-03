// package Practice;

// /**
//  * inheritance : 상속, 부모 클래스의 속성을 자식 클래스(하위 클래스)가 사용할 수 있다. 
//  */

// class person {
//     int gender;
//     int power;

//     person() {
//         this.gender = 1; // 1 : 남성, 2 : 여성
//         this.power = 100;
//     }

//     void walk() {
//         System.out.println("걸어가고 있어요~");
//     }

// }

// class hero extends person {
//     String name;
//     int age;

//     hero() {}
//     hero(String name, int age) {
//         super(); // 부모클래스의 생성자를 불러와 준다. this.gender = 1; 부분 (생략가능)
//         this.name = name;
//         this.age = age;
//     }

//     void walk() { // 부모클래스의 매서드를 자식 클래스에서 재정의 해서 사용하는 경우(오버라이딩)
//         System.out.println("두배로 빨리 걸어가고 있어요~");
//     }

//     void eat() {
//         System.out.println("밥먹고 있어요.");
//     }

//     void display() { // 부모클래스의 속성을 사용할 수 있다.
//         System.out.println("성별 : " + gender + ", 파워 : " + power + ", 이름 : " + name + ", 나이 : " + age);
//     }
// }

// public class inheritance {
//     public static void main(String[] args) {
//         person p1 = new person();
//         p1.walk(); // 걸어가고 있어요~

//         hero h1 = new hero("슈퍼맨", 20);
//         System.out.println(h1.name); // 슈퍼맨
//         System.out.println(h1.age); // 20
//         System.out.println(h1.gender); // 1, 부모클래스 속성
//         System.out.println(h1.power); // 100
//         h1.walk(); // 원래라면 "걸어가고 있어요~" 이지만 재 정의(오버라이드) 해서 "두배로 빨리 걸어가고 있어요~"
//         h1.eat();
//         h1.display();

//         hero h2 = new hero("원더우먼", 30);
//         h2.gender = 2;
//         h2.power = 300;
//         h2.display();
//     }
// }

// // 오버로딩 != 오버라이드