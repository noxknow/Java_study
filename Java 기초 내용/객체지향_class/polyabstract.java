package Practice;
/*
 * 추상 + 다형성
 */

abstract class Car {
    abstract void run();
}

class Ambulance extends Car {
    void run() {System.out.println("앰뷸런스 지나가요. 삐용삐용");}
}

class SportsCar extends Car {
    void run() {System.out.println("스포츠카 지나가요. 씽~!");}
}

public class polyabstract {
    public static void main(String[] args) {
        // Car c1 = new Ambulance(); // 객체를 자식 클래스로 만들고 받는 타입이 부모 클래스
        // Car c2 = new SportsCar();

        // c1.run(); // 앰뷸런스 지나가요. 삐용삐용
        // c2.run(); // 스포츠카 지나가요. 씽~!

        // Car[] cars = new Car[2];
        // cars[0] = new Ambulance(); 가능하다.

        // Car[] cars = new Car[2];
        // cars = new Car[] {new Ambulance(), new SportsCar()};
        Car[] cars = {new Ambulance(), new SportsCar()}; // 위의 내용 한줄로

        for (int i = 0; i < cars.length; i++) {
            cars[i].run();
        }

        System.out.println("-------------------");
        for (Car obj : cars) {
            obj.run();
        }
    }
}
