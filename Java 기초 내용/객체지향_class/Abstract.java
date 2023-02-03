package Practice;

// Abstract : 추상 클래스 (추상 : 애매모호하게 말하는 것 --> 구체적인 내용을 적지 않는다.)
// 구체적인 내용은 작성하지 않고, 공통적인 특징을 추상적으로 선언 --> 리턴값 조차 없이 메서드명만 선언
// 객체 생성 불가능, 추상 클래스는 상속을 받아서 재정의(오버라이드) 해서 사용한다.
// 추상 클래스는 자식 클래스들의 공통적인 특징을 변수나 메서드로 정의해둔 것.
// 추상 클래스는 강제성을 띄고 있다. 상속을 받는 자식 클래스는 무조건 메서드를 사용하게 만든다.
// 일반 클래스처럼 일반적인 속성, 생성자, 메서드를 포함할 수 있다.
// 추상 메서드를 하나라도 가지고 있다면, 무조건 추상 클래스로 정의 되어야 한다.
/*
꼭 재 정의(override) 해야 하는가?
--> extends로 상속을 받았다면 무조건.
하기 싫다면 자식클래스도 abstract를 붙여서 추상으로 만들어준다.
 */

abstract class Animal {
    abstract void cry();
    void eat() {System.out.println("먹다.");} // 가능은 하지만 객체생성 불가라 필요 없다.
}

class Dog extends Animal {
    void cry() {
        System.out.println("멍멍~!");
    }
}

class Cat extends Animal {
    void cry() {
        System.out.println("야옹!!");
    }
}

class Animal2 {
    void fly() {
        System.out.println("날다.");
    }
}

public class Abstract {
    public static void main(String[] args) {
        // Animal dog = new Animal(); // Err
        Animal2 dragonfly = new Animal2(); 
        dragonfly.fly(); // 날다.

        Dog dog = new Dog();
        dog.cry(); // 멍멍~!

        Cat cat = new Cat();
        cat.cry(); // 야옹!!
    }
}
