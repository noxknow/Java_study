package Practice;

class FarmMachine {

    int price;
    int year;
    String color;

    void move(){
        System.out.println("Farm-Machine is moving.");
    }

    void dig(){
        System.out.println("Farm-Machine is digging.");
    }

    void grind(){
        System.out.println("Farm-Machine is grinding.");
    }
}

public class oop {
    public static void main(String[] args) {
        
        FarmMachine fm = new FarmMachine();
        System.out.println(fm);

        fm.price = 1000000;
        fm.year = 2020;
        fm.color = "red";

        System.out.println(String.format("%,d", fm.price));
        System.out.println(fm.year);
        System.out.println(fm.color);

        fm.move();
        fm.dig();
        fm.grind();
    }
}
